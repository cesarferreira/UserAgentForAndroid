# UserAgentForAndroid [![](https://jitpack.io/v/cesarferreira/UserAgentForAndroid.svg)](https://jitpack.io/#cesarferreira/UserAgentForAndroid)


> Identify important app information on the `User-agent` header

<p align="center">
  <img src="extras/police.png" width="50%" />
</p>


## Expected result

```
D/OkHttp: --> GET https://example.com/api/v1/users
D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; PIXEL3 XL)
D/OkHttp: Accept: application/json
D/OkHttp: Authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxZmM2MFmODQ
D/OkHttp: Host: example.com
D/OkHttp: Connection: Keep-Alive
D/OkHttp: Accept-Encoding: gzip
D/OkHttp: User-Agent: okhttp/3.12.0
D/OkHttp: --> END GET
D/OkHttp: <-- 200 https://example.com/api/v1/users (453ms)
```

Important part:

> User-Agent: **DeliveryApp/1.0.0 (Android Pie 9.0; PIXEL3 XL)**

## Usage:

```kotlin
val userAgent = UserAgentForAndroid(
    appName = context.getString(R.string.app_name), // DeliveryApp
    appVersion = BuildConfig.VERSION_NAME           // 1.0.0
)
```

### Just add a logging interceptor

```kotlin
val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.HEADERS
}

val okHttpClient = OkHttpClient.Builder()
    .followRedirects(true)
    .addInterceptor(loggingInterceptor)
    .addInterceptor(userAgent)
    .build()
```

### Add the `OkHttpClient` to your retrofit builder

```kotlin
val retrofit = Retrofit.Builder()
  .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
  .addConverterFactory(GsonConverterFactory.create())
  .client(okHttpClient)
  .baseUrl(BASE_URL)
  .build()
```

# Install
Just add the following dependency in your app's `build.gradle`

```groovy
allprojects { repositories { maven { url 'https://jitpack.io' } }}
```

```groovy
dependencies {
    implementation 'com.github.cesarferreira:UserAgentForAndroid:0.1.0'
}
```


## Created by
[Cesar Ferreira](https://cesarferreira.com)

## License
MIT © [Cesar Ferreira](http://cesarferreira.com)