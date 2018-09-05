package com.network.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import java.io.File;

public class GlideUtils {
    public static final void loadShow(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext()).load(path).into(imageView);
    }

    public static final void loadGif(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext()).asGif().load(path).into(imageView);
    }

    public static final void loadBitmap(Context context, Bitmap bitmap, ImageView imageView) {
        Glide.with(context.getApplicationContext()).asBitmap().load(bitmap).into(imageView);
    }

    public static final void loadShow(Context context, Object path, ImageView imageView, OnLoadImageResult callback) {
        Glide.with(context.getApplicationContext()).load(path).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                imageView.setImageDrawable(resource);
                callback.onSuccess();
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                callback.onError();
            }
        });
    }

    public static void loadFile(Context context, String path, ImageView imageView) {
        Glide.with(context).load(Uri.fromFile(new File(path))).into(imageView);
    }

    public interface OnLoadImageResult {
        void onSuccess();

        void onError();
    }
}
