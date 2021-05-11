package com.healthyryu.networktest.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.healthyryu.networktest.network.ApiService
import com.healthyryu.networktest.repo.ApiRepository
import com.healthyryu.networktest.utils.BASE_URL
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

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor: Interceptor = Interceptor { chain ->
            val response: Response = chain.proceed(chain.request())
            response
        }

        val httpClient = OkHttpClient.Builder().apply {
            addNetworkInterceptor(StethoInterceptor())
            addInterceptor(interceptor)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(
        apiService: ApiService
    ): ApiRepository {
        return ApiRepository(apiService)
    }

}