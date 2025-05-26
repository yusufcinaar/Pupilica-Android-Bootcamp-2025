package com.example.foodorderapp.di

import com.example.foodorderapp.presentation.screens.cart.CartViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    
    @Provides
    @Singleton
    fun provideCartViewModel(): CartViewModel {
        return CartViewModel()
    }
}
