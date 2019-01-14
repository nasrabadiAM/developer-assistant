/*
 *     This is the source code of developer-assistant project.
 *     Copyright (C)   Ali Nasrabadi<nasrabadiam@gmail.com>  2018-2019
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}


android {
    compileSdkVersion(Deps.buildVariants.target_sdk)
    defaultConfig {
        applicationId = "com.nasrabadiam.developerassistant"
        minSdkVersion(Deps.buildVariants.min_sdk)
        targetSdkVersion(Deps.buildVariants.target_sdk)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }
    
    kapt { generateStubs = true }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../buildSrc/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isUseProguard = true
            isMinifyEnabled = false
            isDebuggable = false
            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    //kotlin
    implementation(Deps.kotlin.stdlib)
    implementation(Deps.kotlin.reflect)

    //ui
    implementation(Deps.supportx.app_compat)
    implementation(Deps.supportx.constraint_layout)
    implementation(Deps.supportx.recyclerview)
    implementation(Deps.supportx.design)

    //LiveData & viewModel
    implementation(Deps.archx.lifecycle.extensions)
    kapt(Deps.archx.lifecycle.compiler)

    //rxJava
    implementation(Deps.rx.rxjava2)
    implementation(Deps.rx.rx_android)

    //glide
    implementation(Deps.glide.runtime)

    //dependency injection
    implementation(Deps.dagger.runtime)
    kapt(Deps.dagger.compiler)

    //junit test
    testImplementation(Deps.test.junit)
    testImplementation(Deps.mockito.core)
    testImplementation(Deps.arch.testing)


    //android unit test
    androidTestImplementation(Deps.android_x_test.core)

    androidTestImplementation(Deps.android_x_test.runner)
    androidTestImplementation(Deps.android_x_test.rules)

    androidTestImplementation(Deps.android_x_test.junit)
    androidTestImplementation(Deps.android_x_test.truth)
    androidTestImplementation(Deps.android_x_test.google_truth)

    androidTestImplementation(Deps.android_x_test.espresso.core)
    androidTestImplementation(Deps.android_x_test.espresso.contrib)
    androidTestImplementation(Deps.android_x_test.espresso.intents)
    androidTestImplementation(Deps.android_x_test.espresso.accessibility)
    androidTestImplementation(Deps.android_x_test.espresso.web)
    androidTestImplementation(Deps.android_x_test.espresso.idling_concurrent)
    androidTestImplementation(Deps.android_x_test.espresso.idling_resource)
}