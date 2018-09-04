package com.weddingcar.driver.common.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.weddingcar.driver.R;


/**
 * Material dialog
 */
public class MaterialDialog {

    private final static int BUTTON_BOTTOM = 9;
    private final static int BUTTON_TOP = 9;

    private boolean mCancel = true;
    private boolean mOutCancel = true;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private MaterialDialog.Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private CharSequence mTitle;
    private int mMatimage = 0;
    private int mMessageResId;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private Button mNegativeButton;
    private boolean mHasShow = false;
    private int mBackgroundResId = -1;
    private Drawable mBackgroundDrawable;
    private View mMessageContentView;
    private int mMessageContentViewResId;
    private DialogInterface.OnDismissListener mOnDismissLinenter;
    private int positiveId = -1;
    private int negativeId = -1;
    private String positiveStr;
    private String negativeStr;
    private View.OnClickListener positiveLisenter;
    private View.OnClickListener negativeLisenter;
    private DialogInterface.OnKeyListener keylistener;
    private int mTitledrawable;

    public MaterialDialog(Context context) {
        this.mContext = context;
    }
    public MaterialDialog(Context context , DialogInterface.OnKeyListener keylistener) {
        this.mContext = context;
        this.keylistener = keylistener;
    }

    public void show() {
        if (!mHasShow) {
            mBuilder = new Builder();
        } else {
            mAlertDialog.show();
        }

        mHasShow = true;
    }

    public MaterialDialog setView(View view) {
        mView = view;
        if (mBuilder != null) {
            mBuilder.setView(view);
        }
        return this;
    }

    public MaterialDialog setContentView(View view) {
        mMessageContentView = view;
        mMessageContentViewResId = 0;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentView);
        }
        return this;
    }

    /**
     * Set a custom view resource to be the contents of the dialog
     *
     * @param layoutId 布局id
     * @return Dialog
     */
    public MaterialDialog setContentView(int layoutId) {
        mMessageContentViewResId = layoutId;
        mMessageContentView = null;
        if (mBuilder != null) {
            mBuilder.setContentView(mMessageContentViewResId);
        }

        return this;
    }

    public MaterialDialog setBackground(Drawable drawable) {
        mBackgroundDrawable = drawable;

        if (mBuilder != null) {
            mBuilder.setBackground(mBackgroundDrawable);
        }

        return this;
    }

    public MaterialDialog setBackgroundResource(int redId) {
        mBackgroundResId = redId;

        if (mBuilder != null) {
            mBuilder.setBackgroundResource(mBackgroundResId);
        }

        return this;
    }

    public MaterialDialog setTitleImage(int iv_matimage) {
        mMatimage = iv_matimage;
        if (mBuilder != null) {
            mBuilder.setImage(iv_matimage);
        }

        return this;
    }

    public void dismiss() {
        if(null != mAlertDialog) {
            mAlertDialog.dismiss();
        }
    }

    private int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;

        return (int) (dpValue * scale + 0.5f);
    }

//    private static boolean isLollipop() {
//        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
//    }

    public MaterialDialog setTitle(int resId) {
        mTitleResId = resId;

        if (mBuilder != null) {
            mBuilder.setTitle(resId);
        }

        return this;
    }

    public MaterialDialog setTitle(CharSequence title) {
        mTitle = title;

        if (mBuilder != null) {
            mBuilder.setTitle(title);
        }

        return this;
    }

    public MaterialDialog setMessage(int resId) {
        mMessageResId = resId;

        if (mBuilder != null) {
            mBuilder.setMessage(resId);
        }

        return this;
    }

    public MaterialDialog setMessage(CharSequence message) {
        mMessage = message;

        if (mBuilder != null) {
            mBuilder.setMessage(message);
        }

        return this;
    }

    public MaterialDialog setPositiveButton(int resId, final View.OnClickListener listener) {
        this.positiveId = resId;
        this.positiveLisenter = listener;

        return this;
    }

    public Button getPositiveButton() {
        return mPositiveButton;
    }

    public MaterialDialog setPositiveButton(String str, final View.OnClickListener listener) {
        this.positiveStr = str;
        this.positiveLisenter = listener;

        return this;
    }

    public MaterialDialog setNegativeButton(int redId, final View.OnClickListener listener) {
        this.negativeId = redId;
        this.negativeLisenter = listener;

        return this;
    }

    public Button getNegativeButton() {
        return mNegativeButton;
    }

    public MaterialDialog setNegativeButton(String str, final View.OnClickListener listener) {
        this.negativeStr = str;
        this.negativeLisenter = listener;

        return this;
    }

    public MaterialDialog setCanceledOnTouchOutside(boolean cancelable) {
        this.mOutCancel = cancelable;

        if (mBuilder != null) {
            mBuilder.setCanceledOnTouchOutside(mOutCancel);
        }

        return this;
    }

    public MaterialDialog setCancelable(boolean cancelable) {
        this.mCancel = cancelable;

        if (mBuilder != null) {
            mBuilder.setCancelable(mCancel);
        }

        return this;
    }

    public MaterialDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissLisenter) {
        this.mOnDismissLinenter = onDismissLisenter;

        return this;
    }

    private class Builder {
        private TextView mTitleView;
        private ImageView iv_materpic;
        private ViewGroup mMessageContentRoot;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;


        private Builder() {
            if (null == mAlertDialog) {
                mAlertDialog = new AlertDialog.Builder(mContext).create();
            }
            mAlertDialog.show();
            mAlertDialog.setOnKeyListener(keylistener);
            mAlertDialog.getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
            mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);

            mAlertDialogWindow = mAlertDialog.getWindow();
            View contentView = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_materialdialog, null);

            contentView.setFocusable(true);
            contentView.setFocusableInTouchMode(true);
            mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
            mAlertDialogWindow.setContentView(contentView);
            mTitleView = (TextView) mAlertDialogWindow.findViewById(R.id.tv_title);
            iv_materpic= (ImageView) mAlertDialogWindow.findViewById(R.id.iv_materpic);
            mMessageView = (TextView) mAlertDialogWindow.findViewById(R.id.tv_message);
            mButtonLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.ll_buttonLayout);
            mPositiveButton = (Button) mButtonLayout.findViewById(R.id.btn_positive);
            mNegativeButton = (Button) mButtonLayout.findViewById(R.id.btn_negative);
            mMessageContentRoot = (ViewGroup) mAlertDialogWindow.findViewById(
                    R.id.sv_message_content_root);

            if (mView != null) {
                LinearLayout linearLayout
                        = (LinearLayout) mAlertDialogWindow.findViewById(R.id.ll_contentView);

                linearLayout.removeAllViews();
                linearLayout.addView(mView);
            }

            if (mTitleResId != 0) {
                setTitle(mTitleResId);
            }

            if (mTitle != null) {
                setTitle(mTitle);
            }

            if (mTitle == null && mTitleResId == 0) {
                mTitleView.setVisibility(View.GONE);
            }

            if (mMatimage != 0) {
                setImage(mMatimage);
            }

            if (mMatimage == 0) {
                iv_materpic.setVisibility(View.GONE);
            }

            if (mMessageResId != 0) {
                setMessage(mMessageResId);
            }

            if (mMessage != null) {
                setMessage(mMessage);
            }

            if (positiveId != -1) {
                mPositiveButton.setVisibility(View.VISIBLE);
                mPositiveButton.setText(positiveId);
                mPositiveButton.setOnClickListener(positiveLisenter);
            }

            if (negativeId != -1) {
                mNegativeButton.setVisibility(View.VISIBLE);
                mPositiveButton.setText(negativeId);
                mPositiveButton.setOnClickListener(negativeLisenter);
            }

            if (!isNullOrEmpty(positiveStr)) {
                mPositiveButton.setVisibility(View.VISIBLE);
                mPositiveButton.setText(positiveStr);
                mPositiveButton.setOnClickListener(positiveLisenter);
            }

            if (!isNullOrEmpty(negativeStr)) {
                mNegativeButton.setVisibility(View.VISIBLE);
                mNegativeButton.setText(negativeStr);
                mNegativeButton.setOnClickListener(negativeLisenter);
            }

            if (isNullOrEmpty(positiveStr) && positiveId == -1) {
                mPositiveButton.setVisibility(View.GONE);
            }

            if (isNullOrEmpty(negativeStr) && negativeId == -1) {
                mNegativeButton.setVisibility(View.GONE);
            }

            if (mBackgroundResId != -1) {
                LinearLayout linearLayout
                        = (LinearLayout) mAlertDialogWindow.findViewById(
                        R.id.ll_material_background);

                linearLayout.setBackgroundResource(mBackgroundResId);
            }

            if (mBackgroundDrawable != null) {
                LinearLayout linearLayout
                        = (LinearLayout) mAlertDialogWindow.findViewById(
                        R.id.ll_material_background);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    linearLayout.setBackground(mBackgroundDrawable);
                }
            }

            if (mMessageContentView != null) {
                this.setContentView(mMessageContentView);
            } else if (mMessageContentViewResId != 0) {
                this.setContentView(mMessageContentViewResId);
            }

            mAlertDialog.setCanceledOnTouchOutside(mOutCancel);
            mAlertDialog.setCancelable(mCancel);

            if (mOnDismissLinenter != null) {
                mAlertDialog.setOnDismissListener(mOnDismissLinenter);
            }
        }

        /**
         * Set positive button
         *
         * @param text     the button name of button
         * @param listener view on click listener
         */
        public void setPositiveButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setPadding(dip2px(12), 0, dip2px(32), dip2px(BUTTON_BOTTOM));
            button.setOnClickListener(listener);

            mButtonLayout.addView(button);
        }

        public void setNegativeButton(String text, final View.OnClickListener listener) {
            Button button = new Button(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setGravity(Gravity.CENTER);
            button.setTextSize(14);
            button.setPadding(dip2px(12), 0, dip2px(32), dip2px(BUTTON_BOTTOM));
            button.setOnClickListener(listener);

            mButtonLayout.addView(button);
        }

        public void setView(View view) {
            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.ll_contentView);
            linearLayout.removeAllViews();

            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            view.setLayoutParams(layoutParams);

            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    mAlertDialogWindow.setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                    InputMethodManager imm
                            = (InputMethodManager) mContext.getSystemService(
                            Context.INPUT_METHOD_SERVICE);

                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                            InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            });

            linearLayout.addView(view);

            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof EditText) {
                        EditText editText = (EditText) viewGroup.getChildAt(i);
                        editText.setFocusable(true);
                        editText.requestFocus();
                        editText.setFocusableInTouchMode(true);
                    }
                }

                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    if (viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView autoCompleteTextView
                                = (AutoCompleteTextView) viewGroup.getChildAt(i);

                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusable(true);
                    }
                }
            }
        }

        public void setContentView(View contentView) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            contentView.setLayoutParams(layoutParams);
            if (contentView instanceof ListView) {
                setListViewHeightBasedOnChildren((ListView) contentView);
            }

            LinearLayout linearLayout = (LinearLayout) mAlertDialogWindow
                    .findViewById(R.id.ll_message_content_view);
            if (linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }

            for (int i = 0;
                 i < (linearLayout != null ? linearLayout.getChildCount() : 0);
                 i++) {
                if (linearLayout.getChildAt(i)
                        instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView
                            = (AutoCompleteTextView) linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }
        }

        /**
         * Set a custom view source to be the contents of the dialog,
         * The resoucre will be inflate into a ScrollView;
         *
         * @param layoutId resource ID to be inflated
         */
        public void setContentView(int layoutId) {
            mMessageContentRoot.removeAllViews();

            // Not Setting this to other content view because user has defind their own
            // layout params, and we dont't want to overwrite those
            LayoutInflater.from(mMessageContentRoot.getContext())
                    .inflate(layoutId, mMessageContentRoot);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout
                    = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.ll_material_background);
            linearLayout.setBackground(drawable);
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout
                    = (LinearLayout) mAlertDialogWindow.findViewById(
                    R.id.ll_material_background);
            linearLayout.setBackgroundResource(resId);
        }

        public void setTitle(int resId) {
            mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            mTitleView.setText(title);
        }

        public void setMessage(int resId) {
            if (mMessageView != null) {
                mMessageView.setText(resId);
            }
        }

        public void setMessage(CharSequence message) {
            if (mMessageView != null) {
                mMessageView.setText(message);
            }
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }

        public void setCancelable(boolean canceledOnTouchOutside) {
            mAlertDialog.setCancelable(canceledOnTouchOutside);
        }

        public void setImage(int mTitledrawable) {
            iv_materpic.setImageResource(mTitledrawable);
        }
    }

    private boolean isNullOrEmpty(String nText) {
        return nText == null || nText.isEmpty();
    }

    private static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure( View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = totalHeight +
                (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(layoutParams);
    }

    public DialogInterface.OnKeyListener getKeylistener() {
        return keylistener;
    }

    public void setKeylistener(DialogInterface.OnKeyListener keylistener) {
        this.keylistener = keylistener;
    }
}
