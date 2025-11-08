import com.android.build.gradle.LibraryExtension
import com.poc.pocpdv.convention.configureKotlinAndroid
import com.poc.pocpdv.convention.configureKotlinMultiplatform
import com.poc.pocpdv.convention.libs
import com.poc.pocpdv.convention.pathToResourcePrefix
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class KmpLibraryConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            configureKotlinMultiplatform()

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)

                resourcePrefix = this@with.pathToResourcePrefix()
                experimentalProperties["android.experimental.kmp.enableAndroidResources"] = "true"
            }

            dependencies {
                "commonMainImplementation"(libs.findLibrary("kotlinx-serialization-json").get())
                "commonTestImplementation"(libs.findLibrary("kotlin-test").get())
            }
        }
    }
}