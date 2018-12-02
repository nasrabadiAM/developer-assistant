/*
 *     This is the source code of developer-assistant project.
 *     Copyright (C)   Ali Nasrabadi<nasrabadiam@gmail.com>  2018-2018
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

import org.gradle.internal.impldep.com.amazonaws.PredefinedClientConfigurations.defaultConfig

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

apply {
    plugin("kotlin-android")
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
        create("release") {
            storeFile = file("../buildSrc/release.jks")
            storePassword = AppReleaseData.STORE_PASSWORD
            keyAlias = AppReleaseData.KEY_ALIAS
            keyPassword = AppReleaseData.KEY_PASSWORD
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
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

    //junit test
    testImplementation(Deps.test.junit)
    testImplementation(Deps.mockito.core)

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
}