plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.room)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

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