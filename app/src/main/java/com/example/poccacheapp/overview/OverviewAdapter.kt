package com.example.poccacheapp.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.poccacheapp.data.State
import com.example.poccacheapp.databinding.ListViewStatesBinding

class OverviewAdapter(private val onClickListener: OnClickListener): androidx.recyclerview.widget.ListAdapter<State,
        OverviewAdapter.StatesPropertyViewHolder>(DiffCallback) {

    class StatesPropertyViewHolder(private var binding: ListViewStatesBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(states: State){
            binding.stateproperty = states
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<State>(){
        override fun areItemsTheSame(oldItem: State, newItem: State): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: State, newItem: State): Boolean {
            return oldItem.id == oldItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatesPropertyViewHolder {
        return StatesPropertyViewHolder(ListViewStatesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: StatesPropertyViewHolder, position: Int) {
        val states =getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(states)
        }
        holder.bind(states)
    }

    class OnClickListener(val clickListener: (states:State) -> Unit) {
        fun onClick(states: State) = clickListener(states)
    }
}