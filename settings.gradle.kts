pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
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
            alias("material").to("com.google.android.material:material:1.5.0")
            alias("constraint").to("androidx.constraintlayout:constraintlayout:2.1.3")
            alias("junit").to("junit:junit:4.13.2")
            alias("test-ext").to("androidx.test.ext:junit:1.1.3")
            alias("test-espresso").to("androidx.test.espresso:espresso-core:3.4.0")
        }
    }
}

rootProject.name = "JourneyApp"
include(":app")
