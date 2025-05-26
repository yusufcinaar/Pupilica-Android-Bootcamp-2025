package com.example.foodorderapp.domain.model

data class Category(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String
)

// Örnek kategoriler
val sampleCategories = listOf(
    Category("1", "Ana Yemekler", "url_to_main_dishes", "Geleneksel Türk mutfağından ana yemekler"),
    Category("2", "Çorbalar", "url_to_soups", "Sıcak çorba çeşitleri"),
    Category("3", "Makarnalar ve Pizzalar", "url_to_pasta", "İtalyan mutfağından lezzetler"),
    Category("4", "Salatalar ve Sebze Yemekleri", "url_to_salads", "Taze ve sağlıklı seçenekler"),
    Category("5", "Tatlılar", "url_to_desserts", "Baklava ve diğer tatlı çeşitleri"),
    Category("6", "İçecekler", "url_to_drinks", "Ayran, su ve diğer içecekler")
)
