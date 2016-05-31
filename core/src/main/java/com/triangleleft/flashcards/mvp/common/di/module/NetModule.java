package com.triangleleft.flashcards.mvp.common.di.module;

import com.triangleleft.flashcards.mvp.common.di.scope.ApplicationScope;
import com.triangleleft.flashcards.service.IDuolingoRest;
import com.triangleleft.flashcards.util.NetworkDelayInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    private final static String BASE_SCHEME = "https";
    private final static String BASE_URL = "www.duolingo.com";

    @ApplicationScope
    @Provides
    public HttpUrl endpoint() {
        return new HttpUrl.Builder().host(BASE_URL).scheme(BASE_SCHEME).build();
    }

    @ApplicationScope
    @Provides
    public Retrofit retrofit(HttpUrl url, OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(url).client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @ApplicationScope
    @Provides
    public OkHttpClient client(CookieJar cookieJar, NetworkDelayInterceptor delayInterceptor) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().cookieJar(cookieJar).addInterceptor(interceptor)
                .addInterceptor(delayInterceptor).build();
    }

    @ApplicationScope
    @Provides
    public NetworkDelayInterceptor delayInterceptor() {
        return new NetworkDelayInterceptor(0, TimeUnit.SECONDS);
    }

    @ApplicationScope
    @Provides
    public IDuolingoRest duolingoRest(Retrofit retrofit) {
        return retrofit.create(IDuolingoRest.class);
    }

}