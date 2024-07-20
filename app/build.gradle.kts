import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "dev.nonoxy.restaurant_reviews"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.nonoxy.restaurant_reviews"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField(
            "String",
            "RESTAURANT_REVIEWS_API_BASE_URL",
            "\"https://api.mycyprus.app/api/\""
        )

        val localProperties = Properties().apply {
            load(project.rootProject.file("local.properties").inputStream())
        }

        buildConfigField(
            "String",
            "RESTAURANT_REVIEWS_API_KEY",
            "\"${localProperties.getProperty("API_KEY")}\""
        )
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    composeCompiler {
        enableStrongSkippingMode = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.material3)

    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.dagger.hilt.android)
    implementation(libs.dagger.hilt.navigation)
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.navigation.compose)

    implementation(libs.kotlinx.serialization.json)

    implementation(project(":api"))
    implementation(project(":data"))
    implementation(project(":features:theme"))
    implementation(project(":features:common"))
    implementation(project(":features:restaurants"))
    implementation(project(":features:restaurantdetail"))
}