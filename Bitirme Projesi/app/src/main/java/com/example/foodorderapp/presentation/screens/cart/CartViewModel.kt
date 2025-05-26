package com.example.foodorderapp.presentation.screens.cart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.foodorderapp.data.model.YemekModel
import com.example.foodorderapp.domain.model.OrderItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {
    var cartItems by mutableStateOf<List<OrderItem>>(emptyList())
        private set

    var totalAmount by mutableStateOf(0.0)
        private set

    fun addToCart(food: YemekModel, quantity: Int = 1, note: String = "") {
        val orderItem = OrderItem(
            id = food.yemekId,
            foodName = food.yemekAdi,
            price = food.yemekFiyat.toDouble(),
            quantity = quantity,
            note = note
        )
        
        val existingItem = cartItems.find { it.id == orderItem.id }
        cartItems = if (existingItem != null) {
            cartItems.map {
                if (it.id == orderItem.id) {
                    it.copy(quantity = it.quantity + orderItem.quantity)
                } else it
            }
        } else {
            cartItems + orderItem
        }
        updateTotalAmount()
    }

    fun removeFromCart(itemId: String) {
        cartItems = cartItems.filter { it.id != itemId }
        updateTotalAmount()
    }

    fun updateItemQuantity(itemId: String, quantity: Int) {
        if (quantity <= 0) {
            removeFromCart(itemId)
            return
        }
        cartItems = cartItems.map {
            if (it.id == itemId) it.copy(quantity = quantity)
            else it
        }
        updateTotalAmount()
    }

    fun clearCart() {
        cartItems = emptyList()
        totalAmount = 0.0
    }

    private fun updateTotalAmount() {
        totalAmount = cartItems.sumOf { it.price * it.quantity }
    }
}
