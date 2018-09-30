package com.weddingcar.driver.function.mine.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.Base64Utils;
import com.weddingcar.driver.common.utils.DrawableUtils;
import com.weddingcar.driver.common.utils.FileUtils;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.PictureUtils;
import com.weddingcar.driver.common.utils.StringUtils;
import com.weddingcar.driver.common.utils.UIUtils;
import com.weddingcar.driver.function.mine.adapter.LettersAdapter;
import com.weddingcar.driver.function.mine.listener.PlateNumberListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarAuthActivity extends BaseActivity implements View.OnClickListener{

    private final static int REQUEST_CODE_CAR_BRANDS = 1001;
    private final static int REQUEST_CODE_CAR_COLORS = 1002;
    private final static int REQUEST_CODE_ADDRESS_CHOOSE = 1003;
    private static final int REQUEST_CODE_PICTURE_CAMERA = 1004;  // 拍照获取头像
    private static final int REQUEST_CODE_PICTURE_ALBUM = 1005;  // 从相册中选择头像
    private final List<String> provinces = Arrays.asList("京", "津", "冀", "晋", "蒙", "辽", "吉", "黑", "沪", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂",
            "湘", "粤", "桂", "琼", "渝", "川", "贵", "云", "藏", "陕", "甘", "青", "宁", "新", "台", "", "", "");
    private final List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "", "");
    private final List<String> lettersAndFigures = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "", "", "", "",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "", "");

    @BindView(R.id.ll_brand)
    LinearLayout ll_brand;
    @BindView(R.id.tv_brand)
    TextView tv_brand;
    @BindView(R.id.ll_color)
    LinearLayout ll_color;
    @BindView(R.id.tv_color)
    TextView tv_color;
    @BindView(R.id.ll_plate_number)
    LinearLayout ll_plate_number;
    @BindView(R.id.tv_plate_number)
    TextView tv_plate_number;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.iv_car_left_1)
    ImageView iv_car_left_1;
    @BindView(R.id.iv_car_middle_1)
    ImageView iv_car_middle_1;
    @BindView(R.id.iv_car_right_1)
    ImageView iv_car_right_1;
    @BindView(R.id.iv_car_left_2)
    ImageView iv_car_left_2;
    @BindView(R.id.iv_car_middle_2)
    ImageView iv_car_middle_2;
    @BindView(R.id.iv_car_right_2)
    ImageView iv_car_right_2;
    @BindView(R.id.iv_id_left)
    ImageView iv_id_left;
    @BindView(R.id.iv_id_right)
    ImageView iv_id_right;
    @BindView(R.id.iv_driver_left)
    ImageView iv_driver_left;
    @BindView(R.id.iv_driver_right)
    ImageView iv_driver_right;

    private LettersAdapter lettersProvinceAdapter;
    private LettersAdapter lettersCityAdapter;
    private LettersAdapter lettersNumberOneAdapter;
    private LettersAdapter lettersNumberTwoAdapter;
    private LettersAdapter lettersNumberThreeAdapter;
    private LettersAdapter lettersNumberFourAdapter;
    private LettersAdapter lettersNumberFiveAdapter;
    private LettersAdapter lettersNumberSixAdapter;

    private int currentPlateNumberIndex = 0;
    private int provinceSelectedIndex = -1;
    private int citySelectedIndex = -1;
    private int numberOneSelectedIndex = -1;
    private int numberTwoSelectedIndex = -1;
    private int numberThreeSelectedIndex = -1;
    private int numberFourSelectedIndex = -1;
    private int numberFiveSelectedIndex = -1;
    private int numberSixSelectedIndex = -1;

    private GridView gv_province;
    private GridView gv_city;
    private GridView gv_number_1;
    private GridView gv_number_2;
    private GridView gv_number_3;
    private GridView gv_number_4;
    private GridView gv_number_5;
    private GridView gv_number_6;
    private TextView tv_province;
    private TextView tv_city;
    private TextView tv_number_1;
    private TextView tv_number_2;
    private TextView tv_number_3;
    private TextView tv_number_4;
    private TextView tv_number_5;
    private TextView tv_number_6;

    private Dialog mPlateNumberDialog;

    private String carModelID;
    private String carColorID;
    private String address;
    private String imgCarLeft1;
    private String imgCarLeft2;
    private String imgCarMiddle1;
    private String imgCarMiddle2;
    private String imgCarRight1;
    private String imgCarRight2;
    private String imgIdLeft;
    private String imgIdRight;
    private String imgDriverLeft;
    private String imgDriverRight;

    private String currentSelectedImage;
    private Dialog mPicChooseDialog;
    TextView tv_take_picture;
    TextView tv_pic_album;
    TextView tv_cancel;
    Uri imageUri;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_car_auth);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("上传车辆信息");
    }

    @Override
    protected void initView() {
        super.initView();

        ll_brand.setOnClickListener(this);
        ll_color.setOnClickListener(this);
        ll_plate_number.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        iv_car_left_1.setOnClickListener(this);
        iv_car_left_2.setOnClickListener(this);
        iv_car_middle_1.setOnClickListener(this);
        iv_car_middle_2.setOnClickListener(this);
        iv_car_right_1.setOnClickListener(this);
        iv_car_right_2.setOnClickListener(this);
        iv_id_left.setOnClickListener(this);
        iv_id_right.setOnClickListener(this);
        iv_driver_left.setOnClickListener(this);
        iv_driver_right.setOnClickListener(this);

        initDialog();
    }

    private void initDialog() {
        mPlateNumberDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_dialog_plate_number, null);
        initDialogView(inflate);
        // 将布局设置给Dialog
        mPlateNumberDialog.setContentView(inflate);
        // 获取当前Activity所在的窗体
        Window dialogWindow = mPlateNumberDialog.getWindow();
        // 设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        // 获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = getWindowManager().getDefaultDisplay().getWidth();
        // 将属性设置给窗体
        dialogWindow.setAttributes(lp);
    }

    public void initDialogView(View view) {
        tv_province = (TextView) view.findViewById(R.id.tv_province);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_number_1 = (TextView) view.findViewById(R.id.tv_number_1);
        tv_number_2 = (TextView) view.findViewById(R.id.tv_number_2);
        tv_number_3 = (TextView) view.findViewById(R.id.tv_number_3);
        tv_number_4 = (TextView) view.findViewById(R.id.tv_number_4);
        tv_number_5 = (TextView) view.findViewById(R.id.tv_number_5);
        tv_number_6 = (TextView) view.findViewById(R.id.tv_number_6);

        tv_province.setSelected(true);
        tv_province.setOnClickListener(plateNumberItemClickListener);
        tv_city.setOnClickListener(plateNumberItemClickListener);
        tv_number_1.setOnClickListener(plateNumberItemClickListener);
        tv_number_2.setOnClickListener(plateNumberItemClickListener);
        tv_number_3.setOnClickListener(plateNumberItemClickListener);
        tv_number_4.setOnClickListener(plateNumberItemClickListener);
        tv_number_5.setOnClickListener(plateNumberItemClickListener);
        tv_number_6.setOnClickListener(plateNumberItemClickListener);


        PlateNumberListener plateNumberListener = new PlateNumberListener() {
            @Override
            public void onSelectedItemChanged(int index) {
                switch (currentPlateNumberIndex) {
                    case 0:
                        tv_province.setText(provinces.get(lettersProvinceAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 1;
                        plateNumberViewChanged();
                        break;
                    case 1:
                        tv_city.setText(letters.get(lettersCityAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 2;
                        plateNumberViewChanged();
                        break;
                    case 2:
                        tv_number_1.setText(lettersAndFigures.get(lettersNumberOneAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 3;
                        plateNumberViewChanged();
                        break;
                    case 3:
                        tv_number_2.setText(lettersAndFigures.get(lettersNumberTwoAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 4;
                        plateNumberViewChanged();
                        break;
                    case 4:
                        tv_number_3.setText(lettersAndFigures.get(lettersNumberThreeAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 5;
                        plateNumberViewChanged();
                        break;
                    case 5:
                        tv_number_4.setText(lettersAndFigures.get(lettersNumberFourAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 6;
                        plateNumberViewChanged();
                        break;
                    case 6:
                        tv_number_5.setText(lettersAndFigures.get(lettersNumberFiveAdapter.getSelectedIndex()));
                        currentPlateNumberIndex = 7;
                        plateNumberViewChanged();
                        break;
                    case 7:
                        tv_number_6.setText(lettersAndFigures.get(lettersNumberSixAdapter.getSelectedIndex()));
                        break;
                }
            }

            @Override
            public void onButtonDeleteClicked() {
                switch (currentPlateNumberIndex) {
                    case 0:
                        lettersProvinceAdapter.setSelectedIndex(-1);
                        tv_province.setText("");
                        break;
                    case 1:
                        if (lettersCityAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 0;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersCityAdapter.setSelectedIndex(-1);
                        tv_city.setText("");
                        break;
                    case 2:
                        if (lettersNumberOneAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 1;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberOneAdapter.setSelectedIndex(-1);
                        tv_number_1.setText("");
                        break;
                    case 3:
                        if (lettersNumberTwoAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 2;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberTwoAdapter.setSelectedIndex(-1);
                        tv_number_2.setText("");
                        break;
                    case 4:
                        if (lettersNumberThreeAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 3;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberThreeAdapter.setSelectedIndex(-1);
                        tv_number_3.setText("");
                        break;
                    case 5:
                        if (lettersNumberFourAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 4;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberFourAdapter.setSelectedIndex(-1);
                        tv_number_4.setText("");
                        break;
                    case 6:
                        if (lettersNumberFiveAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 5;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberFiveAdapter.setSelectedIndex(-1);
                        tv_number_5.setText("");
                        break;
                    case 7:
                        if (lettersNumberSixAdapter.getSelectedIndex() == -1) {
                            currentPlateNumberIndex = 6;
                            plateNumberViewChanged();
                            return;
                        }
                        lettersNumberSixAdapter.setSelectedIndex(-1);
                        tv_number_6.setText("");
                        break;
                }
            }
        };

        ImageView iv_cancel = (ImageView) view.findViewById(R.id.iv_cancel);
        TextView tv_complete = (TextView) view.findViewById(R.id.tv_complete);
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlateNumberDialog.dismiss();
            }
        });

        tv_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkPlateNumber()) {
                    provinceSelectedIndex = lettersProvinceAdapter.getSelectedIndex();
                    citySelectedIndex = lettersCityAdapter.getSelectedIndex();
                    numberOneSelectedIndex = lettersNumberOneAdapter.getSelectedIndex();
                    numberTwoSelectedIndex= lettersNumberTwoAdapter.getSelectedIndex();
                    numberThreeSelectedIndex = lettersNumberThreeAdapter.getSelectedIndex();
                    numberFourSelectedIndex = lettersNumberFourAdapter.getSelectedIndex();
                    numberFiveSelectedIndex = lettersNumberFiveAdapter.getSelectedIndex();
                    numberSixSelectedIndex = lettersNumberSixAdapter.getSelectedIndex();
                    String plateNumber = lettersProvinceAdapter.getSelectItem() + lettersCityAdapter.getSelectItem()
                            + lettersNumberOneAdapter.getSelectItem() + lettersNumberTwoAdapter.getSelectItem() + lettersNumberThreeAdapter.getSelectItem()
                            + lettersNumberFourAdapter.getSelectItem() + lettersNumberFiveAdapter.getSelectItem();
                    if (numberSixSelectedIndex != -1) {
                        plateNumber += lettersNumberSixAdapter.getSelectItem();
                    }
                    tv_plate_number.setText(plateNumber);
                    mPlateNumberDialog.dismiss();
                }
            }
        });

        gv_province = (GridView) view.findViewById(R.id.gv_province);
        gv_city = (GridView) view.findViewById(R.id.gv_city);
        gv_number_1 = (GridView) view.findViewById(R.id.gv_number_1);
        gv_number_2 = (GridView) view.findViewById(R.id.gv_number_2);
        gv_number_3 = (GridView) view.findViewById(R.id.gv_number_3);
        gv_number_4 = (GridView) view.findViewById(R.id.gv_number_4);
        gv_number_5 = (GridView) view.findViewById(R.id.gv_number_5);
        gv_number_6 = (GridView) view.findViewById(R.id.gv_number_6);
        lettersProvinceAdapter = new LettersAdapter(this, plateNumberListener);
        lettersCityAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberOneAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberTwoAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberThreeAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberFourAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberFiveAdapter = new LettersAdapter(this, plateNumberListener);
        lettersNumberSixAdapter = new LettersAdapter(this, plateNumberListener);
        gv_province.setAdapter(lettersProvinceAdapter);
        gv_city.setAdapter(lettersCityAdapter);
        gv_number_1.setAdapter(lettersNumberOneAdapter);
        gv_number_2.setAdapter(lettersNumberTwoAdapter);
        gv_number_3.setAdapter(lettersNumberThreeAdapter);
        gv_number_4.setAdapter(lettersNumberFourAdapter);
        gv_number_5.setAdapter(lettersNumberFiveAdapter);
        gv_number_6.setAdapter(lettersNumberSixAdapter);
        lettersProvinceAdapter.setData(provinces);
        lettersCityAdapter.setData(letters);
        lettersNumberOneAdapter.setData(lettersAndFigures);
        lettersNumberTwoAdapter.setData(lettersAndFigures);
        lettersNumberThreeAdapter.setData(lettersAndFigures);
        lettersNumberFourAdapter.setData(lettersAndFigures);
        lettersNumberFiveAdapter.setData(lettersAndFigures);
        lettersNumberSixAdapter.setData(lettersAndFigures);
    }

    private boolean checkPlateNumber() {
        if (StringUtils.isEmpty(lettersProvinceAdapter.getSelectItem()) || StringUtils.isEmpty(lettersCityAdapter.getSelectItem())
                || StringUtils.isEmpty(lettersNumberOneAdapter.getSelectItem()) || StringUtils.isEmpty(lettersNumberTwoAdapter.getSelectItem())
                || StringUtils.isEmpty(lettersNumberThreeAdapter.getSelectItem()) || StringUtils.isEmpty(lettersNumberFourAdapter.getSelectItem())
                || StringUtils.isEmpty(lettersNumberFiveAdapter.getSelectItem())) {
            UIUtils.showToastSafe("请完善车辆牌照信息！");
            return false;
        }
        return true;
    }

    private View.OnClickListener plateNumberItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_province:
                    if (currentPlateNumberIndex != 0) {
                        currentPlateNumberIndex = 0;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_city:
                    if (currentPlateNumberIndex != 1) {
                        currentPlateNumberIndex = 1;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_1:
                    if (currentPlateNumberIndex != 2) {
                        currentPlateNumberIndex = 2;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_2:
                    if (currentPlateNumberIndex != 3) {
                        currentPlateNumberIndex = 3;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_3:
                    if (currentPlateNumberIndex != 4) {
                        currentPlateNumberIndex = 4;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_4:
                    if (currentPlateNumberIndex != 5) {
                        currentPlateNumberIndex = 5;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_5:
                    if (currentPlateNumberIndex != 6) {
                        currentPlateNumberIndex = 6;
                        plateNumberViewChanged();
                    }
                    break;
                case R.id.tv_number_6:
                    if (currentPlateNumberIndex != 7) {
                        currentPlateNumberIndex = 7;
                        plateNumberViewChanged();
                    }
                    break;
            }
        }
    };

    private void plateNumberViewChanged() {
        tv_province.setSelected(currentPlateNumberIndex == 0);
        tv_city.setSelected(currentPlateNumberIndex == 1);
        tv_number_1.setSelected(currentPlateNumberIndex == 2);
        tv_number_2.setSelected(currentPlateNumberIndex == 3);
        tv_number_3.setSelected(currentPlateNumberIndex == 4);
        tv_number_4.setSelected(currentPlateNumberIndex == 5);
        tv_number_5.setSelected(currentPlateNumberIndex == 6);
        tv_number_6.setSelected(currentPlateNumberIndex == 7);
        gv_province.setVisibility(currentPlateNumberIndex == 0 ? View.VISIBLE : View.GONE);
        gv_city.setVisibility(currentPlateNumberIndex == 1 ? View.VISIBLE : View.GONE);
        gv_number_1.setVisibility(currentPlateNumberIndex == 2 ? View.VISIBLE : View.GONE);
        gv_number_2.setVisibility(currentPlateNumberIndex == 3 ? View.VISIBLE : View.GONE);
        gv_number_3.setVisibility(currentPlateNumberIndex == 4 ? View.VISIBLE : View.GONE);
        gv_number_4.setVisibility(currentPlateNumberIndex == 5 ? View.VISIBLE : View.GONE);
        gv_number_5.setVisibility(currentPlateNumberIndex == 6 ? View.VISIBLE : View.GONE);
        gv_number_6.setVisibility(currentPlateNumberIndex == 7 ? View.VISIBLE : View.GONE);
    }

    private void initPlateNumberView() {
        if (provinceSelectedIndex != -1) {
            tv_province.setText(provinces.get(provinceSelectedIndex));
            tv_city.setText(letters.get(citySelectedIndex));
            tv_number_1.setText(lettersAndFigures.get(numberOneSelectedIndex));
            tv_number_2.setText(lettersAndFigures.get(numberTwoSelectedIndex));
            tv_number_3.setText(lettersAndFigures.get(numberThreeSelectedIndex));
            tv_number_4.setText(lettersAndFigures.get(numberFourSelectedIndex));
            tv_number_5.setText(lettersAndFigures.get(numberFiveSelectedIndex));
            tv_number_6.setText(lettersAndFigures.get(numberSixSelectedIndex));
            lettersProvinceAdapter.setSelectedIndex(provinceSelectedIndex);
            lettersCityAdapter.setSelectedIndex(citySelectedIndex);
            lettersNumberOneAdapter.setSelectedIndex(numberOneSelectedIndex);
            lettersNumberTwoAdapter.setSelectedIndex(numberTwoSelectedIndex);
            lettersNumberThreeAdapter.setSelectedIndex(numberThreeSelectedIndex);
            lettersNumberFourAdapter.setSelectedIndex(numberFourSelectedIndex);
            lettersNumberFiveAdapter.setSelectedIndex(numberFiveSelectedIndex);
            if (numberSixSelectedIndex != -1) {
                lettersNumberSixAdapter.setSelectedIndex(numberSixSelectedIndex);
            }
        }
    }


    private void goToCarBrandActivity() {
        Intent intent = new Intent(this, CarBrandActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CAR_BRANDS);
    }
    private void goToCarColorActivity() {
        Intent intent = new Intent(this, CarColorActivity.class);
        startActivityForResult(intent, REQUEST_CODE_CAR_COLORS);
    }
    private void goToAddressChooseActivity() {
        Intent intent = new Intent(this, AddressChooseActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADDRESS_CHOOSE);
    }

    /**
     * 拍照获取图片
     */
    private void takePicture(int reqCode) {
        imageUri = DrawableUtils.createImageUri(mContext, "store"); // 创建用来存储图片的uri
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, reqCode);
    }

    /**
     * 从相册取
     */
    private void pickImageFromAlbum(int reqCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, reqCode);
    }

    /**
     * 展示底部相册,拍照选择对话框
     */
    public void showCameraChoose() {
        mPicChooseDialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_dialog_camera_choose, null);
        //初始化控件
        tv_take_picture = (TextView) inflate.findViewById(R.id.tv_take_picture);
        tv_pic_album = (TextView) inflate.findViewById(R.id.tv_pic_album);
        tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);

        tv_take_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(REQUEST_CODE_PICTURE_CAMERA);
                mPicChooseDialog.dismiss();
            }
        });
        tv_pic_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromAlbum(REQUEST_CODE_PICTURE_ALBUM);
                mPicChooseDialog.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicChooseDialog.dismiss();
            }
        });
        // 将布局设置给Dialog
        mPicChooseDialog.setContentView(inflate);
        // 获取当前Activity所在的窗体
        Window dialogWindow = mPicChooseDialog.getWindow();
        // 设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        // 获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;  // 设置Dialog距离底部的距离
        // 将属性设置给窗体
        dialogWindow.setAttributes(lp);
        mPicChooseDialog.show();    // 显示对话框
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_brand:
                goToCarBrandActivity();
                break;
            case R.id.ll_color:
                goToCarColorActivity();
                break;
            case R.id.ll_plate_number:
                initPlateNumberView();
                mPlateNumberDialog.show();
                break;
            case R.id.ll_address:
                goToAddressChooseActivity();
                break;
            case R.id.iv_car_left_1:
                currentSelectedImage = "iv_car_left_1";
                showCameraChoose();
                break;
            case R.id.iv_car_middle_1:
                currentSelectedImage = "iv_car_middle_1";
                showCameraChoose();
                break;
            case R.id.iv_car_right_1:
                currentSelectedImage = "iv_car_right_1";
                showCameraChoose();
                break;
            case R.id.iv_car_left_2:
                currentSelectedImage = "iv_car_left_2";
                showCameraChoose();
                break;
            case R.id.iv_car_middle_2:
                currentSelectedImage = "iv_car_middle_2";
                showCameraChoose();
                break;
            case R.id.iv_car_right_2:
                currentSelectedImage = "iv_car_right_2";
                showCameraChoose();
                break;
            case R.id.iv_id_left:
                currentSelectedImage = "iv_id_left";
                showCameraChoose();
                break;
            case R.id.iv_id_right:
                currentSelectedImage = "iv_id_right";
                showCameraChoose();
                break;
            case R.id.iv_driver_left:
                currentSelectedImage = "iv_driver_left";
                showCameraChoose();
                break;
            case R.id.iv_driver_right:
                currentSelectedImage = "iv_driver_right";
                showCameraChoose();
                break;
        }
    }

    private void setImage(Bitmap bitmap) {
        String imageStream = Base64Utils.bitmapToBase64(bitmap);
        switch (currentSelectedImage) {
            case "iv_car_left_1":
                imgCarLeft1 = imageStream;
                iv_car_left_1.setImageBitmap(bitmap);
                break;
            case "iv_car_middle_1":
                imgCarMiddle1 = imageStream;
                iv_car_middle_1.setImageBitmap(bitmap);
                break;
            case "iv_car_right_1":
                imgCarRight1 = imageStream;
                iv_car_right_1.setImageBitmap(bitmap);
                break;
            case "iv_car_left_2":
                imgCarLeft2 = imageStream;
                iv_car_left_2.setImageBitmap(bitmap);
                break;
            case "iv_car_middle_2":
                imgCarMiddle2 = imageStream;
                iv_car_middle_2.setImageBitmap(bitmap);
                break;
            case "iv_car_right_2":
                imgCarRight2 = imageStream;
                iv_car_right_2.setImageBitmap(bitmap);
                break;
            case "iv_id_left":
                imgIdLeft = imageStream;
                iv_id_left.setImageBitmap(bitmap);
                break;
            case "iv_id_right":
                imgIdRight = imageStream;
                iv_id_right.setImageBitmap(bitmap);
                break;
            case "iv_driver_left":
                imgDriverLeft = imageStream;
                iv_driver_left.setImageBitmap(bitmap);
                break;
            case "iv_driver_right":
                imgDriverRight = imageStream;
                iv_driver_right.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAR_BRANDS:
                if (resultCode == 1) {
                    carModelID = data.getStringExtra("CAR_MODEL_ID");
                    tv_brand.setText(data.getStringExtra("CAR_MODEL_VALUE"));
                }
                break;
            case REQUEST_CODE_CAR_COLORS:
                if (resultCode == 1) {
                    carColorID = data.getStringExtra("CAR_COLOR_ID");
                    tv_color.setText(data.getStringExtra("CAR_COLOR_VALUE"));
                }
                break;
            case REQUEST_CODE_ADDRESS_CHOOSE:
                if (resultCode == 1) {
                    address = data.getStringExtra("ADDRESS");
                    tv_address.setText(address);
                }
                break;
            case REQUEST_CODE_PICTURE_CAMERA:
                if (resultCode == RESULT_OK) {
                    LogUtils.i(TAG, "拍照获取到图片");
                    String imgPath = FileUtils.getRealFilePath(mContext, imageUri);
                    Bitmap bitmap = DrawableUtils.getBitmapFromPath(imgPath, 50, 50);
                    int degree = DrawableUtils.readPictureDegree(imgPath);// 获取图片旋转的角度
                    bitmap = DrawableUtils.rotaingImageView(degree,bitmap);// 将bitmap旋转回来
                    if (bitmap == null) {
                        UIUtils.showToastSafe("图片有问题，不支持上传！");
                        return;
                    }

                    setImage(bitmap);
                }
                break;
            case REQUEST_CODE_PICTURE_ALBUM:
                if (resultCode == RESULT_OK && data != null) {
                    LogUtils.i(TAG, "从相册获取到图片");
                    //图库
                    String imgPath = PictureUtils.getPath(mContext, data.getData());
                    Bitmap bitmap = DrawableUtils.getBitmapFromPath(imgPath, 50, 50);
                    int degree = DrawableUtils.readPictureDegree(imgPath);// 获取图片旋转的角度
                    bitmap = DrawableUtils.rotaingImageView(degree,bitmap);// 将bitmap旋转回来
                    //保存图片到本地
                    if (bitmap == null) {
                        UIUtils.showToastSafe("图片有问题，不支持上传！");
                        return;
                    }
                    setImage(bitmap);
                }
                break;
        }
    }
}
