pluginManagement {
    val androidPluginVersion = "7.1.0"
    val kotlinVersion = "1.6.10"
    val hiltVersion = "2.40.5"
    val detektVersion = "1.19.0"
    val benManesVersion = "0.41.0"

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
    }

    resolutionStrategy {
        eachPlugin {
            if( requested.id.id == "dagger.hilt.android.plugin") {
                useModule("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
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

            alias("view-model").to("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
            alias("lifecycle").to("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
            alias("lifecycle-compiler").to("androidx.lifecycle:lifecycle-compiler:2.4.0")

            alias("hilt-android").to("com.google.dagger:hilt-android:2.40.5")
            alias("hilt-compiler").to("com.google.dagger:hilt-android-compiler:2.40.5")
            alias("hilt-view-model").to("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")

            alias("material").to("com.google.android.material:material:1.5.0")

            alias("junit").to("junit:junit:4.13.2")
            alias("test-lifecycle").to("androidx.arch.core:core-testing:2.1.0")

            alias("test-ext").to("androidx.test.ext:junit:1.1.3")
            alias("test-espresso").to("androidx.test.espresso:espresso-core:3.4.0")
            alias("test-hilt").to("com.google.dagger:hilt-android-testing:2.40.5")
        }
    }
}

rootProject.name = "JourneyApp"
include(":app")
