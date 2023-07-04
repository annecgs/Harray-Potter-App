package com.example.frontend.ui.grifinoriaMembres

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.backend.data.remote.dto.PersonagensItem
import com.example.frontend.R
import com.example.frontend.databinding.HouseMembrerBinding

class AdapterGrifinoriaMembres : ListAdapter<PersonagensItem, AdapterGrifinoriaMembres.ViewHolder>(
    DIFF_CALLBACK
) {
    var onClickListener: ((pokemonId: String) -> Unit)? = null
    class ViewHolder(
        private val binding: HouseMembrerBinding,
        private val onClickListener: ((pokemonId: String) -> Unit)? = null

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(x: PersonagensItem) {
            binding.tvNameMembrer.text = x.name
            findFavorite(x)
            setImage(x)

            binding.linearLayout2.setOnClickListener {
                x.name?.let { it1 -> onClickListener?.invoke(it1) }
            }
        }


        private fun setImage(x: PersonagensItem) {
            if (x.image != "") {
                Glide.with(binding.root.context)
                    .load(x.image)
                    .into(binding.imageMembrer)
            } else {
                Glide.with(binding.root.context)
                    .load(R.drawable.sem_foto)
                    .into(binding.imageMembrer)
            }
        }

        private fun findFavorite(x: PersonagensItem) {
            if (x.isFavorite) {
                binding.imageView3.setImageResource(R.drawable.favorito_on)
            } else {
                binding.imageView3.setImageResource(R.drawable.favorito_off)
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
