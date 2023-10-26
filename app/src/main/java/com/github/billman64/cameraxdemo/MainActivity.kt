package com.github.billman64.cameraxdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.billman64.cameraxdemo.ui.theme.CameraXDemoTheme

class MainActivity : ComponentActivity() {
    val TAG = "CameraXDemo"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraXDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column ( modifier = Modifier.padding(16.dp)) {
                        Greeting(TAG)

                        
                        OutlinedButton(
                            border = BorderStroke(4.dp, Color.Black),
                            shape = RoundedCornerShape(75),
                            colors = ButtonDefaults.buttonColors(),
                            onClick = { Log.d(TAG, "button clicked") }
                        ) { Text("Enable camera")}
                    }

                }
            }
        }

    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = "$text by Bill Lugo",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CameraXDemoTheme {
        Greeting("Android")
    }
}