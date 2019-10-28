package com.oky2abbas.person.domain.model


import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("region")
    val region: Region,
    @SerializedName("address")
    val address: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("coordinate_mobile")
    val coordinateMobile: String,
    @SerializedName("coordinate_phone_number")
    val coordinatePhoneNumber: String
)