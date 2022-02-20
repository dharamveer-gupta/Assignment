package com.dharamveer.assignment.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dharamveer.assignment.R
import com.dharamveer.assignment.model.StateWithCity
import com.google.android.material.radiobutton.MaterialRadioButton


/**
 * Created by Dharamveer Gupta on 20-February-2022 4:54 PM,
 * dharamveer.gupt@gmail.com,
 * Roundr,
 * Navi Mumbai, Maharashtra, India.
 */
class CityAdapter(
    private var cities: MutableList<StateWithCity>,
    listener: (Boolean) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>(), Filterable {

    /*private val differCallback = object : DiffUtil.ItemCallback<StateWithCity>() {
        override fun areItemsTheSame(oldItem: StateWithCity, newItem: StateWithCity): Boolean {
            return oldItem.city == newItem.city
        }

        override fun areContentsTheSame(oldItem: StateWithCity, newItem: StateWithCity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)*/

    private var citySelected: String = ""

    private var filteredCities = mutableListOf<StateWithCity>()

    private var mListener : (Boolean) -> Unit
    init {
        filteredCities = cities
        mListener = listener
    }


    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val txtCityName: TextView = itemView.findViewById(R.id.textViewCityName)
        private val radioButton: MaterialRadioButton = itemView.findViewById(R.id.radioButton)
        fun bind(model: StateWithCity?, position: Int, listener: (Boolean) -> Unit) {
            model?.let {
                txtCityName.text = it.toString()

                if (citySelected == model.city) {
                    itemView.isSelected = true
                    radioButton.isChecked = true
                } else {
                    radioButton.isChecked = false
                    itemView.isSelected = false
                }

                itemView.setOnClickListener {
                    model.isChecked = true
                    citySelected = model.city
                    notifyDataSetChanged()
                    mListener(true)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val viewInflater = LayoutInflater.from(parent.context).inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(viewInflater)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(filteredCities[position], position, mListener)
    }

    override fun getItemCount(): Int = filteredCities.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filteredCities = if (charSearch.isEmpty()) {
                    cities
                } else {
                    val resultList = mutableListOf<StateWithCity>()
                    for (row in cities) {
                        /**
                         * Note - If requirement is to search by both City & State name then
                         * row.toString().lowercase().contains(charSearch.lowercase())
                         * */

                        //Search only by City name
                        if (row.city.lowercase().contains(charSearch.lowercase())) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredCities
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredCities = results?.values as MutableList<StateWithCity>
                notifyDataSetChanged()
            }
        }
    }

    /*fun updateItems(items: MutableList<StateWithCity>?) {
        cities.addAll(items ?: mutableListOf())
        notifyDataSetChanged()
    }*/

}