G1QuizApp - Android Studio project (Kotlin)
Package name: com.g1quiz.app

What is included:
- app/src/main/assets/questions.json   (questions array)
- app/src/main/res/drawable/           (all images, sanitized)
- Kotlin source in app/src/main/java/com/g1quiz/app/
- Gradle configuration (project and app build.gradle)

Important:
- This project does NOT include the Gradle wrapper or Android SDK.
- To build the APK you need either Android Studio or command-line Android SDK + Gradle.

Build with Android Studio:
1. Open this folder in Android Studio
2. Let Studio sync and install missing SDK components
3. Build > Build Bundle(s) / APK(s) > Build APK(s)

Build without Android Studio (command-line):
1. Install JDK 17
2. Install Android SDK command-line tools and set ANDROID_SDK_ROOT or ANDROID_HOME
3. Install required platforms/build-tools (e.g., platform 34, build-tools 34.0.0)
4. Either generate gradle wrapper: run 'gradle wrapper' in project root (requires Gradle installed)
   or use your system Gradle: 'gradle assembleDebug'
5. The debug APK will be at: app/build/outputs/apk/debug/app-debug.apk

If you want, I can also add a Gradle wrapper files, but that requires generating the wrapper ZIP which is typically created by Gradle itself.
