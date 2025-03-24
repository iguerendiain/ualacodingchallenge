import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("androidx.room")
}

android {
    namespace = "ignacio.guerendiain.uala"
    compileSdk = 35

    defaultConfig {
        applicationId = "ignacio.guerendiain.uala"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue("string", "api_base_url", "https://gist.githubusercontent.com/hernan-uala/dce8843a8edbe0b0018b32e137bc2b3a/raw/0996accf70cb0ca0e16f9a99e0ee185fafca7af1/")

        manifestPlaceholders["GOOGLE_GEO_API_KEY"] = getLocalProperty("google_api_key", "")
    }

    room {
        schemaDirectory("$projectDir/db")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

fun getLocalProperty(propertyKey: String, defaultValue: String): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    return if (localPropertiesFile.exists()) {
        properties.load(localPropertiesFile.inputStream())
        properties.getProperty(propertyKey) ?: defaultValue
    } else {
        defaultValue
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Compose navigation
    implementation(libs.navigation.compose)

    // Koin DI
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    // Google Maps
    implementation(libs.maps.compose)

}