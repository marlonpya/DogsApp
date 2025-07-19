plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    //id("plugins.jacoco")
    id("kotlin-kapt")
}

android {
    namespace = AndroidBuildConfig.applicationId
    compileSdkVersion(AndroidBuildConfig.compileSdk)

    defaultConfig {
        applicationId = AndroidBuildConfig.applicationId
        minSdk = AndroidBuildConfig.minSdk
        targetSdk = AndroidBuildConfig.targetSdk
        versionCode = AndroidBuildConfig.versionCode
        versionName = AndroidBuildConfig.versionName
        testInstrumentationRunner = AndroidBuildConfig.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
        }
        release {
            isDebuggable = false
            isMinifyEnabled = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-android.txt",
                    "proguard-rules.pro"
                )
            )
        }
    }

    compileOptions {
        targetCompatibility = JavaVersion.VERSION_21
        sourceCompatibility = JavaVersion.VERSION_21
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/**"
        }
    }

    buildFeatures.buildConfig = true
    buildFeatures.compose = true

    composeOptions {
        kotlinCompilerExtensionVersion = AndroidBuildConfig.composeVersion
    }
    defaultConfig {
        applicationId = "com.arteaga.marlon.recipes"
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
        freeCompilerArgs = freeCompilerArgs + AndroidBuildConfig.requiresOptIn
    }
    /*kapt {
        correctErrorTypes = true
    }*/
}


configurations.all {
    resolutionStrategy {
        force("androidx.test:core:1.6.1")
        force("androidx.test:rules:1.6.1")
        force("androidx.test:runner:1.6.2")
    }
}

dependencies {

    val composeBom = platform(libs.compose.boom)
    implementation(composeBom)
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.bundles.app.implements)
    kapt(libs.bundles.kapt)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.bundles.app.androidTest)
    testImplementation(libs.bundles.test)
    debugImplementation(libs.bundles.app.debud.implements)
}
