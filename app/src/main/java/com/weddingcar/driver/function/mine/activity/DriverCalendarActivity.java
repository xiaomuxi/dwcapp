package com.weddingcar.driver.function.mine.activity;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.network.library.bean.mine.request.GetCalendarEventRequest;
import com.network.library.bean.mine.request.OperateCalendarEventRequest;
import com.network.library.bean.mine.response.GetCalendarEventEntity;
import com.network.library.constant.HttpAction;
import com.network.library.controller.NetworkController;
import com.network.library.view.NormalView;
import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.bean.UserInfo;
import com.weddingcar.driver.common.config.ToastConstant;
import com.weddingcar.driver.common.manager.SPController;
import com.weddingcar.driver.common.ui.datepicker.bizs.calendars.DPCManager;
import com.weddingcar.driver.common.ui.datepicker.bizs.decors.DPDecor;
import com.weddingcar.driver.common.ui.datepicker.cons.DPMode;
import com.weddingcar.driver.common.ui.datepicker.views.DatePicker;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriverCalendarActivity extends BaseActivity {
    private static final String OPERATE_CALENDAR_TYPE_ADD = "OPERATE_CALENDAR_TYPE_ADD";
    private static final String OPERATE_CALENDAR_TYPE_DELETE = "OPERATE_CALENDAR_TYPE_DELETE";

    @BindView(R.id.dp_calendar)
    DatePicker dp_calendar;

    private Calendar mCalendar;
    private NetworkController networkController;
    private NetworkController operateCalendarController;
    private UserInfo userInfo;
    List<String> orderDateList = new ArrayList<>();
    List<String> busDateList = new ArrayList<>();
    String currentType = "";
    String currentDate = "";
    @Override
    protected void init() {
        super.init();
        userInfo = SPController.getInstance().getUserInfo();
        setContentView(R.layout.activity_driver_calendar);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("出车日历");
    }

    @Override
    protected void initView() {
        super.initView();
        networkController = new NetworkController();
        operateCalendarController = new NetworkController();
        networkController.attachView(getCalendarEventView);
        operateCalendarController.attachView(operateCalendarView);

        initDatePicker();
        initData();
    }

    private void initDatePicker() {
        mCalendar = Calendar.getInstance();
        dp_calendar.setDate(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH)+1);
        dp_calendar.setFestivalDisplay(false);
        dp_calendar.setTodayDisplay(false);
        dp_calendar.setHolidayDisplay(false);
        dp_calendar.setDeferredDisplay(false);
        dp_calendar.setScrollEnable(false);
        dp_calendar.setMode(DPMode.MULTIPLE);
        dp_calendar.setDPDecor(new DPDecor() {

            @Override
            public void drawDecorTR(Canvas canvas, Rect rect, Paint paint, String data) {
                super.drawDecorTR(canvas, rect, paint, data);
                paint.setColor(getResources().getColor(R.color.bg_orange));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
            }

            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint, String data) {
                super.drawDecorBG(canvas, rect, paint, data);
                paint.setColor(Color.RED);
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
            }
        });
        dp_calendar.setOnTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar startDate = Calendar.getInstance();
                Calendar endDate = Calendar.getInstance();
                endDate.add(Calendar.YEAR, 10);
                endDate.set(Calendar.MONTH, 11);

                TimePickerView timePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mCalendar.setTime(date);
                        dp_calendar.setDate(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH) + 1);
                    }
                })
                        .setDate(mCalendar)
                        .setType(new boolean[]{true, true, false, false, false, false})
                        .setCancelText("取消")//取消按钮文字
                        .setSubmitText("确认")//确认按钮文字
                        .setContentTextSize(18)//滚轮文字大小
                        .setTitleSize(20)//标题文字大小
                        .setTitleText("选择年月")//标题文字
                        .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                        .isCyclic(false)//是否循环滚动
                        .setTitleColor(Color.WHITE)//标题文字颜色
                        .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                        .setCancelColor(Color.BLUE)//取消按钮文字颜色
                        .setTitleBgColor(getResources().getColor(R.color.bg_main_red))//标题背景颜色 Night mode
                        .setTextColorCenter(getResources().getColor(R.color.text_black))
                        .setBgColor(0xFFFFFFFF)//滚轮背景颜色 Night mode
                        .setRangDate(startDate, endDate)//起始终止年月日设定
                        .setLabel("年","月","日","时","分","秒")//默认设置为年月日时分秒
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .isDialog(true)//是否显示为对话框样式
                        .build();
                timePickerView.show();
            }
        });
        dp_calendar.setOnDateClickListener(new DatePicker.OnDateClickListener() {
            @Override
            public void onClick(String dateStr) {
                if (orderDateList.contains(dateStr)) {
                    UIUtils.showToastSafe("当天已有订单");
                    return;
                }

                currentDate = dateStr;
                currentType = busDateList.contains(dateStr) ? OPERATE_CALENDAR_TYPE_DELETE : OPERATE_CALENDAR_TYPE_ADD;
                operateCalendarEvent(currentType, dateFormat(dateStr, "yyyy-M-d", "yyyy-MM-dd"));
            }
        });
    }

    private void initData() {
        GetCalendarEventRequest request = new GetCalendarEventRequest();
        GetCalendarEventRequest.Query query = new GetCalendarEventRequest.Query();
        query.setApiId("HC030003");
        query.setCustomerID(userInfo.getUserId());
        query.setUserid(userInfo.getUserId());
        query.setDEVICEID(userInfo.getDeviceId());
        request.setQuery(query);

        networkController.sendRequest(HttpAction.ACTION_GET_CALENDAR_EVENT, request);
    }

    private void operateCalendarEvent(String type, String date) {
        OperateCalendarEventRequest request = new OperateCalendarEventRequest();
        OperateCalendarEventRequest.Query query = new OperateCalendarEventRequest.Query();
        query.setCustomerID(userInfo.getUserId());
        query.setUserid(userInfo.getUserId());
        query.setDEVICEID(userInfo.getDeviceId());
        query.setTime(date);
        if (StringUtils.equals(OPERATE_CALENDAR_TYPE_DELETE, type)) {
            query.setApiId("HC030002");
        }
        else {
            query.setApiId("HC030001");
        }
        request.setQuery(query);

        operateCalendarController.sendRequest(HttpAction.ACTION_OPERATE_CALENDAR_EVENT, request);
    }

    private NormalView<GetCalendarEventEntity> getCalendarEventView = new NormalView<GetCalendarEventEntity>() {
        @Override
        public void onSuccess(GetCalendarEventEntity entity) {
            LogUtils.i(TAG, entity.toString());
            GetCalendarEventEntity.Data data = entity.getData().get(0);
            List<GetCalendarEventEntity.Data.OrderlistBean> orderItemList = data.getOrderlist();
            List<GetCalendarEventEntity.Data.BuslistBean> busItemList = data.getBuslist();
            orderDateList = new ArrayList<>();
            busDateList = new ArrayList<>();
            for (int i = 0; i < orderItemList.size(); i++) {
                orderDateList.add(orderItemList.get(i).getTime());
            }
            for (int i = 0; i < busItemList.size(); i++) {
                busDateList.add(busItemList.get(i).getTime());
            }

            orderDateList = dateFormat(orderDateList, "yyyy-MM-dd", "yyyy-M-d");
            busDateList = dateFormat(busDateList, "yyyy-MM-dd", "yyyy-M-d");
            //Set order list time
            DPCManager.getInstance().setDecorBG(orderDateList);
            DPCManager.getInstance().setDecorTR(busDateList);
            dp_calendar.notifyView();
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    private NormalView operateCalendarView = new NormalView() {
        @Override
        public void onSuccess(Object entity) {
            if (StringUtils.equals(OPERATE_CALENDAR_TYPE_ADD, currentType)) {
                busDateList.add(currentDate);
            }
            else {
                busDateList.remove(currentDate);
            }

            DPCManager.getInstance().setDecorTR(busDateList);
            dp_calendar.notifyView();
            UIUtils.showToastSafe("设置成功");
        }

        @Override
        public void showLoading() {
            showProcess("正在请求数据...");
        }

        @Override
        public void hideLoading() {
            hideProcess();
        }

        @Override
        public void onRequestSuccess() {

        }

        @Override
        public void onRequestError(String errorMsg, String methodName) {
            LogUtils.i(TAG, "======>");
            LogUtils.i(TAG, errorMsg);
            UIUtils.showToastSafe(StringUtils.isEmpty(errorMsg) ? ToastConstant.TOAST_REQUEST_ERROR : errorMsg);
        }
    };

    public List<String> dateFormat(List<String> dateList, String oriFormat, String targetFormat) {
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            String dateStr = dateList.get(i);
            SimpleDateFormat dateFormat = new SimpleDateFormat(oriFormat, Locale.CHINA);
            SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat, Locale.CHINA);

            try {
                dateStr = targetDateFormat.format(dateFormat.parse(dateList.get(i)));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dates.add(dateStr);
        }

        return dates;
    }

    public String dateFormat(String dateStr, String oriFormat, String targetFormat) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(oriFormat, Locale.CHINA);
        SimpleDateFormat targetDateFormat = new SimpleDateFormat(targetFormat, Locale.CHINA);
        try {
            dateStr = targetDateFormat.format(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateStr;
    }
}
