package ir.net_box.test.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val requestBuilder = originalRequest
            .newBuilder().addHeader(
                "SESSION-KEY",
                "43581c49f795564442a066b11e95bcdc7dba9ac6062178d9c2fb65acce4ba761"
            )

        return chain.proceed(requestBuilder.build())
    }
}
