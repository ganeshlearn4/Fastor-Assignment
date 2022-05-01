package com.fastorassignment.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.fastorassignment.BuildConfig
import com.fastorassignment.data.Constants
import com.fastorassignment.data.network.Api
import com.fastorassignment.data.repository.Repository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): Context {
        return context
    }

    @Named("ChuckerInterceptor")
    @Singleton
    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        val chuckerInterceptor = ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()

        return chuckerInterceptor
    }

    // Provides Arraylist of Interceptor
    @Singleton
    @Provides
    fun provideInterceptors(@Named("ChuckerInterceptor") chuckerInterceptor: ChuckerInterceptor): ArrayList<Interceptor> {
        val interceptors: ArrayList<Interceptor> = ArrayList()

        // Adding Logging Interceptor
        val level: HttpLoggingInterceptor.Level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val loggingInterceptor: Interceptor = HttpLoggingInterceptor().setLevel(level)
        interceptors.add(loggingInterceptor)

        // Adding Chucker Interceptor
        interceptors.add(chuckerInterceptor)

        return interceptors
    }

    // Provides OkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder().followRedirects(false)
        for (interceptor in interceptors) {
            builder.addInterceptor(interceptor)
        }
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.readTimeout(120, TimeUnit.SECONDS)
        return builder.build()
    }

    // Provides Retrofit
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.ApiUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    // Provides Api
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: Api): Repository {
        return Repository(api)
    }

    @Singleton
    @Provides
    fun provideGSON(): Gson {
        return Gson()
    }
}