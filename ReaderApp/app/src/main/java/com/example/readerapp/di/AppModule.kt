package com.example.readerapp.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /*
     Retrofit 의존성
    */
    @Singleton
    @Provides
    // Loging에 사용할 OkHttpClient를 주입하는 provideOkHttpClient 메소드 정의
    fun provideOkHttpClient() : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    // Retrofit 객체를 주입하기 위한 provideRetrofit 메소드
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder() // 빌더 패턴을 통해 retrofit 객체를 만든다.
            .addConverterFactory(GsonConverterFactory.create()) // DTO 변환에 사용할 GsonConverterFactory를 JSON Converter로 설정 만약 Moshi을 쓰면 MoshiConverterFactory쓰면 됨.
            .client(okHttpClient) //클라이언트 속성에 okHTTP interceptor를 넣어줘서 로그캣에서 패킷내용을 모니터링 (okhttp가 apllication과 서버 사이에서 data를 interceptor할 수 있는 기능이 있기 때문)
            .baseUrl(BASE_URL) // 베이스 URL 전달
            .build() // 객체 생성
    }

    @Singleton
    @Provides
    // api서비스 객체를 주입하기 위한 provideApiService 메소드
    fun provideTest1ApiService(retrofit : Retrofit): Test1Api {
        // Retrofit의 create메소드로 Test1Api의 인스턴스 생성
        return retrofit.create(Test1Api::class.java)
    }
}