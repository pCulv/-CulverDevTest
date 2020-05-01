package dev.codephoenix.phildevtest

import android.text.TextUtils
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private var httpClient: OkHttpClient
    private var builder: Retrofit.Builder?
    private var retrofit: Retrofit?

    init {
        httpClient = OkHttpClient().newBuilder().build()
        builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
        val localBuilder: Retrofit.Builder? = builder
        retrofit = localBuilder?.build()
    }

    fun <S> createService(serviceClass: Class<S>, apiKey: String?): S? {

        if (apiKey != null) {
            if (!TextUtils.isEmpty(apiKey)) {
                val authenticationInterceptor = AuthInterceptor(apiKey)

                if (!httpClient.interceptors()?.contains(authenticationInterceptor)!!) {
                    httpClient = httpClient.newBuilder()!!
                        .addInterceptor(authenticationInterceptor)
                        .build()

                    builder = retrofit?.newBuilder()

                    retrofit = builder?.client(httpClient)?.build()
                }
            }
        }

        return retrofit?.create(serviceClass)
    }

    companion object {
        const val BASE_URL = "https://newsapi.org"

    }
}