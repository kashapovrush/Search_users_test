plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.kashapovrush.searchuserstest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kashapovrush.searchuserstest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //add project
    implementation(project(":core:network"))
    implementation(project(":core:auth"))
    implementation(project(":features-mobile:common"))
    implementation(project(":features-mobile:palette"))
    implementation(project(":features-mobile:search-users-feature"))
    implementation(project(":features-mobile:user-repositories-feature"))
    implementation(project(":features-mobile:auth-feature"))
    implementation(project(":features-mobile:auth-user-feature"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.oauth.appauth)

    implementation(libs.fragment.manager)

    implementation (libs.browser.core)
}
