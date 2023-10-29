package com.github.billman64.cameraxdemo.ui.theme

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Size
import android.view.OrientationEventListener
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
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
                        var cameraProvider = ProcessCameraProvider.getInstance(this)
                        bindImageAnalysis(cameraProvider)


                    } catch (e:Exception){
                        e.printStackTrace()
                    }
                }
            }, ContextCompat.getMainExecutor(this)
        )



    }

    private fun bindImageAnalysis(cameraProvider: ListenableFuture<ProcessCameraProvider>) {

//        var imageAnalysis: ImageAnalysis.Builder = ImageAnalysis.Builder().setTargetResolution(Size(1280, 720))
//            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//            .build()
//            .setAnalyzer(ContextCompat.getMainExecutor(this), ImageAnalysis.Analyzer {
//                analyze(image:ImageProxy){
//
//                }
//
//            }
//            )

    }


//    var orientationEventListener:OrientationEventListener.enable()


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onConfigurationChange() - ${newConfig.orientation}")
        val orientationTv = findViewById<TextView>(R.id.orientation)

        when(newConfig.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> orientationTv.text = "Portrait"
            Configuration.ORIENTATION_LANDSCAPE -> orientationTv.text = "Landscape"
            else -> "(unknown orientation)"
        }

    }



}