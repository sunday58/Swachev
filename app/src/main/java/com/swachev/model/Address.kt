package com.swachev.model

data class Address(
    val city: String,
    val country: String,
    val lattitude: Long,
    val line1: String,
    val line2: String,
    val longitude: Long,
    val state: String,
    val zipCode: Int
)