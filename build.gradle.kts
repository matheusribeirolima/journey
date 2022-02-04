import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    val androidPluginVersion = "7.1.0"
    val kotlinVersion = "1.6.10"
    val detektVersion = "1.19.0"
    val benManesVersion = "0.41.0"
    id("com.android.application").version(androidPluginVersion) apply false
    id("com.android.library").version(androidPluginVersion) apply false
    id("org.jetbrains.kotlin.android").version(kotlinVersion) apply false
    id("io.gitlab.arturbosch.detekt").version(detektVersion)
    id("com.github.ben-manes.versions").version(benManesVersion)
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

tasks.withType<Detekt>().configureEach {
    buildUponDefaultConfig = true
    parallel = true
    reports {
        jvmTarget = "11"
        html.required.set(true)
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    return version.contains("alpha|beta|rc".toRegex())
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
