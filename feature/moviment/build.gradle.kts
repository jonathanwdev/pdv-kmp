plugins {
    alias(libs.plugins.convention.cmp.feature)
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