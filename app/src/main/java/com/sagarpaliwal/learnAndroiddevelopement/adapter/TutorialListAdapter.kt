package com.sagarpaliwal.learnAndroiddevelopement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.fragment.TutorialListFragment
import com.sagarpaliwal.learnAndroiddevelopement.model.Tutorials
 /************************************* Adapter  For RecyclerView **************************************************/
class TutorialListAdapter(private val listener: TutorialListFragment, private val items: MutableList<Tutorials>) : RecyclerView.Adapter<TutorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.customlayout, parent, false)
       val viewHolder =  TutorialViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        val currentItem: Tutorials = items[position]
        holder.titleView.text = currentItem.Heading
     }

    override fun getItemCount(): Int {
        return items.size
    }


}

class TutorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.textViewmain)
 }
interface TutorialItemClicked {
    fun onItemClicked(item: Tutorials)
}