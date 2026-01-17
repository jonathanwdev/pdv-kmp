plugins {
    alias(libs.plugins.convention.kmp.library)
    alias(libs.plugins.convention.buildkonfig)
}

kotlin {

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(projects.core.domain)
                implementation(projects.core.network)
                implementation(projects.core.database)
                implementation(libs.koin.core)

                implementation(libs.androidx.room.runtime)
                implementation(libs.sqlite.bundled)

            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.android)
            }
        }

        iosMain {
            dependencies {

            }
        }

    }

}