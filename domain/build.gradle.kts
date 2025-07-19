plugins {
    //alias(libs.plugins.android.library)
    //alias(libs.plugins.kotlin.android)
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    //id("plugins.jacoco")
    id("kotlin-kapt")
}

android {
    namespace = "${AndroidBuildConfig.basePackage}.domain"

    compileSdkVersion(AndroidBuildConfig.compileSdk)

    buildTypes{
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    defaultConfig {
        minSdk = AndroidBuildConfig.minSdk
        testInstrumentationRunner = AndroidBuildConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/**"
        }
    }

    buildFeatures.buildConfig = true

    defaultConfig {
        testInstrumentationRunner = AndroidBuildConfig.testInstrumentationRunner
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
        freeCompilerArgs = freeCompilerArgs + AndroidBuildConfig.requiresOptIn
    }
}

dependencies {
    implementation(libs.bundles.library.implements)
    kapt(libs.bundles.kapt)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.library.androidTest)
}
