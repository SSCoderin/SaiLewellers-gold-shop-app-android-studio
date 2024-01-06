// UploadActivity.kt
package com.owner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jewellery.kada
import com.sj.saijewellers.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var selectedImageUri: Uri
    private lateinit var storageReference: StorageReference
    private lateinit var binding: ActivityUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance().reference

        val categories = arrayOf(
            "Ring", "Necklace", "Mangalsutra", "Ear Ring", "Nose Ring",
            "Toe Ring", "Payal", "Chain", "Ankle", "Bangles", "Kada",
            "Neck Chain", "Wrist Chain"
        )

        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerCategory.adapter = categoryAdapter

        binding.btnSelectImage.setOnClickListener {
            openImageChooser()
        }

        binding.btnUpload.setOnClickListener {
            uploadImage()
        }
    }

    private fun openImageChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    selectedImageUri = data.data!!
                    binding.imageView.setImageURI(selectedImageUri)
                }
            }
        }

    private fun uploadImage() {
        val imageName = binding.editTextImageName.text.toString()
        val price = binding.editTextPrice.text.toString().toDouble()
        val category = binding.spinnerCategory.selectedItem.toString()

        val folderReference = storageReference.child(category)
        val imageReference = folderReference.child(imageName)

        imageReference.putFile(selectedImageUri)
            .addOnSuccessListener {
                // Image uploaded successfully
                imageReference.downloadUrl.addOnSuccessListener { imageUrl ->
                    saveImageDetailsToDatabase(imageName, price, category, imageUrl.toString())
                    // Start CategoryActivity after uploading
                    startActivity(Intent(this, RingActivity::class.java).apply {
                        putExtra("/Ring", category)
                    })
                    startActivity(Intent(this, kada::class.java).apply {
                        putExtra("category", category)
                    })
                }
            }
            .addOnFailureListener { e ->
                // Handle unsuccessful uploads
                Toast.makeText(this, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveImageDetailsToDatabase(
        imageName: String,
        price: Double,
        category: String,
        imageUrl: String
    ) {
        val databaseReference = FirebaseDatabase.getInstance().reference
        val imageDetails = ImageDetails(imageName, price, category, imageUrl)

        databaseReference.child("images").child(category).push().setValue(imageDetails)
            .addOnSuccessListener {
                Toast.makeText(this, "Image details saved successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this,
                    "Failed to save image details: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}
