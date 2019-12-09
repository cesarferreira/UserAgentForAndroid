package cesarferreira.useragentforandroid.library

import android.os.Build
import com.jaredrummler.android.device.DeviceName

class DeviceInfo {

    companion object {

        /**
         * e.g. Marshmallow / Pie / Nougat
         */
        fun currentAndroidName(): String {
            val release = androidReleaseNumber()

            return when {
                release >= 4.1 && release < 4.4 -> "Jelly Bean"
                release < 5 -> "Kit Kat"
                release < 6 -> "Lollipop"
                release < 7 -> "Marshmallow"
                release < 8 -> "Nougat"
                release < 9 -> "Oreo"
                release < 10 -> "Pie"
                release < 11 -> "10"
                release < 12 -> "11"
                release < 13 -> "12"
                release < 14 -> "13"
                else -> "??"
            }
        }

        /**
         * e.g. Pixel 3 / Samsung S10
         */
        fun getDeviceName(): String = DeviceName.getDeviceName()

        /**
         * e.g. "9.0" for Pie
         */
        fun androidReleaseNumber(): Double {
            val strippedVersion =
                java.lang.String(Build.VERSION.RELEASE).replaceAll("(\\d+[.]\\d+)(.*)", "$1")
            return try {
                java.lang.Double.parseDouble(strippedVersion)
            } catch (exp: Exception) {
                when (strippedVersion) {
                    "Q" -> 10
                    "R" -> 11
                    "S" -> 12
                    "T" -> 13
                    "U" -> 14
                    else -> (-1)
                }.toDouble()
            }
        }
    }
}
