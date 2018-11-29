package com.relieve.android.network.service

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.relieve.android.BuildConfig
import com.relieve.android.network.Camar
import com.relieve.android.network.data.camar.ApiResponse
import com.relieve.android.network.data.camar.Device
import com.relieve.android.network.data.camar.Event
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CamarService {

    @POST("device")
    fun postNewDevice(@Body device: Device): Observable<ApiResponse<Device>>

    @GET("earthquakeList")
    fun postNewDevice(@Query("limit") limit: Int, @Query("page") page: Int): Observable<ApiResponse<Event>>

    companion object {
        fun create(): CamarService {

            val okHttpClient = OkHttpClient().newBuilder().apply {
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
                .baseUrl(Camar.URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(CamarService::class.java)
        }
    }
}