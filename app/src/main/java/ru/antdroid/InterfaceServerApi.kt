package ru.antdroid

import retrofit2.Call
import retrofit2.http.GET
import ru.antdroid.data.DataCurrency

interface InterfaceServerApi {
    @GET("stocks.json")
    fun getCurrency(): Call<DataCurrency>
}