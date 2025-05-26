package com.example.foodorderapp.domain.model

import java.util.UUID

enum class OrderStatus {
    BEKLEMEDE,
    HAZIRLANIYOR,
    YOLDA,
    TAMAMLANDI,
    IPTAL_EDILDI
}

data class OrderItem(
    val id: String,
    val foodName: String,
    val price: Double,
    val quantity: Int,
    val note: String = ""
)

data class Order(
    val id: String = UUID.randomUUID().toString(),
    val items: List<OrderItem>,
    val totalAmount: Double,
    val status: OrderStatus = OrderStatus.BEKLEMEDE,
    val address: String,
    val phoneNumber: String,
    val orderNote: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
