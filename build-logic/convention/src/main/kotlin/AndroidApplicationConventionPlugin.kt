import com.android.build.api.dsl.ApplicationExtension
import com.poc.pocpdv.convention.configureKotlinAndroid
import com.poc.pocpdv.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }
            extensions.configure<ApplicationExtension> {
                namespace = "com.poc.pocpdv"
                compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString().toInt()

                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    minSdk = libs.findVersion("projectMinSdkVersion").get().toString().toInt()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                configureKotlinAndroid(this)
            }
        }
    }
}