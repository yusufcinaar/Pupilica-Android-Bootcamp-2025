package com.example.foodorderapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodorderapp.presentation.screens.splash.SplashScreen
import com.example.foodorderapp.presentation.screens.categories.CategoriesScreen
import com.example.foodorderapp.presentation.screens.profile.ProfileScreen
import com.example.foodorderapp.presentation.screens.order.OrderScreen
import com.example.foodorderapp.presentation.screens.cart.CartScreen
import com.example.foodorderapp.presentation.screens.cart.CartViewModel
import com.example.foodorderapp.presentation.screens.foodlist.FoodListScreen
import com.example.foodorderapp.domain.model.OrderItem
import com.example.foodorderapp.domain.model.Category
import javax.inject.Inject

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Categories : Screen("categories")
    object Profile : Screen("profile")
    object Order : Screen("order")
    object Cart : Screen("cart")
    object FoodList : Screen("food_list/{categoryId}")

    companion object {
        fun createFoodListRoute(categoryId: String): String {
            return "food_list/$categoryId"
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
    modifier: Modifier = Modifier
) {
    val cartViewModel: CartViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Categories.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Categories.route) {
            CategoriesScreen(
                onCategorySelected = { category ->
                    navController.navigate(Screen.createFoodListRoute(category.id))
                },
                onNavigateToCart = {
                    navController.navigate(Screen.Cart.route)
                },
                cartViewModel = cartViewModel
            )
        }

        composable(
            route = Screen.FoodList.route,
            arguments = listOf(
                navArgument("categoryId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")
            FoodListScreen(
                categoryId = categoryId ?: "",
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToCart = {
                    navController.navigate(Screen.Cart.route)
                },
                cartViewModel = cartViewModel
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }

        composable(Screen.Cart.route) {
            CartScreen(
                onNavigateToOrder = {
                    navController.navigate(Screen.Order.route)
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
                viewModel = cartViewModel
            )
        }

        composable(Screen.Order.route) {
            OrderScreen(
                cartItems = cartViewModel.cartItems,
                onBackClick = {
                    navController.navigateUp()
                },
                onOrderSubmit = { address, phone, note ->
                    // TODO: Handle order submission
                    cartViewModel.clearCart()
                    navController.navigate(Screen.Categories.route) {
                        popUpTo(Screen.Categories.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
