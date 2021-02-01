plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.kotlinNavigationSafeargs)
}

//val keystorePropertiesFile = rootProject.file("keystore.properties")
//val keystoreProperties = Properties()
//keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    compileSdkVersion(AndroidSdk.compile)
    buildToolsVersion = "30.0.2"

    defaultConfig {
        applicationId = "com.shema102.spacextracker"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

//    signingConfigs {
//        getByName("release") {
//            storeFile(file(keystoreProperties["storeFile"]))
//            storePassword(keystoreProperties["storePassword"])
//            keyAlias(keystoreProperties["keyAlias"])
//            keyPassword(keystoreProperties["keyPassword"])
//        }
//    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
//            signingConfig(signingConfigs.release)
        }

        getByName("debug") {
            applicationIdSuffix(".debug")
            isDebuggable = true
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

androidExtensions {
    isExperimental = true
}


dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appcompat)
    implementation(Libraries.constraint)
    implementation(Libraries.legacy)

    implementation(Libraries.coroutines)
    implementation(Libraries.coroutinesAndroid)

    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationUi)
    implementation(Libraries.navigationUiKtx)

    implementation(Libraries.preference)

    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitCoroutinesAdapter)

    implementation(Libraries.gson)

    implementation(Libraries.glide)
    kapt(Libraries.glideKapt)

    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomKtx)
    kapt(Libraries.roomCompilerKapt)

    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.lifecycleViewmodelKtx)
    kapt(Libraries.lifecycleCommonKapt)

    implementation(Libraries.material)

    implementation(Libraries.threeten)

    implementation(Libraries.dagger)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerCompilerKapt)
    kapt(Libraries.daggerAndroidProcessorKapt)


    implementation(Libraries.groupie)
    implementation(Libraries.groupieExtensions)


    androidTestImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.roomTest)
}