package com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.ui.theme

import android.os.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.stoyanvuchev.systemuibarstweaker.ProvideSystemUIBarsTweaker
import com.stoyanvuchev.systemuibarstweaker.SystemUIBarsConfiguration
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.ColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.color_scheme.LocalColorScheme
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.LocalThemeMode
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.ThemeMode
import com.stoyanvuchev.systemuibarstweaker.demo.presentation.common.theme_mode.isSystemInDarkThemeMode
import com.stoyanvuchev.systemuibarstweaker.rememberSystemUIBarsTweaker

private val lightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
)


private val darkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
)

@Composable
fun ApplicationTheme(
    themeMode: ThemeMode = LocalThemeMode.current,
    colorScheme: ColorScheme = LocalColorScheme.current,
    systemUIBarsConfiguration: SystemUIBarsConfiguration = SystemUIBarsConfiguration.default(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalThemeMode provides themeMode,
        LocalColorScheme provides colorScheme
    ) {

        val darkTheme = isSystemInDarkThemeMode()
        val localColorScheme = LocalColorScheme.current

        val colors = when {

            localColorScheme == ColorScheme.Dynamic
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context)
                else dynamicLightColorScheme(context)
            }

            localColorScheme == ColorScheme.Default && darkTheme -> darkColorScheme
            localColorScheme == ColorScheme.Default && !darkTheme -> lightColorScheme

            localColorScheme == ColorScheme.Lavender && darkTheme -> darkColorScheme()
            else -> lightColorScheme()

        }

        val systemUIBarsTweaker = rememberSystemUIBarsTweaker(
            initialConfiguration = remember { systemUIBarsConfiguration }
        )

        DisposableEffect(
            systemUIBarsTweaker,
            systemUIBarsConfiguration,
            darkTheme
        ) {

            systemUIBarsTweaker.tweakSystemUIBarsConfiguration(
                systemUIBarsConfiguration = systemUIBarsConfiguration.copy(
                    statusBarStyle = systemUIBarsConfiguration.statusBarStyle.copy(
                        darkIcons = if (systemUIBarsConfiguration.statusBarStyle.color.value == Color.Unspecified.value
                        ) !darkTheme else false
                    ),
                    navigationBarStyle = systemUIBarsConfiguration.navigationBarStyle.copy(
                        darkIcons = if (systemUIBarsConfiguration.navigationBarStyle.color.value == Color.Unspecified.value
                        ) !darkTheme else false
                    )
                )
            )

            onDispose {}

        }

        ProvideSystemUIBarsTweaker(systemUIBarsTweaker = systemUIBarsTweaker) {

            MaterialTheme(
                colorScheme = colors,
                typography = Typography(),
                content = content
            )

        }

    }

}