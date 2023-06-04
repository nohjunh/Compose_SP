package com.example.authenticationdemo

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class DemoApplication(): Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // KaKao SDK 초기화
        KakaoSdk.init(this, "{NATIVE_APP_KEY}")
    }
}