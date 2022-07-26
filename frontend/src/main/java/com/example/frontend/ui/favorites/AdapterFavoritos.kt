package com.example.harraypotterapp.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.HouseMembrerBinding

class AdapterFavoritos : ListAdapter<PersonagensItem, AdapterFavoritos.ViewHolder>(DIFF_CALLBACK) {
    var onClickListener: ((pokemonId: String) -> Unit)? = null
    class ViewHolder(
        private val binding: HouseMembrerBinding,
        private val onClickListener: ((pokemonId: String) -> Unit)? = null

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(x: PersonagensItem) {
            binding.tvNameMembrer.text = x.name
            findPatrono(x)
            findAncestral(x)
            findHouse(x)
            findFavorite(x)
            setImage(x)

            binding.linearLayout2.setOnClickListener {
                onClickListener?.invoke(x.name)
            }
        }

        private fun findHouse(x: PersonagensItem) {
            if (x.house == "Gryffindor") {
                Glide.with(binding.root.context)
                    .load(R.drawable.grifinoria)
                    .into(binding.simbolHouse)
            } else if (x.house == "Hufflepuff") {
                Glide.with(binding.root.context)
                    .load(R.drawable.lufalufa)
                    .into(binding.simbolHouse)
            } else if (x.house == "Ravenclaw") {
                Glide.with(binding.root.context)
                    .load(R.drawable.corvinal)
                    .into(binding.simbolHouse)
            } else if (x.house == "Slytherin") {
                Glide.with(binding.root.context)
                    .load(R.drawable.sonserina)
                    .into(binding.simbolHouse)
            } else {
                Glide.with(binding.root.context)
                    .load(R.drawable.casanaodefinida)
                    .into(binding.simbolHouse)
            }
        }

        private fun setImage(x: PersonagensItem) {
            if (x.image != "") {
                Glide.with(binding.root.context)
                    .load(x.image)
                    .into(binding.imageMembrer)
            } else {
                Glide.with(binding.root.context)
                    .load(R.drawable.bruxonaoidentificado)
                    .into(binding.imageMembrer)
            }
        }

        private fun findAncestral(x: PersonagensItem) {
            if (x.ancestry != "") {
                binding.tvAncestry.text = "Ancestral: " + x.ancestry
            } else {
                binding.tvAncestry.text = "Ancestral não encontrado"
            }
        }

        private fun findPatrono(x: PersonagensItem) {
            if (x.patronus != "") {
                binding.tvPatronus.text = "Patrono: " + x.patronus
            } else {
                binding.tvPatronus.text = "Patrono não encontrado"
            }
        }

        private fun findFavorite(x: PersonagensItem) {
            if (x.isFavorite) {
                binding.imageView3.visibility = View.VISIBLE
            } else {
                binding.imageView3.visibility = View.GONE
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PersonagensItem>() {
            override fun areItemsTheSame(oldItem: PersonagensItem, newItem: PersonagensItem): Boolean {
                return oldItem.name == oldItem.name
            }

            override fun areContentsTheSame(oldItem: PersonagensItem, newItem: PersonagensItem): Boolean {
                return oldItem == oldItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HouseMembrerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
