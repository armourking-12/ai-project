package com.duddletech.convoaiadmin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var switchMaintenance: SwitchCompat
    private lateinit var tvActiveSessionsCount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_main)

        // 1. Initialize Firebase Services
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // 2. Security Check: Only allow logged-in admins
        checkAdminAccess()

        // 3. Initialize UI Components from activity_admin_main.xml
        switchMaintenance = findViewById(R.id.switchMaintenance)
        tvActiveSessionsCount = findViewById(R.id.tvActiveSessionsCount)

        val cardUsers = findViewById<MaterialCardView>(R.id.cardUsers)
        val cardCoins = findViewById<MaterialCardView>(R.id.cardCoins)
        val cardAnalytics = findViewById<MaterialCardView>(R.id.cardAnalytics)

        // 4. Sync Maintenance State on Launch
        loadSystemSettings()

        // 5. Maintenance Switch Listener (The Kill Switch)
        switchMaintenance.setOnCheckedChangeListener { _, isChecked ->
            updateMaintenanceStatus(isChecked)
        }

        // 6. Navigation: Manage Registered Users
        cardUsers.setOnClickListener {
            // This opens the list of users who signed up in the User App
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }

        // 7. Navigation: Economy & Coins
        cardCoins.setOnClickListener {
            Toast.makeText(this, "Opening Economy Control Panel...", Toast.LENGTH_SHORT).show()
            // Future step: Start EconomyActivity::class.java
        }

        // 8. Refresh Analytics Data
        cardAnalytics.setOnClickListener {
            fetchRealTimeStats()
        }
    }

    private fun checkAdminAccess() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // If not logged in at all, redirect to a login screen if you have one
            Toast.makeText(this, "Please log in to access Admin Panel", Toast.LENGTH_LONG).show()
        } else {
            // Optional: Check Firestore 'Admins' collection for current user email
            db.collection("Admins").document(currentUser.email!!).get()
                .addOnSuccessListener { doc ->
                    if (!doc.exists()) {
                        Toast.makeText(this, "Unauthorized Access Denied!", Toast.LENGTH_LONG).show()
                        finish() // Closes the app for non-admins
                    }
                }
        }
    }

    private fun loadSystemSettings() {
        // Monitors the 'isMaintenanceActive' field to trigger MaintenanceActivity.xml
        db.collection("Settings").document("System")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val isMaintenanceOn = document.getBoolean("isMaintenanceActive") ?: false
                    switchMaintenance.isChecked = isMaintenanceOn
                }
            }
    }

    private fun updateMaintenanceStatus(status: Boolean) {
        val updates = mapOf("isMaintenanceActive" to status)

        db.collection("Settings").document("System")
            .set(updates)
            .addOnSuccessListener {
                val msg = if (status) "Maintenance ACTIVE 🚧" else "System LIVE 🚀"
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun fetchRealTimeStats() {
        // Counts users registered via User App's SignUpActivity.kt
        db.collection("Users").get()
            .addOnSuccessListener { result ->
                tvActiveSessionsCount.text = result.size().toString()
                Toast.makeText(this, "Statistics Updated", Toast.LENGTH_SHORT).show()
            }
    }
}