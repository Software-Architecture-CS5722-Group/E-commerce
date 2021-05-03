package com.groupjn.productservice.api.command

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class AddProductCommand (
        @TargetAggregateIdentifier
        val id: String,
        val price: Double,
        val stock:Int,
        val description: String
)

data class  UpdateStockCommand(
        @TargetAggregateIdentifier
        val id: String,
        val stock: Int
)
