package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = RoyalBlueMain,
    onPrimary = Color.White,
    primaryContainer = SoftBannerBlue,
    onPrimaryContainer = RoyalBlueDark,
    secondary = CobaltLight,
    onSecondary = Color.White,
    tertiary = TechAqua,
    onTertiary = Color.White,
    background = AppBackground,
    onBackground = DeepSlate,
    surface = CardSurfaceWhite,
    onSurface = DeepSlate,
    surfaceVariant = SoftBannerBlue,
    onSurfaceVariant = DeepSlate,
    outline = GlassyOutlineBlue
)

// Define DarkColorScheme for fallback, but keep it highly polished too
private val DarkColorScheme = darkColorScheme(
    primary = CobaltLight,
    onPrimary = DeepSlate,
    primaryContainer = RoyalBlueDark,
    onPrimaryContainer = Color.White,
    secondary = RoyalBlueMain,
    onSecondary = Color.White,
    tertiary = TechAqua,
    onTertiary = DeepSlate,
    background = DeepSlate,
    onBackground = Color.White,
    surface = Color(0xFF1E293B),
    onSurface = Color.White,
    surfaceVariant = Color(0xFF0F172A),
    onSurfaceVariant = Color.White,
    outline = GlassyOutlineBlue
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // For corporate showcase, we align colors strictly with user specifications (no dynamic overrule)
    content: @Composable () -> Unit,
) {
    // The user requested a specific "Light theme" and "White and blue color palette",
    // so we default to LightColorScheme. If the system is explicitly in darkTheme,
    // we can still honor it elegantly, but default light is the brand key.
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
