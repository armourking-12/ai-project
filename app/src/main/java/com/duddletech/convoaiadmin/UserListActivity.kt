package com.duddletech.convoaiadmin

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.FirebaseFirestore

class UserListActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var userAdapter: UserAdapter
    private lateinit var rvUsers: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        db = FirebaseFirestore.getInstance()
        rvUsers = findViewById(R.id.rvUsers)
        progressBar = findViewById(R.id.progressBar)

        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.setHasFixedSize(true)
        userArrayList = arrayListOf()

        // Initialize adapter with a click listener for editing coins
        userAdapter = UserAdapter(userArrayList) { user ->
            showEditCoinDialog(user)
        }
        rvUsers.adapter = userAdapter

        fetchUsers()
    }

    private fun fetchUsers() {
        progressBar.visibility = View.VISIBLE
        // Ensure this matches the collection name in your User App!
        db.collection("Users").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    userArrayList.clear()
                    for (document in result) {
                        val user = document.toObject(User::class.java)
                        // Save document ID to handle updates easily
                        user.userId = document.id
                        userArrayList.add(user)
                    }
                    userAdapter.notifyDataSetChanged()
                }
                progressBar.visibility = View.GONE
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
    }

    private fun showEditCoinDialog(user: User) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_edit_coins, null)
        val etCoins = dialogView.findViewById<TextInputEditText>(R.id.etCoins)
        etCoins.setText(user.coins.toString())

        AlertDialog.Builder(this)
            .setTitle("Edit Coins for ${user.email}")
            .setView(dialogView)
            .setPositiveButton("Update") { _, _ ->
                val newCoins = etCoins.text.toString().toIntOrNull()
                if (newCoins != null) {
                    updateUserCoins(user.userId, newCoins)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun updateUserCoins(docId: String, newCoins: Int) {
        db.collection("Users").document(docId)
            .update("coins", newCoins)
            .addOnSuccessListener {
                Toast.makeText(this, "Coins Updated!", Toast.LENGTH_SHORT).show()
                fetchUsers() // Refresh list
            }
            .addOnFailureListener {
                Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show()
            }
    }
}