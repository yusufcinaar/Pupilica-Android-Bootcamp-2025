package com.example.foodorderapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.foodorderapp.data.model.YemekModel

@Composable
fun FoodDetailDialog(
    food: YemekModel,
    onDismiss: () -> Unit,
    onAddToCart: (YemekModel, Int, String) -> Unit
) {
    var quantity by remember { mutableStateOf("1") }
    var note by remember { mutableStateOf("") }
    
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "http://kasimadalan.pe.hu/yemekler/resimler/${food.yemekResimAdi}",
                    contentDescription = food.yemekAdi,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = food.yemekAdi,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${food.yemekFiyat} ₺",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { 
                        if (it.isEmpty() || (it.toIntOrNull() != null && it.toInt() > 0)) {
                            quantity = it
                        }
                    },
                    label = { Text("Adet") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Not") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("İptal")
                    }
                    
                    Button(
                        onClick = {
                            quantity.toIntOrNull()?.let { qty ->
                                onAddToCart(food, qty, note.trim())
                            }
                        },
                        enabled = quantity.isNotEmpty()
                    ) {
                        Text("Sepete Ekle")
                    }
                }
            }
        }
    }
}
