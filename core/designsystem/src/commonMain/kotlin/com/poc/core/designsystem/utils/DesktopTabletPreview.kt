package com.poc.core.designsystem.utils

import org.jetbrains.compose.ui.tooling.preview.Preview

@Target(
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.SOURCE)
@Preview(
    name = "DesktopTablet",
    widthDp = 1024,
    heightDp = 768
)
annotation class DesktopTabletPreview