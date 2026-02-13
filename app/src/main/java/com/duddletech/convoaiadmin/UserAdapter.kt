package com.duddletech.convoaiadmin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val userList: ArrayList<User>,
    private val onEditClick: (User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.email.text = user.email
        holder.coins.text = "Coins: ${user.coins}"

        holder.editButton.setOnClickListener {
            onEditClick(user)
        }
    }

    override fun getItemCount(): Int = userList.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val email: TextView = itemView.findViewById(R.id.tvUserEmail)
        val coins: TextView = itemView.findViewById(R.id.tvUserCoins)
        val editButton: Button = itemView.findViewById(R.id.btnEditCoin)
    }
}