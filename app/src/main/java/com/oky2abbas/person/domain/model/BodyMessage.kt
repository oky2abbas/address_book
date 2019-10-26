package com.oky2abbas.person.domain.model

import com.google.gson.annotations.SerializedName


data class BodyMessage(
    @SerializedName("data")
    val data: Any = Any(),
    @SerializedName("message")
    val message: String = ""
)