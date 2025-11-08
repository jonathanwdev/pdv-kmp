import com.poc.pocpdv.convention.configureAndroidTarget
import com.poc.pocpdv.convention.configureDesktopTarget
import com.poc.pocpdv.convention.configureIosTargets
import com.poc.pocpdv.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CmpApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.poc.convention.android.application.compose")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            configureAndroidTarget()
            configureIosTargets()
            configureDesktopTarget()
            dependencies {
                "debugImplementation"(libs.findLibrary("androidx-compose-ui-tooling").get())
            }
        }
    }
}