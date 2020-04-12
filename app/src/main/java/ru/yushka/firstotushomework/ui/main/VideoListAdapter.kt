package ru.yushka.firstotushomework.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.yushka.firstotushomework.R

class VideoListAdapter : RecyclerView.Adapter<VideoListAdapter.ItemVideoHolder>() {
    var items: List<VideoItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemVideoHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_video,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemVideoHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ItemVideoHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvVideoTitle: TextView = view.findViewById(R.id.tvVideoTitle)
        private val tvProductValue: TextView = view.findViewById(R.id.tvProductValue)
        private val tvManufacturerValue: TextView = view.findViewById(R.id.tvManufacturerValue)
        private val tvCategoryValue: TextView = view.findViewById(R.id.tvCategoryValue)

        fun bind(item: VideoItem) {
//            with(item) {
//                tvVideoTitle.text = videoTitle
//                tvCategoryValue.text = category
//                tvManufacturerValue.text = manufacturer
//                tvProductValue.text = product
//            }
        }
    }
}
