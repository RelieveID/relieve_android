package com.relieve.android.network

import android.os.Build
import com.relieve.android.BuildConfig
import com.relieve.android.network.data.relieve.ApiResponse
import com.relieve.android.network.data.relieve.Login
import com.relieve.android.network.data.relieve.UserToken
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface RelieveService {

    @POST("login")
    fun login(@Body bodyLogin: Login) : Observable<ApiResponse<UserToken>>

    companion object {
        const val RELIEVE_BASE_URL = "http://35.240.181.2:3001/"
        const val HEADER_SECRET = "secret"
        const val SECRET = "YTvZ3kG9X9Vz6MLHdNIwnaTefjs2Udph"
        fun create(): RelieveService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val newRequest = request.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader(HEADER_SECRET, SECRET)
                                .build()

                        return@addInterceptor chain.proceed(newRequest)

                    }
                    .addInterceptor(loggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(RELIEVE_BASE_URL)
                    .client(okHttpClient)
                    .build()

            return retrofit.create(RelieveService::class.java)
        }
    }
}