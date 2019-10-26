package com.oky2abbas.addressbook.utils

import android.content.Context
import com.github.oky2abbas.ktx.provider.isNetworkAvailable
import com.oky2abbas.addressbook.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isNetworkAvailable()) {
            throw NoConnectivityException(context)
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}

class NoConnectivityException(val context: Context) : IOException() {
    override val message: String?
        get() = context.getString(R.string.str_network_not_available)
}
