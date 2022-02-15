package com.beyond.projecttoy.board

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.beyond.projecttoy.LvModel

import com.beyond.projecttoy.R
import com.beyond.projecttoy.databinding.ActivityBoardBinding
import com.beyond.projecttoy.util.FBA
import com.beyond.projecttoy.util.FBRef
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream

class BoardActivity : AppCompatActivity() {
    private lateinit var binding : ActivityBoardBinding



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_board)

        binding.WbtnBoardwrite.setOnClickListener{
            val title = binding.BETTitle1.text.toString()
            val content = binding.BETContents1.text.toString()
            val uid = FBA.getUid()
            val time = FBA.getTime()


            Log.d("강", title)
            Log.d("걍", content)

            val key = FBRef.boardRef.push().key.toString()





            FBRef.boardRef
                .child(key)
//              .push()
                .setValue(LvModel(title, content, uid, time))




            imageUPload(key)

            Toast.makeText(this, "게시글이 작성되었습니다", Toast.LENGTH_LONG).show()


            finish()







        }

        binding.BEI1.setOnClickListener {




            val key = FBRef.boardRef.push().key.toString()


            Log.d("할", "머니")
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 100)
        }















    }
    private fun imageUPload(key: String){
        val storage = Firebase.storage


        val storageRef = storage.reference

// Create a reference to "mountains.jpg"
        val mountainsRef = storageRef.child(key +".png")

        val imageView = binding.BEI1
        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)//<- 경로설정
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK && requestCode == 100){
            Log.d("꾸꾸", "꺄꺄")
            val BEI1 = findViewById<ImageView>(R.id.BEI1)
            Glide.with(this).load(data?.data).into(BEI1)

        }

    }




}
