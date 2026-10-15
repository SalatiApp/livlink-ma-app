package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NativeHomeScreenNew() {
    var role by remember { mutableStateOf("اختر نوع الاستخدام") }
    var expanded by remember { mutableStateOf(false) }
    var tab by remember { mutableIntStateOf(0) }
    var dark by remember { mutableStateOf(true) }

    val bg = if (dark) Color(0xFF0F1722) else Color(0xFFF4F6F8)
    val panel = if (dark) Color(0xFF1B2635) else Color.White
    val fg = if (dark) Color.White else Color(0xFF0F1722)
    val red = Color(0xFFE53945)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            containerColor = bg,
            bottomBar = {
                NavigationBar(containerColor = panel) {
                    val labels = listOf("الرئيسية", "العروض", "الخريطة", "إضافة", "السائقين")
                    val symbols = listOf("⌂", "٪", "⌖", "+", "♙")
                    labels.forEachIndexed { i, label ->
                        NavigationBarItem(
                            selected = tab == i,
                            onClick = { tab = i },
                            icon = { Text(symbols[i], fontSize = 20.sp) },
                            label = { Text(label, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = red,
                                selectedTextColor = red,
                                indicatorColor = if (dark) Color(0xFF3A2530) else Color(0xFFFFE5E7),
                                unselectedIconColor = fg,
                                unselectedTextColor = fg
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                Modifier.fillMaxSize().padding(padding)
            ) {
                Row(
                    Modifier.fillMaxWidth().background(Color(0xFF0F1722)).padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.weight(1f)) {
                        Text("ليفرور المغرب", color = red, fontSize = 23.sp)
                        Text("منصة التوصيل بجميع مدن المملكة", color = Color.White, fontSize = 12.sp)
                    }
                    Text("🇲🇦", fontSize = 28.sp)
                    TextButton(onClick = { dark = !dark }) {
                        Text(if (dark) "☀" else "☾", color = Color.White, fontSize = 22.sp)
                    }
                }

                Column(Modifier.fillMaxSize().padding(16.dp)) {
                    Text("مرحبا بك في LIVLINK", color = fg, fontSize = 22.sp)
                    Spacer(Modifier.height(6.dp))
                    Text("خدمات التوصيل في جميع أنحاء المغرب", color = fg)
                    Spacer(Modifier.height(16.dp))

                    Box {
                        OutlinedButton(
                            onClick = { expanded = true },
                            modifier = Modifier.fillMaxWidth()
                        ) { Text(role, color = fg) }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            listOf("صاحب محل", "ليفرور").forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(item) },
                                    onClick = { role = item; expanded = false }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                    Text("اكتشف خدماتنا", color = fg, fontSize = 18.sp)
                    Spacer(Modifier.height(10.dp))

                    val cards = listOf(
                        "📦" to "طلبات التوصيل",
                        "🗺" to "مدن وأحياء المغرب",
                        "🛵" to "انضم كليفرور",
                        "🏪" to "خدمات أصحاب المحلات"
                    )

                    cards.forEachIndexed { i, item ->
                        Card(
                            Modifier.fillMaxWidth().padding(bottom = 10.dp)
                                .clickable { tab = listOf(0, 2, 4, 1)[i] },
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = panel)
                        ) {
                            Row(
                                Modifier.fillMaxWidth().padding(18.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(item.first, fontSize = 26.sp)
                                Spacer(Modifier.width(14.dp))
                                Text(item.second, color = fg, fontSize = 15.sp)
                            }
                        }
                    }

                    Text(
                        when (tab) {
                            1 -> "قسم العروض"
                            2 -> "الخريطة: الربط الفعلي مازال ما تدارش"
                            3 -> "إضافة طلب: الوظيفة مازال ما تربطاتش"
                            4 -> "قسم السائقين"
                            else -> "الرئيسية"
                        },
                        color = fg
                    )
                }
            }
        }
    }
}
