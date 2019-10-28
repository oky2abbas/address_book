package com.oky2abbas.person.di.module

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oky2abbas.person.BuildConfig
import com.oky2abbas.person.utils.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class RestModule {

    @Singleton
    @Provides
    @Named("BaseURL")
    fun provideBaseURL(): String {
        return BuildConfig.web + "/api/"
    }

    @Singleton
    @Provides
    fun provideGSon(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gSon: Gson,
        @Named("BaseURL") baseURL: String
    ): Retrofit {
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(nullResponse())
            .addConverterFactory(GsonConverterFactory.create(gSon))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .callTimeout(4, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().header(
                    "Authorization", Credentials.basic(
                        "09822222222",
                        "sana1234"
                    )
                ).build()
                chain.proceed(request)
            }

        return httpClient.build()
    }

    @Singleton
    @Provides
    @Named("nullResponse")
    fun nullResponse() = object : Converter.Factory() {
        fun converterFactory() = this

        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, Any?> {

            return object : Converter<ResponseBody, Any?> {

                val nextResponseBodyConverter = retrofit
                    .nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody): Any? {
                    return if (value.contentLength() != 0L)
                        nextResponseBodyConverter.convert(value)
                    else null
                }
            }
        }
    }
}