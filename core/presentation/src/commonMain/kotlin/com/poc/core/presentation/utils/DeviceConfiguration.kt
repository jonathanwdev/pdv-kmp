package com.poc.core.presentation.utils

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass


@Composable
fun currentDeviceConfiguration(): DeviceConfiguration {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    return DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
}


enum class DeviceConfiguration {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP;

    val isMobile: Boolean
        get() = this in listOf(MOBILE_LANDSCAPE, MOBILE_PORTRAIT)

    val isWideScreen: Boolean
        get() = this in listOf(TABLET_LANDSCAPE,DESKTOP)

    companion object {
        fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DeviceConfiguration {
            return with(windowSizeClass) {
                when {
                    minWidthDp < WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND &&
                            minHeightDp >= WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND -> MOBILE_PORTRAIT

                    minWidthDp >= WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND &&
                            minHeightDp < WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND -> MOBILE_LANDSCAPE

                    minWidthDp in WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND..WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND &&
                            minHeightDp >= WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND -> TABLET_PORTRAIT

                    minWidthDp >= WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND &&
                            minHeightDp in WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND..WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND -> TABLET_LANDSCAPE

                    else -> DESKTOP

                }
            }
        }

    }

}