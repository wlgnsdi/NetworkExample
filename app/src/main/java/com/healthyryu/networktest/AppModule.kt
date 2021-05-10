package com.healthyryu.networktest

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: Interceptor = Interceptor { chain ->
        val response: Response = chain.proceed(chain.request())
        Log.d("NetworkModule", ">> " + response.request.url.toString())
        response
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        val httpClient = OkHttpClient.Builder().apply {
            addNetworkInterceptor(StethoInterceptor())
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl("http://api2.inhandplus.com/admin/")
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository {
        return ApiRepository(apiService)
    }

}