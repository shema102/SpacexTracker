const val kotlinVersion = "1.4.21"

object BuildPlugins {

    object Versions {
        const val buildToolsVersion = "4.1.2"
        const val navVersion = "2.3.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigation =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinNavigationSafeargs = "androidx.navigation.safeargs.kotlin"


}

object AndroidSdk {
    const val min = 23
    const val compile = 30
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetpack = "1.2.0"
        const val constraintLayout = "2.0.4"
        const val ktx = "1.3.2"
        const val legacySupport = "1.0.0"
        const val navigation = "1.0.0"
        const val preference = "1.1.1"
        const val retrofit = "2.9.0"
        const val retrofitCoroutinesAdapter = "0.9.2"
        const val gson = "2.9.0"
        const val glide = "4.11.0"
        const val material = "1.2.1"
        const val threeten = "1.2.4"
        const val room = "2.2.6"
        const val archLifecycle = "2.2.0"
        const val coroutines = "1.3.7"
        const val dagger = "2.31.2"
        const val groupie = "2.1.0"
        const val compileSdkVersion = 30
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraint =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"


    // Kotlin Android Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Navigation
    const val navigationFragment =
        "android.arch.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUi = "android.arch.navigation:navigation-ui:${Versions.navigation}"
    const val navigationFragmentKtx =
        "android.arch.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "android.arch.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Preference
    const val preference = "androidx.preference:preference-ktx:${Versions.preference}"

    // Retrofit 2
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesAdapter}"

    // Gson
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // Room components
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompilerKapt = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    // Lifecycle components
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
    const val lifecycleCommonKapt =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.archLifecycle}"
    const val lifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"

    // Material design
    const val material = "com.google.android.material:material:${Versions.material}"

    // Better dateTime-time support even on older Android versions
    const val threeten = "com.jakewharton.threetenabp:threetenabp:${Versions.threeten}"

    // Dagger 2
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport =
        "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerCompilerKapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroidProcessorKapt =
        "com.google.dagger:dagger-android-processor:${Versions.dagger}"

    // Groupie
    const val groupie = "com.xwray:groupie:${Versions.groupie}"
    const val groupieExtensions = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"
}

object TestLibraries {
    private object Versions {
        const val junit = "1.1.2"
        const val espresso = "3.1.0-alpha4"
        const val room = "2.2.6"

    }

    const val junit = "androidx.test.ext:junit:${Versions.junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val roomTest = "androidx.room:room-testing:${Versions.room}"

}