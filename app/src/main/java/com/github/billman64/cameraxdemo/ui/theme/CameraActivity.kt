package com.github.billman64.cameraxdemo.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import com.github.billman64.cameraxdemo.R
import com.google.common.util.concurrent.ListenableFuture

class CameraActivity : ComponentActivity() {
    val TAG = "CameraActivity" // this.localClassName
    lateinit var previewView:PreviewView
    private lateinit var cameraProviderFuture:ListenableFuture<ProcessCameraProvider>
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        Log.d(TAG, "onCreate()")
    }
}