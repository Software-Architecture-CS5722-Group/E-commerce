package com.groupjn.productservice.api.event

data class ProductAddedEvent (
        val id: String,
        val price: Double,
        val stock:Int,
        val description: String
)

data class StockUpdatedEvent(
        val id:String,
        val stock: Int
)