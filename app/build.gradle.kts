import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.muratdayan.weather"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.muratdayan.weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val keyStoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keyStoreFile.inputStream())

        val apiKey = properties.getProperty("API_KEY")?:""

        buildConfigField(
            type = "String",
            name = "API_KEY",
            value = apiKey
        )

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Navigation kütüphaneleri eklendi nav_graph sağlıyor bize
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Retrofit ve gson
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Hilt ve Ksp aracı ile hilt compiler 
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // location gps service
    implementation (libs.play.services.location)

    // lifecycle viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
}