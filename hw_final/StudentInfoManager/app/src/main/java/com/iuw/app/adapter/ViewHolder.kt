package com.iuw.app.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iuw.app.R
import kotlinx.android.synthetic.main.item_sutdent.view.*


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var icon: ImageView = itemView.findViewById(R.id.icon)
    var name: TextView = itemView.findViewById(R.id.name)
    var professional: TextView = itemView.findViewById(R.id.professional)
    var grade: TextView = itemView.findViewById(R.id.grade)
    var edit: ImageView = itemView.findViewById(R.id.edit)
}