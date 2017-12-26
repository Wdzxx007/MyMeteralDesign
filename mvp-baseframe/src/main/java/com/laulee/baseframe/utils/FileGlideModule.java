package com.laulee.baseframe.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 缓存
 * Created by laulee on 17/12/6.
 */

public class FileGlideModule implements GlideModule {
    //默认缓存大小200M
    private static final int diskCacheSize = 1024 * 1024 * 200;

    @Override
    public void applyOptions( final Context context, GlideBuilder builder ) {
        //获取提供的建议内存缓存大小
        MemorySizeCalculator memorySizeCalculator = new MemorySizeCalculator( context );
        int defaultMemoryCacheSize = memorySizeCalculator.getMemoryCacheSize( );
        int defaultBitmapPoolSize = memorySizeCalculator.getBitmapPoolSize( );
        //设置内存缓存大小
        builder.setMemoryCache( new LruResourceCache( defaultMemoryCacheSize ) );
        //设置BitmapPool缓存内存大小
        builder.setBitmapPool( new LruBitmapPool( defaultBitmapPoolSize ) );
        builder.setDiskCache( new DiskCache.Factory( ) {
            @Override
            public DiskCache build() {
                File cacheLocation = new File( context.getExternalCacheDir( ).getPath(), "image" );
                if( !cacheLocation.exists( ) )
                    cacheLocation.mkdirs( );
                return DiskLruCacheWrapper.get( cacheLocation, diskCacheSize );
            }
        } );
        //设置图片解码格式
        builder.setDecodeFormat( DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents( Context context, Glide glide ) {

    }
}
