package com.example

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val LivRed = Color(0xFFE53945)
private val LivNavy = Color(0xFF101A29)
private val LivPanel = Color(0xFF1B293A)
private val LivMuted = Color(0xFF9BAABD)

@Composable
fun NativeHomeScreenNew() {
    var role by remember { mutableStateOf("اختيار نوع الاستخدام") }
    var menuOpen by remember { mutableStateOf(false) }
    var dark by remember { mutableStateOf(true) }
    var tab by remember { mutableIntStateOf(0) }
    var addType by remember { mutableStateOf("offer") }

    val bg = if (dark) Color(0xFF0C1420) else Color(0xFFF3F5F8)
    val panel = if (dark) LivPanel else Color.White
    val fg = if (dark) Color.White else LivNavy
    val muted = if (dark) LivMuted else Color(0xFF657386)

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            containerColor = bg,
            bottomBar = {
                NavigationBar(
                    containerColor = panel,
                    contentColor = fg,
                    tonalElevation = 8.dp
                ) {
                    val labels = listOf("الرئيسية", "العروض", "الخريطة", "إضافة", "السائقون")
                    val icons = listOf("⌂", "٪", "⌖", "+", "♙")
                    labels.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = tab == index,
                            onClick = { tab = index },
                            icon = {
                                Text(
                                    icons[index],
                                    fontSize = if (index == tab) 25.sp else 22.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            label = { Text(label, fontSize = 10.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = LivRed,
                                selectedTextColor = LivRed,
                                indicatorColor = if (dark) Color(0xFF3A2530) else Color(0xFFFFE6E8),
                                unselectedIconColor = muted,
                                unselectedTextColor = muted
                            )
                        )
                    }
                }
            }
        ) { padding ->
            if (tab == 3) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    AddScreen(
                        dark = dark,
                        onChooseOffer = { addType = "offer" },
                        onChooseDriver = { addType = "driver" }
                    )
                }
            } else {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .verticalScroll(rememberScrollState())
                ) {
                    // Header
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(LivNavy)
                            .padding(horizontal = 18.dp, vertical = 17.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(Modifier.weight(1f)) {
                            Text(
                                "LIVLINK",
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold,
                                letterSpacing = 1.5.sp
                            )
                            Text(
                                "ليفرور المغرب",
                                color = Color(0xFFFF6973),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                "منصة التوصيل بجميع مدن المملكة",
                                color = Color(0xFFCBD5E1),
                                fontSize = 10.sp
                            )
                        }
                        Box(
                            Modifier
                                .size(48.dp)
                                .background(Color(0xFF263548), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("🇲🇦", fontSize = 27.sp)
                        }
                        Spacer(Modifier.width(8.dp))
                        TextButton(onClick = { dark = !dark }) {
                            Text(if (dark) "☀" else "☾", fontSize = 23.sp, color = Color.White)
                        }
                    }

                    Column(
                        Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    ) {
                        Spacer(Modifier.height(22.dp))

                        Text(
                            "مرحبا بك في LIVLINK 👋",
                            color = fg,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(5.dp))
                        Text(
                            "خدمات التوصيل في جميع أنحاء المغرب",
                            color = muted,
                            fontSize = 13.sp
                        )

                        Spacer(Modifier.height(18.dp))

                        // Main callout
                        Card(
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(containerColor = LivRed),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                Modifier.fillMaxWidth().padding(18.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        "التوصيل ولى أسهل!",
                                        color = Color.White,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        "تواصل مع المحلات والسائقين من مكان واحد",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        lineHeight = 19.sp
                                    )
                                    Spacer(Modifier.height(12.dp))
                                    Text(
                                        "اكتشف الخدمات  ←",
                                        color = LivRed,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .background(Color.White, RoundedCornerShape(10.dp))
                                            .clickable { tab = 2 }
                                            .padding(horizontal = 13.dp, vertical = 9.dp)
                                    )
                                }
                                Spacer(Modifier.width(8.dp))
                                Text("🛵", fontSize = 48.sp)
                            }
                        }

                        Spacer(Modifier.height(18.dp))

                        // Role selector
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = panel)
                        ) {
                            Column(Modifier.fillMaxWidth().padding(14.dp)) {
                                Text(
                                    "كيفاش بغيتي تستعمل LIVLINK؟",
                                    color = fg,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(Modifier.height(9.dp))
                                Box {
                                    OutlinedButton(
                                        onClick = { menuOpen = true },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(12.dp)
                                    ) {
                                        Text(role, color = fg)
                                        Spacer(Modifier.weight(1f))
                                        Text("⌄", color = LivRed, fontSize = 20.sp)
                                    }
                                    DropdownMenu(
                                        expanded = menuOpen,
                                        onDismissRequest = { menuOpen = false }
                                    ) {
                                        listOf("صاحب محل", "ليفرور").forEach { item ->
                                            DropdownMenuItem(
                                                text = { Text(item) },
                                                onClick = {
                                                    role = item
                                                    menuOpen = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(Modifier.height(23.dp))

                        Text(
                            "اكتشف خدماتنا",
                            color = fg,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "كل ما تحتاجه للتوصيل في مكان واحد",
                            color = muted,
                            fontSize = 12.sp
                        )
                        Spacer(Modifier.height(13.dp))

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                            ServiceTile(
                                "📦", "طلبات التوصيل", "تتبّع الطلبات",
                                panel, fg, muted, Modifier.weight(1f)
                            ) { tab = 0 }
                            ServiceTile(
                                "🗺️", "مدن وأحياء المغرب", "تصفّح المناطق",
                                panel, fg, muted, Modifier.weight(1f)
                            ) { tab = 2 }
                        }
                        Spacer(Modifier.height(11.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                            ServiceTile(
                                "🛵", "انضم كليفرور", "ابدأ نشاطك",
                                panel, fg, muted, Modifier.weight(1f)
                            ) { role = "ليفرور" }
                            ServiceTile(
                                "🏪", "أصحاب المحلات", "وسّع خدماتك",
                                panel, fg, muted, Modifier.weight(1f)
                            ) { role = "صاحب محل" }
                        }

                        Spacer(Modifier.height(23.dp))

                        Text(
                            "الوصول السريع",
                            color = fg,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(Modifier.height(11.dp))

                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = panel)
                        ) {
                            Column(Modifier.fillMaxWidth().padding(15.dp)) {
                                QuickLink("٪", "قسم العروض", "اكتشف العروض المتاحة", fg, muted) {
                                    tab = 1
                                }
                                HorizontalDivider(color = muted.copy(alpha = 0.2f))
                                QuickLink("＋", "إضافة طلب", "ابدأ طلب توصيل جديد", fg, muted) {
                                    tab = 3
                                }
                                HorizontalDivider(color = muted.copy(alpha = 0.2f))
                                QuickLink("♙", "السائقون", "قسم السائقين", fg, muted) {
                                    tab = 4
                                }
                            }
                        }

                        Spacer(Modifier.height(18.dp))
                        Text(
                            when (tab) {
                                1 -> "قسم العروض — الربط الفعلي ما زال قيد التطوير"
                                2 -> "الخريطة — الربط الفعلي ما زال قيد التطوير"
                                3 -> "إضافة طلب — الوظيفة ما زالت قيد التطوير"
                                4 -> "قسم السائقين — الوظائف قيد التطوير"
                                else -> "LIVLINK • التوصيل عبر مدن المغرب"
                            },
                            color = muted,
                            fontSize = 11.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ServiceTile(
    emoji: String,
    title: String,
    subtitle: String,
    panel: Color,
    fg: Color,
    muted: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(145.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = panel),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            Modifier.fillMaxSize().padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(emoji, fontSize = 29.sp)
            Spacer(Modifier.height(8.dp))
            Text(title, color = fg, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(subtitle, color = muted, fontSize = 11.sp)
        }
    }
}

@Composable
private fun QuickLink(
    icon: String,
    title: String,
    subtitle: String,
    fg: Color,
    muted: Color,
    onClick: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier.size(42.dp).background(LivRed.copy(alpha = 0.13f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, color = LivRed, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = fg, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = muted, fontSize = 11.sp)
        }
        Text("‹", color = muted, fontSize = 23.sp)
    }
}
