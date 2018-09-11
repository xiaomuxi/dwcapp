package com.weddingcar.driver.common.ui;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Riky luwei on 2016/10/24.
 */
public class VerifyCodeView extends AppCompatTextView {

    // handler通知消息
    public static final int MESSAGE_TIME_START = 1;   // 倒计时开始
    public static final int MESSAGE_TIME_UPDATE = 2;  // 倒计时更新
    public static final int MESSAGE_TIME_END = 3;  // 倒计时结束
    public static final int DEFAULT_MAX_TIME = 60;  // 倒计时结束
    public static final String PRE_MESSAGE_TEXT = "";
    public static final String SUF_MESSAGE_TEXT = "s";
    private String mPreMessage = PRE_MESSAGE_TEXT;
    private String mSufMessage = SUF_MESSAGE_TEXT;
    private int mMaxTime = DEFAULT_MAX_TIME;
    private int mCountDownTime;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private String mInitText; // 初始文本信息
    private OnSendCodeListener mSendCodeListener;
    private boolean isCount = false;

    public VerifyCodeView(Context context) {
        super(context);
        initView();
    }

    public VerifyCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_TIME_START:
                    isCount = true;
                    setSelected(false);
                    setText(mPreMessage + --mCountDownTime + mSufMessage);
                    break;
                case MESSAGE_TIME_UPDATE:
                    setText(mPreMessage + --mCountDownTime + mSufMessage);
                    break;
                case MESSAGE_TIME_END:
                    mCountDownTime = mMaxTime;
                    isCount = false;
                    setSelected(true);
                    setText(mInitText);
                    if(null!= mTimer) {
                        mTimer.cancel();
                        mTimer = null;
                        mTimerTask = null;
                    }
                    break;
            }
        }
    };

    private void initView() {
        mInitText = getText().toString().trim();
        setSelected(true);
        setOnClickListener(mClickListener);
    }

    private OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isCount) {
                return;
            }
            if (mSendCodeListener != null) {
                mSendCodeListener.onStartSend();
            }
        }
    };

    /**
     * 开始倒计时
     */
    public void startToCountDown() {
        mCountDownTime = mMaxTime;
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if (mCountDownTime == mMaxTime) {
                    mHandler.obtainMessage(MESSAGE_TIME_START).sendToTarget();
                } else if (mCountDownTime < mMaxTime && mCountDownTime > 0) {
                    mHandler.obtainMessage(MESSAGE_TIME_UPDATE).sendToTarget();
                } else {
                    mHandler.obtainMessage(MESSAGE_TIME_END).sendToTarget();
                }
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    /**
     * 重置状态
     */
    private void reset() {
        mHandler.obtainMessage(MESSAGE_TIME_END).sendToTarget();
    }



    public void setSendCodeListener(OnSendCodeListener clickListener) {
        this.mSendCodeListener = clickListener;
    }

    public void setMaxTime(int maxTime) {
        this.mMaxTime = maxTime;
        this.mCountDownTime = maxTime;
    }

    public void setPreMessageText(String preMessageText) {
        this.mPreMessage = preMessageText;
    }

    public void setSufMessageText(String sufMessageText) {
        this.mSufMessage = sufMessageText;
    }

    public interface OnSendCodeListener {
        void onStartSend();
    }
}

