package ru.antdroid.data

data class DataCurrency(
    var stock: List<Stock>,
    var asOf: String = ""
)