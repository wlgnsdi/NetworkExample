package com.healthyryu.networktest

data class ClinicalInfo(
    val name: String,
    val code: String,
    val purpose: String,
) {
    override fun toString(): String {
        return "Name : $name\nCode : $code\nPurpose : $purpose"
    }
}