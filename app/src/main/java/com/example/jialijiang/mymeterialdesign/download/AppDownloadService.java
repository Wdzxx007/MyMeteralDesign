package com.example.jialijiang.mymeterialdesign.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface AppDownloadService {
    @Streaming
    @GET("{url}")
    Observable<ResponseBody> download( @Path("url") String apkName );
}
