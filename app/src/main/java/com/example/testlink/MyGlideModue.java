package com.example.testlink;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by lxx on 2019/4/29.
 * Use by
 *
 * 在4.0中不用像3.X需要在AndroidManifest.xml配置GlideModule，而是通过注解继承AppGlideModule的子类来配置。
 */
@GlideModule
public class MyGlideModue extends AppGlideModule {
    /**
     * @param context
     * @param builder
     */
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
    }
    /**
     * 禁止解析Manifest
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }
}
