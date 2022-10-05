package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.card_user_info.view.*

class UserRVAdapter(val context: Context,val userList :ArrayList<User>) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>(){


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var userName: TextView = itemView.txtusername
        var lastmsg : TextView = itemView.lastmsg
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.card_user_info,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userName.text = userList[position].name.toString()
        holder.lastmsg.text = "what is this"
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",userList[position].name.toString())
            intent.putExtra("uid", userList[position].uid.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}