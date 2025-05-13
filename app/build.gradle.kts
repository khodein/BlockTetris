plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

object ProjectSettings {
    private const val FOCUS_SDK = 35
    private const val MIN_SDK = 24

    private const val MAJOR = 0
    private const val MINOR = 0
    private const val PATCH = 1

    private const val CODE = 1

    private const val DELIMITER = "."

    fun getFocusSdk(): Int { return FOCUS_SDK }

    fun getMinSdk(): Int { return MIN_SDK }

    fun getCode(): Int { return CODE }

    fun getVersion(): String {
        return "${MAJOR}${DELIMITER}${MINOR}${DELIMITER}${PATCH}"
    }
}

android {
    namespace = "com.gg.tetris.block.app"

    compileSdk = ProjectSettings.getFocusSdk()

    defaultConfig {
        applicationId = "com.gg.tetris.block.app"
        minSdk = ProjectSettings.getMinSdk()
        targetSdk = ProjectSettings.getFocusSdk()
        versionCode = ProjectSettings.getCode()
        versionName = ProjectSettings.getVersion()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)

    implementation(libs.material)

    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.serialization.json)

    implementation(libs.koin.android)
    implementation(libs.koin.core)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.review)
    implementation(libs.review.ktx)

    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)

    debugImplementation(libs.leakcanary.android)
}