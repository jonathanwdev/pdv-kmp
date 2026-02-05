
plugins {
    alias(libs.plugins.convention.cmp.application)
    alias(libs.plugins.compose.hot.reload)

}

kotlin {

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            implementation(projects.core.presentation)
            implementation(projects.core.designsystem)
            implementation(projects.core.data)
            implementation(projects.core.database)
            implementation(projects.core.domain)
            implementation(projects.core.network)
            implementation(projects.core.common)

            implementation(projects.feature.sync)
            implementation(projects.feature.home)
            implementation(projects.feature.sale)
            implementation(projects.feature.transaction)
            implementation(projects.feature.catalog)
            implementation(projects.feature.exchange)

            implementation(libs.bundles.koin.common)

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
            implementation(projects.core.presentation)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.kotlin.stdlib)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

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
