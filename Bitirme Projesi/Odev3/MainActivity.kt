package com.example.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Dark mode kontrolü
        setupDarkMode()
        
        // Dil ayarı
        setupLocale()
        
        // UI elemanlarının kurulumu
        setupUI()
    }
    
    private fun setupDarkMode() {
        // Sistem ayarlarına göre otomatik dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
    
    private fun setupLocale() {
        // Dil ayarı (örnek olarak Türkçe)
        val locale = Locale("tr")
        Locale.setDefault(locale)
        
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
    
    private fun setupUI() {
        // Toolbar ayarları
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        
        // RecyclerView ayarları
        // ... RecyclerView adapter ve layout manager ayarları
    }
}
