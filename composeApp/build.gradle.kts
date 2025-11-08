import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.convention.cmp.application)
    alias(libs.plugins.compose.hot.reload)
}

kotlin {

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(projects.core.presentation)
            implementation(projects.core.designsystem)
            implementation(projects.core.data)
            implementation(projects.core.database)
            implementation(projects.core.domain)

            implementation(projects.feature.auth)
            implementation(projects.feature.sale)

            implementation(libs.jetbrains.compose.navigation)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.jetbrains.compose.viewmodel)
            implementation(libs.jetbrains.lifecycle.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.kotlin.stdlib)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.poc.pocpdv.MainKt"

//        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
//            packageName = "com.poc.pocpdv"
//            packageVersion = "1.0.0"
//        }
    }
}
