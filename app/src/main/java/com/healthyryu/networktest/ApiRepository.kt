package com.healthyryu.networktest

import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val service: ApiService
) {

    fun getInfo(id: String) = service.getClinicalInfo(id)
}