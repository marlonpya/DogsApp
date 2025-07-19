plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    //id("plugins.jacoco")
    id("kotlin-kapt")
}

android {
    namespace = "${AndroidBuildConfig.basePackage}.data"
    compileSdkVersion(AndroidBuildConfig.compileSdk)

    buildTypes {
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


        buildConfigField("String", "HOST", "\"https://jsonblob.com/\"")
        buildConfigField("String", "API", "\"api\"")
        buildConfigField("String", "DOGS", "\"1151549092634943488\"")
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
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
        freeCompilerArgs = freeCompilerArgs + AndroidBuildConfig.requiresOptIn
    }
}

dependencies {
    implementation(libs.bundles.retrofit2)
    implementation(libs.bundles.library.implements)
    kapt(libs.bundles.kapt)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.library.androidTest)
    androidTestImplementation(libs.bundles.retrofit2)
    implementation(libs.converter.gson)

    kaptAndroidTest(libs.bundles.kapt)
    implementation(project(":domain"))
}