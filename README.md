# adkrity-plugin-demo

1. Plugin Integration steps

   In your _MyApp/app/build.gradle.kts_ add :

   ```
   android {
       buildTypes {
           release {
             ...
           }
           debug {
             ...
           }
           create("profile") {
               initWith(getByName("debug"))
           }
   }
   
   dependencies {
     // ...
      debugImplementation("com.adkrity.module.adkrity_module:flutter_debug:1.0")
      releaseImplementation("com.adkrity.module.adkrity_module:flutter_release:1.0")
      add("profileImplementation", "com.adkrity.module.adkrity_module:flutter_profile:1.0")
   }
   ```

   Also, In your _MyApp/app/build.gradle.kts_ add :

   ```
   configurations {
       getByName("profileImplementation") {
       }
   }
   ```

   In your _MyApp/settings.gradle.kts_ add :
   ```
   include(":app")
   
   dependencyResolutionManagement {
       repositories {
           maven(url = "https://storage.googleapis.com/download.flutter.io")
           maven(url = "some/path/flutter_module_project/build/host/outputs/repo")
       }
   }
   ```

2. Launching the Plugin

   Add the following XML to your AndroidManifest.xml file under your application tag:
   ```
   <activity
     android:name="io.flutter.embedding.android.FlutterActivity"
     android:theme="@style/LaunchTheme"
     android:configChanges="orientation|keyboardHidden|keyboard|screenSize|locale|layoutDirection|fontScale|screenLayout|density|uiMode"
     android:hardwareAccelerated="true"
     android:windowSoftInputMode="adjustResize"
     />
   ```

   Find a reasonable location in your app to instantiate a FlutterEngine. The following example arbitrarily pre-warms a FlutterEngine in the Application class:
   ```
   import io.flutter.embedding.android.FlutterActivity;
   
   class MyApplication : Application() {
   lateinit var flutterEngine : FlutterEngine

   override fun onCreate() {
   super.onCreate()

    // Instantiate a FlutterEngine.
    flutterEngine = FlutterEngine(this)

    // Start executing Dart code to pre-warm the FlutterEngine.
    flutterEngine.dartExecutor.executeDartEntrypoint(
      DartExecutor.DartEntrypoint.createDefault()
    )

    // Cache the FlutterEngine to be used by FlutterActivity.
    FlutterEngineCache
      .getInstance()
      .put("my_engine_id", flutterEngine)
   }
   }
   ```

   Add code to launch FlutterActivity from whatever point in your app that you'd like. The following
   example shows FlutterActivity being launched from an OnClickListener.
   

   In your ExistingActivity.kt, setOnClickListener :
   ```
   myButton.setOnClickListener {
      startActivity(
          FlutterActivity
              .withCachedEngine("my_engine_id")
              .build(this)
      }
   ```
   
   
   
   
   
   
   
   
   
   
   
