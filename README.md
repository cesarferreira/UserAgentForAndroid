# UserAgentForAndroid
> Identify important information on the `User-agent` header
> User-Agent: **DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)**

```
D/OkHttp: --> GET https://example.com/api/v1/users
D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)
D/OkHttp: Accept: application/json
D/OkHttp: Authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjkxZmM2MFmODQ
D/OkHttp: Host: example.com
D/OkHttp: Connection: Keep-Alive
D/OkHttp: Accept-Encoding: gzip
D/OkHttp: User-Agent: okhttp/3.12.0
D/OkHttp: If-Modified-Since: Mon, 21 Jan 2019 16:53:15 GMT
D/OkHttp: --> END GET
D/OkHttp: <-- 200 https://example.com/api/v1/users (453ms)
```

Important part:

> D/OkHttp: User-Agent: DeliveryApp/1.0.0 (Android Pie 9.0; ONEPLUS A6000)
