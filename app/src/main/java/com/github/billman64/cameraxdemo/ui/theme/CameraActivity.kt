package com.github.billman64.cameraxdemo.ui.theme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import com.github.billman64.cameraxdemo.R
import com.google.common.util.concurrent.ListenableFuture

class CameraActivity : AppCompatActivity() {
    lateinit var previewView:PreviewView
    private lateinit var cameraProviderFuture:ListenableFuture<ProcessCameraProvider>
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
    }
}