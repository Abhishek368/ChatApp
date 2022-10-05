package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.receive.view.*
import kotlinx.android.synthetic.main.sent.view.*

class MessagesRVAdapter(val context: Context,val messageList : ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECEIVE = 1
    val ITEM_SENT =2
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == 1){
            var itemView = LayoutInflater.from(context).inflate(R.layout.receive,parent,false)
            return ReceiveViewHolder(itemView)
        }
        else{
            var itemView = LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SentViewHolder(itemView)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SENT
        }
        return ITEM_RECEIVE
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position].message
        if(holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.toString()
        }
        else{
            val viewHolder = holder as ReceiveViewHolder
            holder.receivedMessage.text = currentMessage.toString()
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }


    inner class SentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val sentMessage :TextView= itemView.sent_text
    }
    inner class ReceiveViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val receivedMessage:TextView = itemView.received_text
    }

}