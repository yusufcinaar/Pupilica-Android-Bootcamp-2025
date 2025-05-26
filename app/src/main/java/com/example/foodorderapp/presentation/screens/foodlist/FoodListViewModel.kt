package com.example.foodorderapp.presentation.screens.foodlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodorderapp.data.model.YemekModel
import com.example.foodorderapp.data.repository.FoodRepository
import com.example.foodorderapp.domain.model.Category
import com.example.foodorderapp.domain.model.sampleCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    var foods by mutableStateOf<List<YemekModel>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var categoryName by mutableStateOf("")
        private set

    fun loadFoodsByCategory(categoryId: String) {
        isLoading = true
        viewModelScope.launch {
            try {
                val category = sampleCategories.find { it.id == categoryId }
                categoryName = category?.name ?: ""
                foods = foodRepository.getFoodsByCategory(categoryId)
            } catch (e: Exception) {
                // TODO: Handle error
                foods = emptyList()
            } finally {
                isLoading = false
            }
        }
    }
}
