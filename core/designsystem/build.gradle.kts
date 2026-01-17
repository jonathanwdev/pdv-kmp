plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor)
                implementation(compose.components.resources)
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

compose.resources {
    publicResClass = true
    packageOfResClass = "designsystem.resources"
    generateResClass = auto
}