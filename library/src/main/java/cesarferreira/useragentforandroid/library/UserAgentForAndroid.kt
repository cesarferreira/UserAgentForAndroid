package cesarferreira.useragentforandroid.library

import android.os.Build
import com.jaredrummler.android.device.DeviceName
import okhttp3.Interceptor
import okhttp3.Response

class UserAgentForAndroid(
    private val appName: String,
    private val appVersion: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)

        val deviceName = DeviceName.getDeviceName()
        val androidName = currentAndroidName()
        val androidRelease = getCurrentAndroidRelease()
        val userAgent =
            "$appName/$appVersion (Android $androidName $androidRelease, API ${Build.VERSION.SDK_INT}; $deviceName)"

        val requestBuilder = original.newBuilder()
            .header("User-Agent", userAgent)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun currentAndroidName(): String {
        val release = getCurrentAndroidRelease()

        return when {
            release >= 4.1 && release < 4.4 -> "Jelly Bean"
            release < 5 -> "Kit Kat"
            release < 6 -> "Lollipop"
            release < 7 -> "Marshmallow"
            release < 8 -> "Nougat"
            release < 9 -> "Oreo"
            release < 10 -> "Pie"
            release < 11 -> "Q"
            release < 12 -> "R"
            release < 13 -> "S"
            release < 14 -> "T"
            else -> "??"
        }
    }

    private fun getCurrentAndroidRelease(): Double {
        return java.lang.Double.parseDouble(
            java.lang.String(Build.VERSION.RELEASE)
                .replaceAll("(\\d+[.]\\d+)(.*)", "$1")
        )
    }

}