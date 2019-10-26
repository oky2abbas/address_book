package com.oky2abbas.person.domain.model


import com.google.gson.annotations.SerializedName

data class StateObject(
    @SerializedName("state_id")
    val stateId: Int,
    @SerializedName("state_name")
    val stateName: String
)