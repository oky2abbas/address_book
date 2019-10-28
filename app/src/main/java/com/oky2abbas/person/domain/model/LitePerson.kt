package com.oky2abbas.person.domain.model

import com.google.gson.annotations.SerializedName


data class LitePerson(
    @SerializedName("region")
    var region: Int = 1,
    @SerializedName("address")
    var address: String = "",
    @SerializedName("lat")
    var lat: Double = 35.7717503,
    @SerializedName("lng")
    var lng: Double = 51.3365315,
    @SerializedName("coordinate_mobile")
    var coordinateMobile: String = "",
    @SerializedName("coordinate_phone_number")
    var coordinatePhoneNumber: String = "",
    @SerializedName("first_name")
    var firstName: String = "",
    @SerializedName("last_name")
    var lastName: String = "",
    @SerializedName("gender")
    var gender: String = ""
)