package cesarferreira.useragentforandroid

import android.content.Context
import cesarferreira.useragentforandroid.library.UserAgentForAndroid
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {

    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") srsearch: String
    ): Observable<Model.Result>

    companion object {
        fun create(context: Context): WikiApiService {

            val userAgent = UserAgentForAndroid(
                appName = context.getString(R.string.app_name),
                appVersion = BuildConfig.VERSION_NAME
            )
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient.Builder()
                .followRedirects(true)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(userAgent)
                .build()


            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }

}