package ru.antdroid

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRequest private constructor() {

    companion object {

        private var retrofit: Retrofit? = null
        private var requestApi: InterfaceServerApi? = null

        private fun getRetrofit(): Retrofit =
            if(retrofit == null){
                Retrofit.Builder()
                    .baseUrl("http://phisix-api3.appspot.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }else retrofit!!

        fun getRequestApi():InterfaceServerApi =
            if (requestApi == null)
                getRetrofit().create(InterfaceServerApi::class.java)
            else
                requestApi!!

    }
}