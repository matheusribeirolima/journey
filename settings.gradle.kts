pluginManagement {
    val androidPluginVersion = "7.1.1"
    val kotlinVersion = "1.6.10"
    val hiltVersion = "2.40.5"
    val detektVersion = "1.19.0"
    val benManesVersion = "0.41.0"
    val jUnit5Version = "1.8.2.0"

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    plugins {
        id("com.android.application") version androidPluginVersion
        id("com.android.library") version androidPluginVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("dagger.hilt.android.plugin") version hiltVersion
        id("io.gitlab.arturbosch.detekt") version detektVersion
        id("com.github.ben-manes.versions") version benManesVersion
        id("de.mannodermaus.android-junit5") version jUnit5Version
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "dagger.hilt.android.plugin" ->
                    useModule("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
                "de.mannodermaus.android-junit5" ->
                    useModule("de.mannodermaus.gradle.plugins:android-junit5:$jUnit5Version")
            }
        }
    }
}
enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    @Suppress("UnstableApiUsage")
    versionCatalogs {
        create("libs") {
            alias("androidx-core").to("androidx.core:core-ktx:1.7.0")
            alias("androidx-appcompat").to("androidx.appcompat:appcompat:1.4.1")
            alias("constraint").to("androidx.constraintlayout:constraintlayout:2.1.3")
            alias("recycler").to("androidx.recyclerview:recyclerview:1.2.1")
            alias("activity").to("androidx.activity:activity-ktx:1.4.0")
            alias("splash").to("androidx.core:core-splashscreen:1.0.0-beta01")
            alias("material").to("com.google.android.material:material:1.5.0")

            version("lifecycle", "2.4.0")
            alias("view-model").to("androidx.lifecycle", "lifecycle-viewmodel-ktx")
                .versionRef("lifecycle")
            alias("lifecycle-runtime").to("androidx.lifecycle", "lifecycle-runtime-ktx")
                .versionRef("lifecycle")
            alias("lifecycle-compiler").to("androidx.lifecycle", "lifecycle-compiler")
                .versionRef("lifecycle")

            version("hilt", "2.40.5")
            alias("hilt-android").to("com.google.dagger", "hilt-android").versionRef("hilt")
            alias("hilt-compiler").to("com.google.dagger", "hilt-android-compiler")
                .versionRef("hilt")
            alias("hilt-view-model").to("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

            alias("gson").to("com.google.code.gson:gson:2.8.9")

            version("junit5", "5.8.2")
            alias("junit-api").to("org.junit.jupiter", "junit-jupiter-api")
                .versionRef("junit5")
            alias("junit-engine").to("org.junit.jupiter", "junit-jupiter-engine")
                .versionRef("junit5")
            alias("mockk").to("io.mockk:mockk:1.12.2")
            alias("test-lifecycle").to("androidx.arch.core:core-testing:2.1.0")
            alias("test-coroutines").to("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")

            alias("test-ext").to("androidx.test.ext:junit:1.1.3")
            alias("test-espresso").to("androidx.test.espresso:espresso-core:3.4.0")
            alias("test-mockk").to("io.mockk:mockk-android:1.12.2")
            alias("test-hilt").to("com.google.dagger", "hilt-android-testing")
                .versionRef("hilt")
        }
    }
}

rootProject.name = "JourneyApp"
include(":app")
