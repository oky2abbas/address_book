package com.oky2abbas.person.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

class LocaleUtils {
    companion object {
        fun setLocale(c: Context): Context {
            return updateResources(c, "fa")
        }

//        fun setNewLocale(c: Context, language: String): Context {
//            return updateResources(c, language)
//        }

        private fun updateResources(context: Context, language: String): Context {
            var newContext = context

            val locale = Locale(language)
            Locale.setDefault(locale)

            val res = context.resources
            val config = Configuration(res.configuration)
            config.setLocale(locale)
            newContext = context.createConfigurationContext(config)

            return newContext
        }

//        fun getLocale(res: Resources): Locale {
//            val config = res.configuration
//            return if (Build.VERSION.SDK_INT >= 24)
//                config.locales.get(0)
//            else config.locale
//        }
    }
}