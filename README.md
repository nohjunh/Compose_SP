# Compose_SP

https://velog.io/@nohjunh/Jetpack-Compose

# Plugins (Project)
```kotlin
plugins {
    id 'com.android.application' version '7.3.1' apply false
    id 'com.android.library' version '7.3.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.6.10' apply false
    id 'com.google.dagger.hilt.android' version '2.44' apply false
}
```

# Plugins (Module)
```kotlin
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'dagger.hilt.android.plugin'
    id 'kotlin-kapt'
}
```

# Dependencies
```kotlin
// navigation
def nav_version = "2.5.3"
implementation "androidx.navigation:navigation-compose:$nav_version"

// Coil
implementation("io.coil-kt:coil-compose:2.2.2")
 
// ViewModel
implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"

//Hilt-Dagger
implementation "com.google.dagger:hilt-android:2.44"
kapt "com.google.dagger:hilt-compiler:2.44"

//Room
implementation "androidx.room:room-runtime:2.5.0"
annotationProcessor "androidx.room:room-compiler:2.5.0"

 // To use Kotlin annotation processing tool (kapt) MUST HAVE!
 kapt("androidx.room:room-compiler:2.5.0")
 implementation "androidx.room:room-ktx:2.5.0"

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4"
 ```
