package ru.antdroid.data

data class Stock(
    var name: String,
    var price: Price,
    var percentChange: Float,
    var volume: Int,
    var symbol: String
)