package com.github.billman64.cameraxdemo.ui.theme

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.OrientationEventListener
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.github.billman64.cameraxdemo.R
import com.google.common.util.concurrent.ListenableFuture

class CameraActivity : ComponentActivity() {
    val TAG = "CameraActivity" // this.localClassName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        Log.d(TAG, "onCreate()")
        var previewView:PreviewView = findViewById(R.id.previewView)
        var cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        var textView:TextView = findViewById(R.id.orientation)

        cameraProviderFuture.addListener(
            {
                run {
                    try{
                        var cameraProvider:ProcessCameraProvider = cameraProviderFuture.get()
                        bindImageAnalysis(cameraProvider)
                    } catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }, ContextCompat.getMainExecutor(this)
        )
    }

    private fun bindImageAnalysis(cameraProvider: ProcessCameraProvider) {

        var imageAnalysis: ImageAnalysis = ImageAnalysis.Builder().setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build()

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), ImageAnalysis.Analyzer {
            ImageAnalysis.Analyzer(){
                fun analyze(image: ImageProxy){
                    image.close()
                }
            }
        }
        )

        val orientationEventListener = object: OrientationEventListener(this) {
            override fun onOrientationChanged(p0: Int) {
                Log.d(TAG, "onConfigurationChange() - ${p0}")
                val orientationTv = findViewById<TextView>(R.id.orientation)

                when(p0) {

                    in 0..45 -> orientationTv.text = "portrait"
                    in 60..120 -> orientationTv.text = "landscape"
                    in 150..210 -> orientationTv.text = "portrait"
                    in 240..300 -> orientationTv.text = "landscape"

                    else -> "(unknown orientation)"
                }
            }
        }
        orientationEventListener.enable()
        var preview:Preview = Preview.Builder().build()
        var cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
        var previewView: PreviewView = findViewById(R.id.previewView)
        preview.setSurfaceProvider(previewView.createSurfaceProvider())
        cameraProvider.bindToLifecycle(this, cameraSelector, imageAnalysis, preview)
    }

}