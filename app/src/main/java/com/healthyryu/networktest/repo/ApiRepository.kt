package com.healthyryu.networktest.repo

import com.healthyryu.networktest.network.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService
) {

    fun getInfo(id: String) = service.getClinicalInfo(id)
}