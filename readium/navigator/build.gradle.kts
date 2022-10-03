/*
 * Copyright 2018 Readium Foundation. All rights reserved.
 * Use of this source code is governed by the BSD-style license
 * available in the top-level LICENSE file of the project.
 */

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("maven-publish")
    id("org.jetbrains.dokka")
}

android {
    // FIXME: This doesn't pass the lint because some resources don"t start with r2_ yet. We need to rename all resources for the next major version.
//    resourcePrefix "r2_"

    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"))
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    api(project(":readium:shared"))

    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.browser:browser:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.fragment:fragment-ktx:1.4.0")
    implementation("androidx.legacy:legacy-support-core-ui:1.0.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.4.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.media:media:1.4.3")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.webkit:webkit:1.4.0")
    // Needed to avoid a crash with API 31, see https://stackoverflow.com/a/69152986/1474476
    implementation("androidx.work:work-runtime-ktx:2.7.1")
    // ChrisBane/PhotoView ( for the Zoom handling )
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    implementation("androidx.media2:media2-session:1.2.0")
    implementation("androidx.media2:media2-player:1.2.0")
    // ExoPlayer is used by the Audio Navigator.
    api("com.google.android.exoplayer:exoplayer-core:2.16.1")
    api("com.google.android.exoplayer:exoplayer-ui:2.16.1")
    api("com.google.android.exoplayer:extension-mediasession:2.16.1")
    api("com.google.android.exoplayer:extension-media2:2.16.1")
    api("com.google.android.exoplayer:extension-workmanager:2.16.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("joda-time:joda-time:2.10.13")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    // AM NOTE: needs to stay this version for now (June 24,2020)
    //noinspection GradleDependency
    implementation("org.jsoup:jsoup:1.14.3")

    // Android-pdf-viewer dependency included with readium-navigator is no longer available in that name and version. So use this.
    // Version 2.8.1, but not working normally so use its short commit hash
    implementation("com.github.barteksc:AndroidPdfViewer:46e2dde")
    // Rtl-viewpager dependency included with readium-navigator is no longer available in that name. So use this.
    implementation("com.github.duolingo:rtl-viewpager:1.0.3")
    // utils dependency included with readium-navigator is no longer available in that name. So use this.
    implementation("com.github.tjek:markhor:1.0.9")

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
