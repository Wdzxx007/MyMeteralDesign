package com.example.jialijiang.mymeterialdesign.http.api;

import com.laulee.baseframe.bean.ResponseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * system模块接口
 * Created by laulee on 17/8/4.
 */

public interface SystemApi {

    //登录
//    @POST("api/login")
//    @FormUrlEncoded
//    public Observable<ResponseBean<AccessToken>> login( @FieldMap Map<String, String> params );

    //获取验证码
    @POST("api/sendVerifyCode")
    @FormUrlEncoded
    public Observable<ResponseBean<String>> getAuthCode( @FieldMap Map<String, String> params );

    //获取消息
//    @POST("api/selectMsgByPhone")
//    @FormUrlEncoded
//    public Observable<ResponseBean<List<MessageEntity>>> getMessage(
//            @FieldMap Map<String, String> params );

    //首页用户信息
//    @POST("api/index")
//    @FormUrlEncoded
//    public Observable<ResponseBean<UserInfoEntity>> getUserInfo(
//            @FieldMap Map<String, String> params );

    //贷款申请
    @POST("api/loanApply")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> doLoanApply( @FieldMap Map<String, String> params );

    //贷款进度
//    @POST("api/findLoanInfo")
//    @FormUrlEncoded
//    Observable<ResponseBean<List<LoanApplyEntity>>> getLoanApplyRecord(
//            @FieldMap Map<String, String> params );

    //绑定银行卡
    @POST("api/bankCardBinding")
    @FormUrlEncoded
    Observable<ResponseBean<Object>> doBindCard( @FieldMap Map<String, String> params );

    //获取银行卡列表
    @POST("api/getSupportBank")
    @FormUrlEncoded
    Observable<ResponseBean<List>> getBankName( @FieldMap Map<String, String> params );

    //实名认证
    @POST("api/identityVerification")
    @Multipart
    Observable<ResponseBean<Object>> doIdentityVerification(
            @PartMap Map<String, RequestBody> files, @PartMap Map<String, String> params );

    //个人中心信息
//    @POST("api/personal")
//    @FormUrlEncoded
//    Observable<ResponseBean<UserCenterEntity>> getUserCenter( @FieldMap Map<String, String>
//            params );

    //银行卡信息
//    @POST("api/selectByPhone")
//    @FormUrlEncoded
//    Observable<ResponseBean<List<BankEntity>>> getBankCard( @FieldMap Map<String, String> params );

    //手机认证token
//    @POST("api/getMobilePhoneToken")
//    @FormUrlEncoded
//    Observable<ResponseBean<AuthTokenEntity>> getMobilePhoneToken(
//            @FieldMap Map<String, String> params );

    //手机采集请求
//    @POST("api/mobilePhoneCertification")
//    @FormUrlEncoded
//    Observable<ResponseBean<CertificationEntity>> certification(
//            @FieldMap Map<String, String> params );

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
