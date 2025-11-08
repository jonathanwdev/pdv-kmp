import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.codingfeline.buildkonfig.compiler.FieldSpec
import com.codingfeline.buildkonfig.gradle.BuildKonfigExtension
import com.poc.pocpdv.convention.pathToPackageName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class BuildKonfigConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.codingfeline.buildkonfig")
            }

            extensions.configure<BuildKonfigExtension> {
                packageName = target.pathToPackageName()
                defaultConfigs {
                    val env = gradleLocalProperties(rootDir, rootProject.providers)
                        .getProperty("ENVIRONMENT")
                        ?: throw IllegalStateException(
                            "Missing ENVIRONMENT property in local.properties"
                        )
                    buildConfigField(FieldSpec.Type.STRING, "ENVIRONMENT", env)
                }
            }
        }
    }
}