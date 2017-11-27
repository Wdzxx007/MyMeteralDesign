package com.example.jialijiang.mymeterialdesign.base.params;

import android.content.Context;

import com.example.jialijiang.mymeterialdesign.base.IBaseParams;
import com.example.jialijiang.mymeterialdesign.util.EncodeUtil;
import com.example.jialijiang.mymeterialdesign.util.LogUtil;
import com.example.jialijiang.mymeterialdesign.util.SystemUtil;
import com.example.jialijiang.mymeterialdesign.util.TimeUtil;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 上产参数基类
 * Created by sean on 16/5/12.
 */
public class BaseParams implements IBaseParams {
    private static final String LIST = "java.util.List";
    private final String STRING = "java.lang.String";
    private final String INT = "int";
    private final String source = "201";
    private final String TIME = "time";
    private final String LANG = "lang";
    private final String lang = "zh_CN";
    private final String PARAMS = "params";
    private final String SIGN = "sign";
    private final String SOURCE = "source";

    private final String VERSION = "version";
    private final String DEVICE_UDID = "device-udid";
    private final String app_key = "95cd002b3b4df2e4d3fc085301847ae0";

    private String version;
    private String deviceOnlyId;

    public BaseParams( Context context ) {
        deviceOnlyId = SystemUtil.getDeviceOnlyId( context );
        version = SystemUtil.getAPPVersion( context );
    }

    @Override
    public JSONObject generateParams() {
        JSONObject json = new JSONObject( );
        for( Field field : getClass( ).getFields( ) ) {
            try {
                if( field.getType( ).getName( ).equals( INT ) ) {
                    int intParams = (Integer) field.get( this );
                    json.put( field.getName( ), intParams );
                } else if( field.getType( ).getName( ).equals( STRING ) ) {
                    String strParams = (String) field.get( this );
                    json.put( field.getName( ), strParams );
                }
            }
            catch( IllegalAccessException | JSONException e ) {
                e.printStackTrace( );
            }
        }
        LogUtil.json( json.toString( ) );
        return json;
    }

    @Override
    public String generateSign( JSONObject json ) {
        String sb = EncodeUtil.encode( json.toString( ) ) + TimeUtil.getYMDTime( );
        return EncodeUtil.getMD5( sb ) + app_key;
    }

    public RequestParams generateRequestParams() {
        RequestParams params = new RequestParams( );
        params.addBodyParameter( SOURCE, source );
        params.addBodyParameter( TIME, TimeUtil.getYMDTime( ) );
        params.addBodyParameter( LANG, lang );
        params.addBodyParameter( VERSION, version );
        params.addBodyParameter( DEVICE_UDID, deviceOnlyId );

        return params;
    }

    /**
     * 获得map参数
     * @return
     */
    public Map<String, Object> obj2map() {
        Map<String, Object> queryParam = new HashMap<String, Object>( );
        JSONObject json = new JSONObject( );
        for( Field field : this.getClass( ).getFields( ) ) {
            try {
                if( field.getType( ).getName( ).equals( INT ) ) {

                    int intParams = (Integer) field.get( this );
                    json.put( field.getName( ), intParams );
                } else if( field.getType( ).getName( ).equals( STRING ) ) {
                    String str = (String) field.get( this );
                    //如果为图片文件
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
        encodeStr.append( app_key );

        queryParam.put( SOURCE, source );
        queryParam.put( TIME, time );
        queryParam.put( LANG, lang );
        queryParam.put( PARAMS, EncodeUtil.encode( json.toString( ) ) );
        queryParam.put( SIGN, EncodeUtil.getMD5( encodeStr.toString( ) ) );
        queryParam.put( VERSION, version );
        queryParam.put( DEVICE_UDID, deviceOnlyId );
        return queryParam;
    }

}
