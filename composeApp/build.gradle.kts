import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
        macosArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        commonMain.dependencies {
            // Core, Compose, and Lifecycle
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            // Room & SQLite
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            // DataStore
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Firebase
//            implementation(libs.firebase.auth)
//            implementation(libs.firebase.firestore)
            // Koin
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
            // Utilities
            implementation(libs.kotlinx.serialization.json)
        }

        androidMain.dependencies {
            // Core, Compose, and Lifecycle
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            // Coroutines
            implementation(libs.kotlinx.coroutines.android)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }

        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.darwin)
        }

    }

}

android {
    namespace = "cz.cvut.docta"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "cz.cvut.docta"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    debugImplementation(compose.uiTooling)
    ksp(libs.room.compiler)

//    kspCommonMainMetadata(libs.room.compiler)

//    add("kspAndroid", libs.room.compiler)

//    add("kspIosX64", libs.room.compiler)
//    add("kspIosArm64", libs.room.compiler)
//    add("kspIosSimulatorArm64", libs.room.compiler)
}

afterEvaluate {
    tasks.named("copyRoomSchemasToAndroidTestAssetsDebugAndroidTest").configure {
        enabled = false
    }
}
