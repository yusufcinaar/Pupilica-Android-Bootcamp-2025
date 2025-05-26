package com.example.foodorderapp.presentation.screens.foodlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.foodorderapp.data.model.YemekModel
import com.example.foodorderapp.presentation.components.FoodDetailDialog
import com.example.foodorderapp.presentation.screens.cart.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodListScreen(
    categoryId: String,
    onNavigateBack: () -> Unit,
    onNavigateToCart: () -> Unit,
    viewModel: FoodListViewModel = hiltViewModel(),
    cartViewModel: CartViewModel
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedFood by remember { mutableStateOf<YemekModel?>(null) }

    LaunchedEffect(categoryId) {
        viewModel.loadFoodsByCategory(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = viewModel.categoryName,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Geri",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = onNavigateToCart) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Sepete git",
                                tint = Color.White
                            )
                        }
                        if (cartViewModel.cartItems.isNotEmpty()) {
                            Badge(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .offset(x = (-8).dp, y = 8.dp),
                                containerColor = Color.White
                            ) {
                                Text(
                                    text = cartViewModel.cartItems.size.toString(),
                                    color = Color(0xFFFFA500)
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA500),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFFFFAF0)
    ) { padding ->
        if (viewModel.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (viewModel.foods.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Bu kategoride yemek bulunamadı")
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.padding(padding)
            ) {
                items(viewModel.foods) { food ->
                    FoodCard(
                        food = food,
                        onClick = {
                            selectedFood = food
                            showDialog = true
                        }
                    )
                }
            }
        }
    }

    if (showDialog && selectedFood != null) {
        FoodDetailDialog(
            food = selectedFood!!,
            onDismiss = { showDialog = false },
            onAddToCart = { food, quantity, note ->
                cartViewModel.addToCart(food, quantity, note)
                showDialog = false
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodCard(
    food: YemekModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            ) {
                AsyncImage(
                    model = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemekResimAdi}",
                    contentDescription = food.yemekAdi,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = food.yemekAdi,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "₺${food.yemekFiyat}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}
