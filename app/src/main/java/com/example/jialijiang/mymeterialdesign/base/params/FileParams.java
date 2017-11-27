package com.example.jialijiang.mymeterialdesign.base.params;

import android.content.Context;

import com.lidroid.xutils.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件基类
 * Created by sean on 16/11/17.
 */
public class FileParams extends TokenParams {
    private final String PARAMS = "params";
    private final String SIGN = "sign";

    private String pictureInfo = "pictureInfo";
    private ArrayList<FilePushEntity> fileList;

    public FileParams( Context context ) {
        super( context );
    }

    /**
     * 设置文件列表
     *
     * @param fileList 文件列表
     */
    public void setFileList( ArrayList<FilePushEntity> fileList ) {
        this.fileList = fileList;
    }

    public ArrayList<FilePushEntity> getFileList() {
        return fileList;
    }



    /**
     * 图片挨个上传
     */
    private void generateFile( RequestParams params, ArrayList<FilePushEntity> pushList,
            JSONObject json ) {
        JSONObject object = new JSONObject( );
        try {
            for( FilePushEntity entity : pushList ) {
                List<String> fileList = entity.getFileList( );
                JSONArray pictureArray = new JSONArray( );
                for( String filePath : fileList ) {
                    File pictureFile = new File( filePath );
                    if( !filePath.startsWith( "http://" ) && pictureFile.exists( ) ) {
                        params.addBodyParameter( pictureFile.getName( ), pictureFile );
                        pictureArray.put( pictureFile.getName( ) );
                    }
                }
                object.put( entity.getFileName( ), pictureArray );
            }
            json.put( pictureInfo, object );
        }
        catch( JSONException e ) {
            e.printStackTrace( );
        }
    }

    /**
     * 生成zip文件夹上传,默认fileList的文件都存在一个文件夹下
     *
     * @param params
     */
    private void generateZipFile( RequestParams params, FilePushEntity fileList ) {
        //        if( params == null || fileList == null || fileList.isEmpty( ) ) {
        //            return;
        //        }
        //        FilePushEntity entity = fileList.get( 0 );
        //        String filePath = entity.getFilePath( );
        //        if( TextUtils.isEmpty( filePath ) ) {
        //            File photoFile = new File( filePath );
        //            if( photoFile.exists( ) ) {
        //                String parentFile = photoFile.getParent( );
        //                String targetPath = photoFile.getParentFile( ).getParent( );
        //                if( parentFile != null && targetPath != null ) {
        //                    try {
        //                        String zipFilePath = CompressedFileUtil.compressedFile(
        // parentFile,
        //                                targetPath );
        //                        File zipFile = new File( zipFilePath );
        //                        if( zipFile.exists( ) ) {
        //                            params.addBodyParameter( entity.getFileName( ), zipFile );
        //                        }
        //                    }
        //                    catch( Exception e ) {
        //                        e.printStackTrace( );
        //                    }
        //                }
        //
        //            }
        //        }
    }
}
