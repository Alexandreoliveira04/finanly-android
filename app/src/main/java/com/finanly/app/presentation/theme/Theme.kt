package com.finanly.app.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme

private val FinanlyDarkColorScheme = darkColorScheme(
    primary = FinanlyGreen80,
    secondary = FinanlyGreenGrey80,
    tertiary = FinanlyGold80,
    background = FinanlyBackgroundDark,
    surface = FinanlySurfaceDark
)

private val FinanlyLightColorScheme = lightColorScheme(
    primary = FinanlyGreen40,
    secondary = FinanlyGreenGrey40,
    tertiary = FinanlyGold40,
    background = FinanlyBackgroundLight,
    surface = FinanlySurfaceLight
)

/**
 * Finanly's own brand identity. Dynamic color is intentionally not used here,
 * so the app looks the same regardless of the device wallpaper.
 */
@Composable
fun FinanlyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) FinanlyDarkColorScheme else FinanlyLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FinanlyTypography,
        shapes = FinanlyShapes,
        content = content
    )
}