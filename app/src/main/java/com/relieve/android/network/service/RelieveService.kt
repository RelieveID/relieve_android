package com.relieve.android.network.service

import com.relieve.android.BuildConfig
import com.relieve.android.network.Bakau
import com.relieve.android.network.data.relieve.ApiResponse
import com.relieve.android.network.data.relieve.Login
import com.relieve.android.network.data.relieve.Register
import com.relieve.android.network.data.relieve.UserToken
import io.reactivex.Observable
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

    @POST("register")
    fun register(@Body bodyRegister: Register) : Observable<ApiResponse<UserToken>>

    companion object {
        fun create(): RelieveService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val okHttpClient = OkHttpClient().newBuilder()
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val newRequest = request.newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .addHeader(
                                    Bakau.HEADER_SECRET,
                                    Bakau.SECRET
                                )
                                .build()

                        return@addInterceptor chain.proceed(newRequest)

                    }
                    .addInterceptor(loggingInterceptor)
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Bakau.URL)
                    .client(okHttpClient)
                    .build()

            return retrofit.create(RelieveService::class.java)
        }
    }
}