package com.example.foodorderapp.presentation.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodorderapp.domain.model.Category
import com.example.foodorderapp.domain.model.sampleCategories
import com.example.foodorderapp.presentation.screens.cart.CartViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    onCategorySelected: (Category) -> Unit,
    onNavigateToCart: () -> Unit,
    viewModel: CategoriesViewModel = hiltViewModel(),
    cartViewModel: CartViewModel
) {
    val orangeGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFA500), // Turuncu
            Color(0xFFFFD700)  // Altın sarısı
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Yemek Kategorileri",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA500), // Turuncu
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = onNavigateToCart) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Sepete git",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFFFFAF0) // Fil dişi beyazı
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.padding(padding)
        ) {
            items(sampleCategories) { category ->
                CategoryCard(
                    category = category,
                    onCategorySelected = onCategorySelected
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: Category,
    onCategorySelected: (Category) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFA500), // Turuncu
                    Color(0xFFFFD700)  // Altın sarısı
                )
            ))
            .clickable { onCategorySelected(category) }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = category.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.description,
                fontSize = 14.sp,
                maxLines = 2,
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center
            )
        }
    }
}
