plugins {
    alias(libs.plugins.convention.cmp.feature)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.components.resources)

                implementation(libs.jetbrains.compose.navigation)
                implementation(libs.coil.network.ktor)
                implementation(libs.coil.compose)

                implementation(projects.core.domain)
                implementation(projects.core.designsystem)
                implementation(projects.core.presentation)
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