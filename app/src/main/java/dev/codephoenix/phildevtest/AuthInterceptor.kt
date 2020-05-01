package dev.codephoenix.phildevtest

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(var apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        if (request.header(AUTHORIZATION) == null) {
            //needs credentials
            requestBuilder.addHeader(AUTHORIZATION, apiKey)
        }
        return chain.proceed(requestBuilder.build())
    }

    companion object {
        const val AUTHORIZATION = "Authorization"
    }
}