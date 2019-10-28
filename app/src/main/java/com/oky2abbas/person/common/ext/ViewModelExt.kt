package com.oky2abbas.person.common.ext

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> ViewModelProvider.Factory.get(
    activity: FragmentActivity,
    clazz: Class<T>
): T {
    return ViewModelProviders.of(activity, this).get(clazz)
}