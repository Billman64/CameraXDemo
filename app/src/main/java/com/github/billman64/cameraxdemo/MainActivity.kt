package com.github.billman64.cameraxdemo

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.billman64.cameraxdemo.ui.theme.CameraActivity
import com.github.billman64.cameraxdemo.ui.theme.CameraXDemoTheme

class MainActivity : ComponentActivity() {
    val TAG = "CameraXDemo"
    val PERM_REQUEST_CODE = 200
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
                            onClick = { Log.d(TAG, "button clicked")
                            cameraButton()
                            }
                        ) { Text("Enable camera")}
                    }

                }
            }
        }

    }

    private fun cameraButton() {

        if(hasCameraPermission()){
            enableCamera()
        } else {
            requestPermission()
        }
    }

    private fun enableCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, Array<String>(1) {Manifest.permission.CAMERA},
        PERM_REQUEST_CODE)
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
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