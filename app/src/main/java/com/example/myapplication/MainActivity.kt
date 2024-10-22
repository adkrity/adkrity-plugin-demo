package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MainActivity : ComponentActivity() {
    lateinit var flutterEngine : FlutterEngine
    private val CHANNEL = "com.adkrity.module/data"

    private var AkUserMobileNumber = "1925024856";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        flutterEngine = FlutterEngine(this)
// Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.navigationChannel.setInitialRoute("adkrity/$AkUserMobileNumber");
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(name = "test", onClick = {
//                        val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
//                        methodChannel.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
//                            if (methodCall.method == "getIntentData") {
//                                result.success(
//                                    AkUserMobileNumber
//                                )
////Accessing data sent from flutter
//                            } else {
//                                Log.i("new method came", methodCall.method)
//                            }
//                        }
                        startActivity(
                            FlutterActivity
                                .withCachedEngine("my_engine_id")
                                .build(this)
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    // Box to center the Button
    Box(
        modifier = Modifier
            .fillMaxSize() // Fills the available space
            .background(MaterialTheme.colorScheme.onBackground) // Sets Box background color to white
            .padding(8.dp), // Fills the available space
        contentAlignment = Alignment.Center // Centers the content inside the Box
    ) {
        Button(
            onClick = { onClick() },
            modifier = modifier
                .padding(8.dp) // Adds padding around the button
                .wrapContentWidth() // Ensures the button only takes the necessary width
        ) {
            Text(
                text = "Start Plugin",
                maxLines = 1 // Ensures the text stays on one line
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        Greeting("Android")
//    }
//}