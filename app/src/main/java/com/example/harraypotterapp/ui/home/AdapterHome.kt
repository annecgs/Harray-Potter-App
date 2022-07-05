package com.example.harraypotterapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.harraypotterapp.R
import com.example.harraypotterapp.data.remote.dto.PersonagensItem
import com.example.harraypotterapp.databinding.PeopleItemBinding

class AdapterHome : ListAdapter<PersonagensItem, AdapterHome.ViewHolder>(DIFF_CALLBACK.DIFF_CALLBACK) {
    var onClickListener: ((peopleId: String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PeopleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: PeopleItemBinding,
        private val onClickListener: ((peopleId: String) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(x: PersonagensItem) {
            binding.nomePeople.text = x.name

            findHouse(x)
            setImage(x)
            getFavorite(x)

            binding.root.setOnClickListener {
                onClickListener?.invoke(x.name)
            }
        }

        private fun findHouse(x: PersonagensItem) {
            if (x.house == "Gryffindor") {
                Glide.with(binding.root.context)
                    .load(R.drawable.grifinoria)
                    .into(binding.casa)
            } else if (x.house == "Hufflepuff") {
                Glide.with(binding.root.context)
                    .load(R.drawable.lufalufa)
                    .into(binding.casa)
            } else if (x.house == "Ravenclaw") {
                Glide.with(binding.root.context)
                    .load(R.drawable.corvinal)
                    .into(binding.casa)
            } else if (x.house == "Slytherin") {
                Glide.with(binding.root.context)
                    .load(R.drawable.sonserina)
                    .into(binding.casa)
            } else {
                Glide.with(binding.root.context)
                    .load(R.drawable.casanaodefinida)
                    .into(binding.casa)
            }
        }

        private fun setImage(x: PersonagensItem) {
            if (x.image != "") {
                Glide.with(binding.root.context)
                    .load(x.image)
                    .into(binding.imagePeople)
            } else {
                Glide.with(binding.root.context)
                    .load(R.drawable.bruxonaoidentificado)
                    .into(binding.imagePeople)
            }
        }

        private fun getFavorite(x: PersonagensItem) {
            when (x.isFavorite) {
                true -> binding.ivFavoriteItem.visibility = View.VISIBLE
                false -> binding.ivFavoriteItem.visibility = View.GONE
            }
        }
    }

    companion object DIFF_CALLBACK {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PersonagensItem>() {
            override fun areItemsTheSame(oldItem: PersonagensItem, newItem: PersonagensItem): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: PersonagensItem, newItem: PersonagensItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
