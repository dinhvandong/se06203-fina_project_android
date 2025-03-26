plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.btec.fpt.appdemo_week7"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.btec.fpt.appdemo_week7"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Unit Testing
    testImplementation(libs.junit)

    // Android Instrumented Testing
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    dependencies {
        // AndroidX Core Libraries
        implementation(libs.appcompat.v161)
        implementation(libs.material.v190)
        implementation(libs.activity.v180)
        implementation(libs.constraintlayout.v214)

        // Unit Testing (Runs on JVM)
        testImplementation(libs.junit)

        // Android Instrumented Testing (Runs on Emulator/Device)
        androidTestImplementation(libs.junit.v115) // JUnit for AndroidX
        androidTestImplementation(libs.espresso.core.v351) // Espresso UI Testing
        androidTestImplementation(libs.core) // Provides ApplicationProvider

        androidTestImplementation("androidx.test:core:1.5.0") // Required for ApplicationProvider
        androidTestImplementation("androidx.test.ext:junit:1.1.5") // JUnit support for Android
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // UI testing
    }

}
