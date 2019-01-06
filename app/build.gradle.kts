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
    androidTestImplementation(Deps.test.junit)
    androidTestImplementation(Deps.supportx.annotations)
    androidTestImplementation(Deps.atsl_x.runner)
    androidTestImplementation(Deps.atsl_x.rules)
    androidTestImplementation(Deps.mockito.core)
    androidTestImplementation(Deps.mockito.android)
    androidTestImplementation(Deps.espresso_x.core)
    androidTestImplementation(Deps.espresso_x.intents)
    androidTestImplementation("com.nhaarman:mockito-kotlin:1.5.0")
    androidTestImplementation(Deps.kotlin.test)

    //fragment test util
    debugImplementation("androidx.fragment:fragment-testing:1.1.0-alpha02")
}