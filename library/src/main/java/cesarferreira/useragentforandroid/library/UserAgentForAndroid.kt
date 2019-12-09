package cesarferreira.useragentforandroid.library

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentForAndroid(
    private val appName: String,
    private val appVersion: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)

        val deviceName = DeviceInfo.getDeviceName()
        val androidName = DeviceInfo.currentAndroidName()
        val androidRelease = DeviceInfo.androidReleaseNumber()
        val userAgent =
            "$appName/$appVersion (Android $androidName $androidRelease, API ${Build.VERSION.SDK_INT}; $deviceName)"

        val requestBuilder = original.newBuilder()
            .header("User-Agent", userAgent)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}