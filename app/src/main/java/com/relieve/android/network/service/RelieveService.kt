package com.relieve.android.network.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.relieve.android.BuildConfig
import com.relieve.android.network.Bakau
import com.relieve.android.network.data.relieve.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RelieveService {

    @GET("user/profile")
    fun getProfile() : Observable<ApiResponse<UserData>>

    @POST("login")
    fun login(@Body bodyLogin: Login) : Observable<ApiResponse<UserToken>>

    @POST("google/callback")
    fun googleLogin(@Body bodyData: GoogleData) : Observable<ApiResponse<UserToken>>

    @POST("register")
    fun register(@Body bodyUserData: UserData) : Observable<ApiResponse<UserToken>>

    companion object {
        fun create(auth: String? = null): RelieveService {

            val okHttpClient = OkHttpClient().newBuilder().apply {
                addInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder().apply {
                        addHeader("Content-Type", "application/json")
                        addHeader(
                            Bakau.HEADER_SECRET,
                            Bakau.SECRET
                        )
                        addHeader(
                            Bakau.HEADER_AUTH,
                            auth ?: ""
                        )
                    }.build()

                    return@addInterceptor chain.proceed(newRequest)
                }

                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                }

                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(StethoInterceptor())
                    addInterceptor(loggingInterceptor)
                }
            }.build()

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