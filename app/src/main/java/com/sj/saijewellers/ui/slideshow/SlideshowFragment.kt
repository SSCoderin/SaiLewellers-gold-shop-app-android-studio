package com.sj.saijewellers.ui.slideshow

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.owner.owner
import com.sj.saijewellers.R
import com.sj.saijewellers.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imageView: ImageView = root.findViewById(R.id.owner)

        imageView.setOnClickListener {
            showPasswordDialog()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showPasswordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.toast_password, null)
        val editTextPassword: EditText = dialogView.findViewById(R.id.editTextPassword)
        val btnSubmit: Button = dialogView.findViewById(R.id.btnSubmit)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(true)
            .create()

        btnSubmit.setOnClickListener {
            val enteredPassword = editTextPassword.text.toString()

            // Check the entered password (replace "your_actual_password" with your actual password)
            if (enteredPassword == "1") {
                // Password is correct, open the owner activity
                dialog.dismiss()
                openOwnerActivity()
            } else {
                // Incorrect password, show an alert dialog
                showIncorrectPasswordDialog()
            }
        }

        dialog.show()
    }

    private fun openOwnerActivity() {
        val intent = Intent(activity, owner::class.java)
        startActivity(intent)
    }

    private fun showIncorrectPasswordDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Incorrect Password")
            .setMessage("The entered password is incorrect. Please try again.")
            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                // You can add any action on OK click, or leave it empty
            })
            .create()

        alertDialog.show()
    }
}
