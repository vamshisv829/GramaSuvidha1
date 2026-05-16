package com.gramasuvidha.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gramasuvidha.R
import com.gramasuvidha.data.Project

class ProjectAdapter(
    private val isKannada: () -> Boolean,
    private val onClick: (Project) -> Unit
) : ListAdapter<Project, ProjectAdapter.VH>(DIFF) {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvBudget: TextView = view.findViewById(R.id.tvBudget)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)
        val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = getItem(position)
        holder.tvTitle.text = if (isKannada()) p.title_kn else p.title
        holder.tvStatus.text = if (isKannada()) p.status_kn else p.status
        holder.tvBudget.text = "₹${"%,d".format(p.budget)}"
        holder.progressBar.progress = p.progress
        holder.tvProgress.text = "${p.progress}%"
        holder.ivThumbnail.load(p.before_image) { crossfade(true) }
        holder.itemView.setOnClickListener { onClick(p) }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(a: Project, b: Project) = a.id == b.id
            override fun areContentsTheSame(a: Project, b: Project) = a == b
        }
    }
}
