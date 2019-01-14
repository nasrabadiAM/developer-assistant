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

import org.gradle.api.artifacts.dsl.RepositoryHandler

object Deps {
    object versions {
        const val arch_core = "1.1.1"
        const val room = "1.1.1"
        const val lifecycle = "1.1.1"
        const val support = "28.0.0"
        const val supportx = "1.0.0"
        const val dagger = "2.15"
        const val junit = "4.12"
        const val uiautomator = "2.1.3"
        const val uiautomator_x = "2.2.0"
        const val espresso = "3.0.2"
        const val robolectric = "3.8"
        const val retrofit = "2.4.0"
        const val gson = "2.8.5"
        const val okhttp_logging_interceptor = "3.9.0"
        const val mockwebserver = "3.8.1"
        const val apache_commons = "2.5"
        const val mockito = "2.7.19"
        const val mockito_all = "1.10.19"
        const val dexmaker = "2.2.0"
        const val constraint_layout = "1.0.2"
        const val leakcanary = "1.6.1"
        const val glide = "4.7.1"
        const val picasso = "2.5.2"
        const val stetho = "1.5.0"
        const val timber = "4.5.1"
        const val android_gradle_plugin = "3.2.1"
        const val rxjava2 = "2.1.3"
        const val rx_android = "2.0.1"
        const val atsl_runner = "1.0.2"
        const val atsl_rules = "1.0.1"
        const val hamcrest = "1.3"
        const val kotlin = "1.3.0"
        const val coroutines = "1.0.1"
        const val paging = "1.0.1"
        const val work = "1.0.0-alpha04"
        const val navigation = "1.0.0-alpha07"
        const val multidex = "1.0.3"
        const val multidex_x = "2.0.0"
        const val constraint_layout_x = "1.1.2"
        const val arch_core_x = "2.0.0"
        const val espresso_x = "3.1.0"
        const val atsl_x = "1.1.0"

        object android_x_test {
            const val core = "1.0.0"

            const val runner = "1.1.0"
            const val rules = "1.1.0"
            const val junit = "1.0.0"
            const val truth = "1.0.0"
            const val google_truth = "0.42"
            const val esspresso = "3.1.0"

        }
    }


    object support {
        const val annotations = "com.android.support:support-annotations:${versions.support}"
        const val app_compat = "com.android.support:appcompat-v7:${versions.support}"
        const val recyclerview = "com.android.support:recyclerview-v7:${versions.support}"
        const val cardview = "com.android.support:cardview-v7:${versions.support}"
        const val design = "com.android.support:design:${versions.support}"
        const val v4 = "com.android.support:support-v4:${versions.support}"
        const val core_utils = "com.android.support:support-core-utils:${versions.support}"
        const val custom_tabs = "com.android.support:customtabs:${versions.support}"
        const val constraint_layout = "com.android.support.constraint:constraint-layout:${versions.constraint_layout}"
    }

    object supportx {
        const val annotations = "androidx.annotation:annotation:${versions.supportx}"
        const val app_compat = "androidx.appcompat:appcompat:${versions.supportx}"
        const val recyclerview = "androidx.recyclerview:recyclerview:${versions.supportx}"
        const val cardview = "androidx.cardview:cardview:${versions.supportx}"
        const val design = "com.google.android.material:material:${versions.supportx}"
        const val v4 = "androidx.legacy:legacy-support-v4:${versions.supportx}"
        const val core_utils = "androidx.legacy:legacy-support-core-utils:${versions.supportx}"
        const val custom_tabs = "androidx.browser:browser:${versions.supportx}"
        const val constraint_layout = "androidx.constraintlayout:constraintlayout:${versions.constraint_layout_x}"
    }

    object arch {
        const val testing = "android.arch.core:core-testing:${versions.arch_core}"
        const val paging = "android.arch.paging:retrofit_runtime:${versions.paging}"

        object lifecycle {
            const val runtime = "android.arch.lifecycle:retrofit_runtime:${versions.lifecycle}"
            const val extensions = "android.arch.lifecycle:extensions:${versions.lifecycle}"
            const val java8 = "android.arch.lifecycle:common-java8:${versions.lifecycle}"
            const val compiler = "android.arch.lifecycle:compiler:${versions.lifecycle}"
        }

        object room {
            const val runtime = "android.arch.persistence.room:retrofit_runtime:${versions.room}"
            const val compiler = "android.arch.persistence.room:compiler:${versions.room}"
            const val rxjava2 = "android.arch.persistence.room:rxjava2:${versions.room}"
            const val testing = "android.arch.persistence.room:testing:${versions.room}"
        }

        object work {
            const val runtime = "android.arch.work:work-retrofit_runtime:${versions.work}"
            const val testing = "android.arch.work:work-testing:${versions.work}"
            const val firebase = "android.arch.work:work-firebase:${versions.work}"
            const val runtime_ktx = "android.arch.work:work-retrofit_runtime-ktx:${versions.work}"
        }
    }

    object archx {
        const val testing = "androidx.arch.core:core-testing:${versions.arch_core_x}"
        const val paging = "androidx.paging:paging-retrofit_runtime:${versions.arch_core_x}"

        object lifecycle {
            const val retrofit_runtime = "androidx.lifecycle:lifecycle-retrofit_runtime:${versions.arch_core_x}"

            // ViewModel and LiveData
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${versions.arch_core_x}"

            // alternatively - just ViewModel\
            const val viewModel =
                "androidx.lifecycle:lifecycle-viewmodel:${versions.arch_core_x}-ktx" // use -ktx for Kotlin
            // alternatively - just LiveData
            const val LiveData = "androidx.lifecycle:lifecycle-livedata:${versions.arch_core_x}"

            const val java8 = "androidx.lifecycle:lifecycle-common-java8:${versions.arch_core_x}"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:${versions.arch_core_x}"
        }

        object room {
            const val runtime = "androidx.room:room-retrofit_runtime:${versions.arch_core_x}-rc01"
            const val compiler = "androidx.room:room-compiler:${versions.room}-rc01"
            const val rxjava2 = "androidx.room:room-rxjava2:${versions.room}-rc01"
            const val testing = "androidx.room:room-testing:${versions.room}-rc01"
        }

        object work {
            const val runtime = "android.arch.work:work-retrofit_runtime:${versions.work}"
            const val testing = "android.arch.work:work-testing:${versions.work}"
            const val firebase = "android.arch.work:work-firebase:${versions.work}"
            const val runtime_ktx = "android.arch.work:work-retrofit_runtime-ktx:${versions.work}"
        }
    }

    object network {
        const val retrofit_runtime = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${versions.retrofit}"
        const val retrofit_mock = "com.squareup.retrofit2:network-retrofit_mock:${versions.retrofit}"
        const val okhttp_logging_interceptor =
            "com.squareup.okhttp3:logging-interceptor:${versions.okhttp_logging_interceptor}"
        const val okhttp_mock_web_server = "com.squareup.okhttp3:mockwebserver:${versions.mockwebserver}"

    }

    object dagger {
        const val runtime = "com.google.dagger:dagger:${versions.dagger}"
        const val android = "com.google.dagger:dagger-android:${versions.dagger}"
        const val android_support = "com.google.dagger:dagger-android-support:${versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
        const val android_support_compiler = "com.google.dagger:dagger-android-processor:${versions.dagger}"
    }

    object espresso {
        const val core = "com.android.support.test.espresso:espresso-core:${versions.espresso}"
        const val contrib = "com.android.support.test.espresso:espresso-contrib:${versions.espresso}"
        const val intents = "com.android.support.test.espresso:espresso-intents:${versions.espresso}"
    }

    object espresso_x {
        const val core = "androidx.test.espresso:espresso-core:${versions.espresso_x}"
        const val contrib = "androidx.test.espresso:espresso-contrib:${versions.espresso_x}"
        const val intents = "androidx.test.espresso:espresso-intents:${versions.espresso_x}"
    }

    object roboelectric {
        const val core = "org.robolectric:robolectric:${versions.robolectric}"
    }

    object atsl {
        const val runner = "com.android.support.test:runner:${versions.atsl_runner}"
        const val rules = "com.android.support.test:rules:${versions.atsl_runner}"
    }

    object atsl_x {
        const val runner = "androidx.test:runner:${versions.atsl_x}"
        const val rules = "androidx.test:rules:${versions.atsl_x}"
    }

    object android_x_test {
        /** Core library*/
        const val core = "androidx.test:core:${versions.android_x_test.core}"

        /** AndroidJUnitRunner and JUnit Rules*/
        const val runner = "androidx.test:runner:${versions.android_x_test.runner}"
        const val rules = "androidx.test:rules:${versions.android_x_test.rules}"

        /** Assertions*/
        const val junit = "androidx.test.ext:junit:${versions.android_x_test.junit}"
        const val truth = "androidx.test.ext:truth:${versions.android_x_test.truth}"
        const val google_truth = "com.google.truth:truth:${versions.android_x_test.google_truth}"

        /** Espresso dependencies*/
        object espresso {
            const val core = "androidx.test.espresso:espresso-core:${versions.android_x_test.esspresso}"
            const val contrib = "androidx.test.espresso:espresso-contrib:${versions.android_x_test.esspresso}"
            const val intents = "androidx.test.espresso:espresso-intents:${versions.android_x_test.esspresso}"
            const val accessibility =
                "androidx.test.espresso:espresso-accessibility:${versions.android_x_test.esspresso}"
            const val web = "androidx.test.espresso:espresso-web:${versions.android_x_test.esspresso}"
            const val idling_concurrent =
                "androidx.test.espresso.idling:idling-concurrent:${versions.android_x_test.esspresso}"
            const val idling_resource =
                "androidx.test.espresso:espresso-idling-resource:${versions.android_x_test.esspresso}"
        }
    }


    object mockito {
        const val core = "org.mockito:mockito-core:${versions.mockito}"
        const val all = "org.mockito:mockito-all:${versions.mockito}"
        const val android = "org.mockito:mockito-android:${versions.mockito}"
    }

    object kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin}"
        const val test = "org.jetbrains.kotlin:kotlin-test-junit:${versions.kotlin}"
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.kotlin}"
        const val allopen = "org.jetbrains.kotlin.plugin.allopen:${versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${versions.kotlin}"

    }

    object coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"

    }

    object leakcanary {
        const val debug = "com.squareup.leakcanary:leakcanary-android:${versions.leakcanary}"
        const val release = "com.squareup.leakcanary:leakcanary-android-no-op:${versions.leakcanary}"
        const val debugfragment = "com.squareup.leakcanary:leakcanary-support-fragment:${versions.leakcanary}"
    }


    object glide {
        const val runtime = "com.github.bumptech.glide:glide:${versions.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${versions.glide}"
    }

    const val picasso = "com.squareup.picasso:picasso:${versions.picasso}"

    const val multidex = "com.android.support:multidex:${versions.multidex}"
    const val multidex_x = "androidx.multidex:multidex:${versions.multidex_x}"

    object stetho {
        const val core = "com.facebook.stetho:stetho:${versions.stetho}"
        const val okhttp = "com.facebook.stetho:stetho-okhttp3:${versions.stetho}"
        const val urlconnection = "com.facebook.stetho:stetho-urlconnection:${versions.stetho}"
        const val jsconsole = "com.facebook.stetho:stetho-js-rhino:${versions.stetho}"
    }


    const val dexmaker = "com.linkedin.dexmaker:dexmaker-mockito:${versions.dexmaker}"
    const val timber = "com.jakewharton.timber:timber:${versions.timber}"

    object test {
        const val junit = "junit:junit:${versions.junit}"
        const val hamcrest = "org.hamcrest:hamcrest-all:${versions.hamcrest}"
        const val uiautomator = "com.android.support.test.uiautomator:uiautomator-v18:${versions.uiautomator}"
        const val uiautomator_x = "androidx.test.uiautomator:uiautomator:${versions.uiautomator_x}"
    }

    object rx {
        const val rxjava2 = "io.reactivex.rxjava2:rxjava:${versions.rxjava2}"
        const val rx_android = "io.reactivex.rxjava2:rxandroid:${versions.rx_android}"
    }


    const val android_gradle_plugin = "com.android.tools.build:gradle:${versions.android_gradle_plugin}"

    object buildVariants {
        const val min_sdk = 16
        const val target_sdk = 28
        const val build_tools = "28.0.3"
    }


    object navigation {
        const val runtime = "android.arch.navigation:navigation-retrofit_runtime:${versions.navigation}"
        const val runtime_ktx = "android.arch.navigation:navigation-retrofit_runtime-ktx:${versions.navigation}"
        const val fragment = "android.arch.navigation:navigation-fragment:${versions.navigation}"
        const val fragment_ktx = "android.arch.navigation:navigation-fragment-ktx:${versions.navigation}"
        const val ui_ktx = "android.arch.navigation:navigation-ui-ktx:${versions.navigation}"
        const val safe_args_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${versions.navigation}"
        const val testing_ktx = "android.arch.navigation:navigation-testing-ktx:${versions.navigation}"
    }


    fun addRepos(handler: RepositoryHandler) {
        handler.google()
        handler.jcenter()
        handler.mavenCentral()
    }

}
