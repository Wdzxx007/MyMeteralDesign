package com.example.jialijiang.mymeterialdesign.base;

import com.example.jialijiang.mymeterialdesign.app.App;
import com.example.jialijiang.mymeterialdesign.util.ProConfigUtil;
import com.laulee.baseframe.utils.EncodeUtil;
import com.laulee.baseframe.utils.LogUtil;
import com.laulee.baseframe.utils.SystemUtil;
import com.laulee.baseframe.utils.TimeUtil;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 参数基类
 * Created by sean on 15/10/8.
 */
public class BaseParams {
    private static final String LIST = "java.util.List";
    private static final String LANG = "lang";
    private static final String SOURCE = "source";
    private static final String CLONE_ID = "clone_id";
    private static final String VERSION = "version";
    private static final String DEVICE_UDID = "device-udid";
    private final String STRING = "java.lang.String";
    private final String INT = "int";
    private final String TIME = "time";
    private final String PARAMS = "params";
    private final String SIGN = "sign";

    private String version;
    private String deviceOnlyId;

    public BaseParams() {
        deviceOnlyId = SystemUtil.getDeviceOnlyId( App.getInstance( ) );
        version = SystemUtil.getAPPVersion( App.getInstance( ) );
    }

    /**
     * 获得map参数
     *
     * @return
     */
    public Map<String, String> obj2map() {
        Map<String, String> queryParam = new HashMap<>( );
        JSONObject json = new JSONObject( );
        for( Field field : this.getClass( ).getFields( ) ) {
            try {
                if( field.getType( ).getName( ).equals( INT ) ) {

                    int intParams = (Integer) field.get( this );
                    json.put( field.getName( ), intParams );
                } else if( field.getType( ).getName( ).equals( STRING ) ) {
                    String str = (String) field.get( this );
                    json.put( field.getName( ), str );
                } else if( field.getType( ).getName( ).equals( LIST ) ) {
                    List<String> list = (List<String>) field.get( this );
                    String listStr = list.toString( );
                    json.put( field.getName( ), listStr );
                }
                //上传图片文件
            } catch( Exception e ) {
                LogUtil.e( e.toString( ) );
            }
        }
        LogUtil.json( json.toString( ) );
        //
        StringBuffer sb = new StringBuffer( );
        sb.append( EncodeUtil.encode( json.toString( ) ) );
        String time = TimeUtil.getYMDTime( );
        sb.append( time );

        StringBuffer encodeStr = new StringBuffer( );
        encodeStr.append( EncodeUtil.getMD5( sb.toString( ) ) );
        encodeStr.append( ProConfigUtil.getConfigKey( "APP_KEY" ) );

        queryParam.put( TIME, time );
        queryParam.put( PARAMS, EncodeUtil.encode( json.toString( ) ) );
        queryParam.put( SIGN, EncodeUtil.getMD5( encodeStr.toString( ) ) );
        queryParam.put( SOURCE, ProConfigUtil.getConfigKey( "SOURCE" ) );
        queryParam.put( LANG, ProConfigUtil.getConfigKey( "LANG" ) );
        queryParam.put( CLONE_ID, "huawei" );
        queryParam.put( VERSION, version );
        queryParam.put( DEVICE_UDID, deviceOnlyId );
        return queryParam;
    }

    /**
     * 转换成partMap
     * @return
     */
    public Map<String, RequestBody> obj2partMap() {
        Map<String, RequestBody> queryParam = new HashMap<>( );
        JSONObject json = new JSONObject( );
        for( Field field : this.getClass( ).getFields( ) ) {
            try {
                if( field.getType( ).getName( ).equals( INT ) ) {

                    int intParams = (Integer) field.get( this );
                    json.put( field.getName( ), intParams );
                } else if( field.getType( ).getName( ).equals( STRING ) ) {
                    String str = (String) field.get( this );
                    json.put( field.getName( ), str );
                } else if( field.getType( ).getName( ).equals( LIST ) ) {
                    List<String> list = (List<String>) field.get( this );
                    String listStr = list.toString( );
                    json.put( field.getName( ), listStr );
                }
                //上传图片文件
            } catch( Exception e ) {
                LogUtil.e( e.toString( ) );
            }
        }
        LogUtil.json( json.toString( ) );
        //
        StringBuffer sb = new StringBuffer( );
        sb.append( EncodeUtil.encode( json.toString( ) ) );
        String time = TimeUtil.getYMDTime( );
        sb.append( time );

        StringBuffer encodeStr = new StringBuffer( );
        encodeStr.append( EncodeUtil.getMD5( sb.toString( ) ) );
        encodeStr.append( ProConfigUtil.getConfigKey( "APP_KEY" ) );

        queryParam.put( TIME, toRequestBody(time) );
        queryParam.put( PARAMS, toRequestBody( EncodeUtil.encode( json.toString( ) )) );
        queryParam.put( SIGN, toRequestBody( EncodeUtil.getMD5( encodeStr.toString( )) ) );
        queryParam.put( SOURCE, toRequestBody(ProConfigUtil.getConfigKey( "SOURCE" ) ));
        queryParam.put( LANG, toRequestBody(ProConfigUtil.getConfigKey( "LANG" ) ));
        queryParam.put( CLONE_ID, toRequestBody("huawei" ));
        queryParam.put( VERSION, toRequestBody(version ));
        queryParam.put( DEVICE_UDID, toRequestBody(deviceOnlyId) );
        return queryParam;
    }

    /**
     * 字符串转requestBody
     * @param value
     * @return
     */
    private RequestBody toRequestBody( String value ) {
        RequestBody requestBody = RequestBody.create( MediaType.parse( null), value);
        return requestBody;
    }
}
