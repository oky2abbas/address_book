package com.oky2abbas.person.common.ext

import okhttp3.MediaType
import okhttp3.RequestBody

fun String.toResponseBody(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this)
}