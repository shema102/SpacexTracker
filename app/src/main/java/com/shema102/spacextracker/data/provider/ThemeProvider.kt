package com.shema102.spacextracker.data.provider


interface ThemeProvider {
    fun getThemeFromPreferences(): Int
    fun getTheme(selectedTheme: String): Int
}