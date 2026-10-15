package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NativeHomeScreen() {
    var role by remember { mutableStateOf("اختر نوع الاستخدام") }
    var expanded by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth().background(Color(0xFF0F1722)).padding(16.dp)) {
        Text("LIVLINK", color = Color(0xFFE53945), fontSize = 28.sp)
        Text("منصة التوصيل لجميع مدن المغرب", color = Color.White, fontSize = 13.sp)
        Spacer(Modifier.height(12.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(role)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text("صاحب محل") }, onClick = { role = "صاحب محل"; expanded = false })
                DropdownMenuItem(text = { Text("ليفرور") }, onClick = { role = "ليفرور"; expanded = false })
            }
        }
    }
}
