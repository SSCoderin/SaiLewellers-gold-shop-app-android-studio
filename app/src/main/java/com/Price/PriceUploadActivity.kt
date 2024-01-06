package com.Price

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.sj.saijewellers.R

class PriceUploadActivity : AppCompatActivity() {

    private lateinit var editTextGoldPrice: EditText
    private lateinit var editTextSilverPrice: EditText
    private lateinit var btnUploadPrices: Button

    private val databaseReference = FirebaseDatabase.getInstance().reference.child("prices")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_price_upload)

        editTextGoldPrice = findViewById(R.id.editTextGoldPrice)
        editTextSilverPrice = findViewById(R.id.editTextSilverPrice)
        btnUploadPrices = findViewById(R.id.btnUploadPrices)

        btnUploadPrices.setOnClickListener {
            uploadPrices()
        }
    }

    private fun uploadPrices() {
        val goldPrice = editTextGoldPrice.text.toString().toDouble()
        val silverPrice = editTextSilverPrice.text.toString().toDouble()

        val priceMap = mapOf("gold" to goldPrice, "silver" to silverPrice)

        // Upload prices to Firebase
        databaseReference.setValue(priceMap)
    }
}
