import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.kotlin.android") apply false
    id("dagger.hilt.android.plugin") apply false
    id("io.gitlab.arturbosch.detekt")
    id("com.github.ben-manes.versions")
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

tasks {
    withType<Detekt>().configureEach {
        buildUponDefaultConfig = true
        parallel = true
        reports {
            jvmTarget = "11"
            html.required.set(true)
        }
    }

    withType<DependencyUpdatesTask> {
        rejectVersionIf {
            isNonStable(candidate.version)
        }
    }

    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

fun isNonStable(version: String): Boolean {
    return version.contains("alpha|beta|rc".toRegex())
}
