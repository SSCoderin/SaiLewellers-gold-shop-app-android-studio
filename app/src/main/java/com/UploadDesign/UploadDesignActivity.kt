package com.UploadDesign
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.sj.saijewellers.R
import java.io.IOException
import java.util.*

class UploadDesignActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageViewDesign: ImageView
    private lateinit var editTextName: EditText
    private lateinit var editTextContact: EditText

    private var filePath: Uri? = null

    private val storageReference = FirebaseStorage.getInstance().reference
    private val databaseReference = FirebaseDatabase.getInstance().reference.child("designs")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_design)

        imageViewDesign = findViewById(R.id.imageViewDesign)
        editTextName = findViewById(R.id.editTextName)
        editTextContact = findViewById(R.id.editTextContact)

        val btnChooseImage: Button = findViewById(R.id.btnChooseImage)
        val btnUploadDesign: Button = findViewById(R.id.btnUploadDesign)

        btnChooseImage.setOnClickListener {
            chooseImage()
        }

        btnUploadDesign.setOnClickListener {
            uploadDesign()
        }
    }

    private fun chooseImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageViewDesign.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadDesign() {
        if (filePath != null) {
            val ref = storageReference.child("images/" + UUID.randomUUID().toString())
            ref.putFile(filePath!!)
                .addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { uri ->
                        val designName = editTextName.text.toString()
                        val contactNumber = editTextContact.text.toString()

                        val designMap = mapOf("name" to designName, "contact" to contactNumber, "image" to uri.toString())

                        databaseReference.push().setValue(designMap)

                        Toast.makeText(this@UploadDesignActivity, "Design uploaded successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this@UploadDesignActivity, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this@UploadDesignActivity, "Select an image", Toast.LENGTH_SHORT).show()
        }
    }
}
