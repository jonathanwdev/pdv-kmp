plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
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