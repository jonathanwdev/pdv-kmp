plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.core.domain)
                implementation(projects.core.designsystem)
                implementation(projects.core.presentation)
                implementation(libs.bundles.koin.common)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
            }
        }

        androidMain {
            dependencies {
            }
        }

        iosMain {
            dependencies {

            }
        }
    }

}