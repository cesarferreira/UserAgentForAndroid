# UserAgentForAndroid

> Identify important app information on the `User-agent` header


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
val userAgent = UserAgentForAndroid(appName, appVersion)


OkHttpClient.Builder()
    .followRedirects(true)
    .addInterceptor(userAgent)
    .build()
```