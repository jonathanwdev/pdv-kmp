plugins {
    alias(libs.plugins.convention.cmp.library)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.material3.adaptive.layout)
                implementation(projects.core.domain)
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