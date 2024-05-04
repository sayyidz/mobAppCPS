package com.example.mobappcps

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobappcps.databinding.ActivityAnalyzeBinding
import com.example.mobappcps.ml.CancerClassification
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp

class AnalyzeActivity : AppCompatActivity() {
    lateinit var btnUploadImage: Button
    lateinit var btnAnalyze: Button
    lateinit var textView: TextView
    lateinit var imageView2: ImageView
    lateinit var bitmap: Bitmap
    lateinit var binding: ActivityAnalyzeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAnalyzeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intentBackToHome = Intent(this, HomeActivity::class.java)
            startActivity(intentBackToHome)
            finish() // Optionally, you can finish the current activity to remove it from the back stack
        }


        btnUploadImage = findViewById(R.id.btnUploadImage)
        btnAnalyze = findViewById(R.id.btnAnalyze)
        textView = findViewById(R.id.textView)
        imageView2 = findViewById(R.id.imageView2)

        // image processor

        var imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()


        btnUploadImage.setOnClickListener{
            var intent = Intent()
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, 100)
        }

        btnAnalyze.setOnClickListener{


            val model = CancerClassification.newInstance(this)

// Creates inputs for reference.
            val image = TensorImage.fromBitmap(bitmap)

            imageProcessor.process(TensorImage())


// Runs model inference and gets result.
            val outputs = model.process(image)
            val probability = outputs.probabilityAsCategoryList


// Releases model resources if no longer used.
            model.close()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100){
            var uri = data?.data;
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            imageView2.setImageBitmap(bitmap)
        }
    }
}