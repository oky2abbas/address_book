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

data class CityObject(
    @SerializedName("city_id")
    val cityId: Int,
    @SerializedName("city_name")
    val cityName: String
)

data class Region(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("city_object")
    val cityObject: CityObject,
    @SerializedName("state_object")
    val stateObject: StateObject
)

data class StateObject(
    @SerializedName("state_id")
    val stateId: Int,
    @SerializedName("state_name")
    val stateName: String
)