package com.healthyryu.networktest.network

import com.healthyryu.networktest.data.ClinicalInfo
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("project")
    fun getClinicalInfo(@Query("_id") id: String): Single<Response<ClinicalInfo>>
}