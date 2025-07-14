package com.solux.dorandoran.core_ui.component

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val label: String,
)