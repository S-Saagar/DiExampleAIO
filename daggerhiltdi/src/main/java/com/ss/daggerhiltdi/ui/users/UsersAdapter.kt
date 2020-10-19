package com.ss.daggerhiltdi.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ss.daggerhiltdi.data.model.Users
import com.ss.daggerhiltdi.databinding.RvItemUserBinding

class UsersAdapter(private val itemClick: (Users.Result?) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    val users = ArrayList<Users.Result?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        RvItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = users.size


    inner class UserViewHolder(
        private val rvItemUserBinding: RvItemUserBinding
    ) :
        RecyclerView.ViewHolder(rvItemUserBinding.root) {
        fun bind(position: Int) {
            rvItemUserBinding.userModel = users[position]

            rvItemUserBinding.cvContainer.setOnClickListener {
                itemClick(users[position])
            }
        }
    }
}