plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.journeyapp"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.journeyapp.CustomTestRunner"
    }

    buildFeatures.viewBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    val defaultDimension = "default"
    flavorDimensions += defaultDimension
    productFlavors {
        create("free") {
            dimension = defaultDimension
        }
        create("pro") {
            dimension = defaultDimension
            applicationIdSuffix = ".pro"
        }
    }

    sourceSets {
        getByName("pro") {
            res {
                srcDirs("src/pro/res")
            }
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.constraint)
    implementation(libs.recycler)
    implementation(libs.activity)
    implementation(libs.splash)
    implementation(libs.material)

    implementation(libs.view.model)
    implementation(libs.lifecycle.runtime)
    kapt(libs.lifecycle.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.view.model)

    implementation(libs.gson)

    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.mockk)
    testImplementation(libs.test.lifecycle)
    testImplementation(libs.test.coroutines)

    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.mockk)
    androidTestImplementation(libs.test.hilt)
    kaptAndroidTest(libs.hilt.compiler)
}
