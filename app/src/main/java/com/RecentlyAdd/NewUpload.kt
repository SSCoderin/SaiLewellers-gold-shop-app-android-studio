package com.RecentlyAdd
// Import necessary libraries
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.sj.saijewellers.R
import java.io.IOException

class NewUpload : Activity() {

    private lateinit var nameEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var imageView: ImageView

    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_upload)

        nameEditText = findViewById(R.id.nameEditText)
        weightEditText = findViewById(R.id.weightEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        imageView = findViewById(R.id.imageView)

        val selectImageButton: Button = findViewById(R.id.selectImageButton)
        selectImageButton.setOnClickListener {
            // Open gallery to select an image
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        val uploadButton: Button = findViewById(R.id.uploadButton)
        uploadButton.setOnClickListener {
            uploadImageToFirestore()
        }
    }

    private fun uploadImageToFirestore() {
        val name = nameEditText.text.toString()
        val weight = weightEditText.text.toString()
        val description = descriptionEditText.text.toString()

        val storageRef = FirebaseStorage.getInstance().reference.child("images/${System.currentTimeMillis()}")
        val uploadTask = storageRef.putFile(selectedImageUri)

        uploadTask.addOnSuccessListener { taskSnapshot ->
            // Image uploaded successfully, now get the download URL
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                // Save image details to Firestore
                saveImageDetailsToFirestore(name, weight, description, uri.toString())
            }
        }.addOnFailureListener { exception ->
            Log.e("UploadImage", "Error uploading image", exception)
        }
    }

    private fun saveImageDetailsToFirestore(name: String, weight: String, description: String, imageUrl: String) {
        val newData = NewData(name, weight, description, imageUrl)
        val db = FirebaseFirestore.getInstance()
        db.collection("images").add(newData)
            .addOnSuccessListener { documentReference ->
                Log.d("UploadImage", "DocumentSnapshot added with ID: ${documentReference.id}")
                // Optionally, you can show a success message or navigate to another screen.
            }
            .addOnFailureListener { e ->
                Log.e("UploadImage", "Error adding document", e)
            }
    }

    // Handle image selection result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data!!
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                imageView.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
}
