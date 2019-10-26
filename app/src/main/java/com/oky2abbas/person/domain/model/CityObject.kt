package com.oky2abbas.person.domain.model


import com.google.gson.annotations.SerializedName

data class CityObject(
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("city_name")
    val cityName: String
)