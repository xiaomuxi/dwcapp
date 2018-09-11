package com.weddingcar.driver.function.user.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.weddingcar.driver.R;
import com.weddingcar.driver.common.base.BaseActivity;
import com.weddingcar.driver.common.utils.DrawableUtils;
import com.weddingcar.driver.common.utils.FileUtils;
import com.weddingcar.driver.common.utils.LogUtils;
import com.weddingcar.driver.common.utils.PictureUtils;
import com.weddingcar.driver.common.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{
    private static final int PHOTO_REQUEST_CAMERA = 1001;  // 拍照获取头像
    private static final int PHOTO_REQUEST_ALBUM = 1002; // 从相册中选择头像
    private static final String PHOTO_FILE_NAME = "head";  // 头像照片

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_boy)
    TextView tv_boy;
    @BindView(R.id.tv_girl)
    TextView tv_girl;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.btn_complete)
    Button btn_complete;

    TextView tv_take_picture;
    TextView tv_pic_album;
    TextView tv_cancel;
    private Dialog mPicChooseDialog;
    Uri imageUri;
    private boolean isBoySelected = true;

    @Override
    protected void init() {
        super.init();
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        setActionBar(R.layout.common_top_bar);
        setTopTitleAndLeft("完善个人信息");
    }

    @Override
    protected void initView() {
        super.initView();
        initHead();
        tv_boy.setSelected(isBoySelected);
        tv_girl.setSelected(!isBoySelected);
        iv_head.setOnClickListener(this);
        tv_boy.setOnClickListener(this);
        tv_girl.setOnClickListener(this);
        btn_complete.setOnClickListener(this);
    }

    private void initHead() {
        iv_head.setImageDrawable(getResources().getDrawable(R.drawable.my_head));
//        RequestOptions options = new RequestOptions().placeholder(R.drawable.my_head);
//        Glide.with(this).load("").apply(options).into(iv_head);
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
                takePicture(PHOTO_FILE_NAME, PHOTO_REQUEST_CAMERA);
                mPicChooseDialog.dismiss();
            }
        });
        tv_pic_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromAlbum(PHOTO_REQUEST_ALBUM);
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

    /**
     * 拍照获取图片
     */
    private void takePicture(String name, int reqCode) {
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


    private void onSelectSex(boolean isBoy) {
        if (isBoySelected == isBoy) {
            return;
        }
        isBoySelected = isBoy;
        tv_girl.setSelected(!isBoySelected);
        tv_boy.setSelected(isBoySelected);
    }

    private void onSubmitPersonalInfo() {
        //TODO: checking the info and submit it to server.
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                showCameraChoose();
                break;
            case R.id.tv_boy:
                onSelectSex(true);
                break;
            case R.id.tv_girl:
                onSelectSex(false);
                break;
            case R.id.btn_complete:
                onSubmitPersonalInfo();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_CAMERA && resultCode == RESULT_OK) {
            LogUtils.i(TAG, "拍照获取到图片");
            String imgPath = FileUtils.getRealFilePath(mContext, imageUri);
            Bitmap bitmap = DrawableUtils.getBitmapFromPath(imgPath, 50, 50);
            if (bitmap == null) {
                UIUtils.showToastSafe("图片有问题，不支持上传！");
            } else {
                iv_head.setImageBitmap(bitmap);
            }
        }

        if(requestCode == PHOTO_REQUEST_ALBUM && resultCode == RESULT_OK && data != null) {
            LogUtils.i(TAG, "从相册获取到图片");
            //图库
            String imgPath = PictureUtils.getPath(mContext, data.getData());
            Bitmap bitmap = DrawableUtils.getBitmapFromPath(imgPath, 50, 50);
            //保存图片到本地
            if (bitmap == null) {
                UIUtils.showToastSafe("图片有问题，不支持上传！");
            } else {
                iv_head.setImageBitmap(bitmap);
            }
        }
    }
}
