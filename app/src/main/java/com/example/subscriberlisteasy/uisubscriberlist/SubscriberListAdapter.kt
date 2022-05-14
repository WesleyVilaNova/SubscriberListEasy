package com.example.subscriberlisteasy.uisubscriberlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriberlisteasy.data.db.entity.SubscriberEntity
import com.example.subscriberlisteasy.databinding.SubscriberItemBinding
import com.example.subscriberlisteasy.uisubscriberlist.interfaces.IOnClick

class SubscriberListAdapter(val _onClick: IOnClick) : IOnClick,
    ListAdapter<SubscriberEntity, SubscriberListAdapter.SubscriberViewHolder>(DiffCalBack()) {

    override fun onClick(click: SubscriberEntity?) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberViewHolder {
        return SubscriberViewHolder(
            SubscriberItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SubscriberViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.textNameItem.text = item.name
        holder.binding.textNameEmail.text = item.email

        holder.itemView.setOnClickListener { _onClick.onClick(item) }
    }

    class SubscriberViewHolder(val binding: SubscriberItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}

class DiffCalBack : DiffUtil.ItemCallback<SubscriberEntity>() {
    override fun areItemsTheSame(oldItem: SubscriberEntity, newItem: SubscriberEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SubscriberEntity, newItem: SubscriberEntity): Boolean {
        return oldItem.name == newItem.name && oldItem.email == newItem.email
    }
}
