package com.beer.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.beer.domain.dto.Beer
import com.mido.beers.R
import com.mido.beers.databinding.SingleItemBinding
import java.util.*

class BeerAdapter(val beersList: List<Beer>) :
    RecyclerView.Adapter<BeerAdapter.PunkViewHolder>(), Filterable {

    private lateinit var onItemClickListener: OnItemClickListener
    private var beersFilterList: MutableList<Beer>

    init {
        beersFilterList = beersList.toMutableList()
    }

    interface OnItemClickListener {
        fun onItemClick(item: Beer)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PunkViewHolder {
        val binding =
            SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PunkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PunkViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                with(beersFilterList[position]) {
                    tvName.text = this.name
                    tvDesc.text = this.tagline
                    imgCircleBeer.setImageResource(R.drawable.ic_beer)
                    root.setOnClickListener {
                        onItemClickListener.onItemClick(this)
                    }
                }
            }
        }
    }

    override fun getItemCount() = beersFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charSearch = charSequence.toString()
                beersFilterList = if (charSearch.isEmpty()) {
                    beersList.toMutableList()
                } else {
                    beersList.filter {
                        it.name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))
                    }.toMutableList()
                }

                return FilterResults().apply {
                    values = beersFilterList
                    count = beersFilterList.size
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }

    inner class PunkViewHolder(val binding: SingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}