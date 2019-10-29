package com.oky2abbas.person.common.ext

import org.greenrobot.eventbus.EventBus

fun Any.pushToBus() {
    EventBus.getDefault().post(this)
}

fun Any.registerBus() {
    EventBus.getDefault().register(this)
}

fun Any.unRegisterBus() {
    EventBus.getDefault().unregister(this)
}