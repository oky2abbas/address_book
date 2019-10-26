package com.oky2abbas.person.domain.model


import com.google.gson.annotations.SerializedName

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