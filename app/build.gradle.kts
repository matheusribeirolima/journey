plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.journeyapp"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(libs.view.model)
    implementation(libs.lifecycle)
    kapt(libs.lifecycle.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.view.model)

    implementation(libs.material)

    implementation(libs.gson)

    testImplementation(libs.junit)
    testImplementation(libs.test.lifecycle)

    androidTestImplementation(libs.test.ext)
    androidTestImplementation(libs.test.espresso)
    androidTestImplementation(libs.test.hilt)
    kaptAndroidTest(libs.hilt.compiler)
}
