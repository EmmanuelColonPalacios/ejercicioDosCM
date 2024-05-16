package com.example.ejerciciodoscm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ejerciciodoscm.databinding.ItemCharacterBinding

class CharacterAdapter(private val listener: OnItemClickListener) : ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(CharacterComparator()) {

    interface OnItemClickListener {
        fun onItemClick(character: Character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.apply {
                characterName.text = character.name
                characterAffiliation.text = character.affiliation ?: "No Affiliation"
                Glide.with(itemView.context)
                    .load(character.imageUrl)
                    .into(characterImage)
                root.setOnClickListener { listener.onItemClick(character) }
            }
        }
    }

    class CharacterComparator : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
