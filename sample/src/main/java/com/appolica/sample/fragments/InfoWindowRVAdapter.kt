package com.appolica.sample.fragments

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.appolica.sample.R

import java.util.ArrayList

/**
 * Created by Bogomil Kolarov on 22.09.16.
 * Copyright © 2016 Appolica. All rights reserved.
 */
class InfoWindowRVAdapter : RecyclerView.Adapter<InfoWindowRVAdapter.ItemViewHolder>() {

    private val data = ArrayList<String>()

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

         val textView: TextView

        init {

            textView = itemView.findViewById<View>(R.id.listItemTextView) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addData(data: List<String>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addItem(item: String) {
        data.add(item)
        notifyDataSetChanged()
    }

}
