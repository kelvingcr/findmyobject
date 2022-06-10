package br.com.kelvingcr.findmyobject.service.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {

        private lateinit var retrofit: Retrofit
        private val baseURL = "https://proxyapp.correios.com.br/v1/sro-rastro/"

       private fun getRetrofitInstance() : Retrofit {

            //Gerencia as comunicação com chamadas http
            val httpClient = OkHttpClient.Builder()

            if(!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }

        fun <S> createService(serviceClass: Class<S>) : S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}