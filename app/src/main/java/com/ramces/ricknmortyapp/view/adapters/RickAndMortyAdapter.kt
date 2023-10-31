package com.ramces.ricknmortyapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramces.ricknmortyapp.data.api.CharacterApi
import com.ramces.ricknmortyapp.databinding.ItemListBinding

class RickAndMortyAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<CharacterApi, RickAndMortyAdapter.RickAndMortyViewHolder>(DiffCallback) {
    class RickAndMortyViewHolder(private var binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(characterOfRickAndMorty: CharacterApi) {
            binding.property = characterOfRickAndMorty
            binding.executePendingBindings()
        }
    }

    override fun submitList(list: MutableList<CharacterApi>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CharacterApi>() {
        override fun areItemsTheSame(oldItem: CharacterApi, newItem: CharacterApi): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CharacterApi, newItem: CharacterApi): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickAndMortyViewHolder {
        return RickAndMortyViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(
        holder: RickAndMortyViewHolder,
        position: Int
    ) {
        val rickAndMorty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(rickAndMorty)
        }
        holder.bind(rickAndMorty)
    }

    class OnClickListener(val clickListener: (rickAndMorty: CharacterApi) -> Unit) {
        fun onClick(rickAndMorty: CharacterApi) = clickListener(rickAndMorty)
    }
}