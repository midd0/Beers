package com.beer.presentation.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.beer.domain.common.Data
import com.beer.domain.common.Status
import com.beer.domain.dto.Beer
import com.beer.presentation.view.adapter.BeerAdapter
import com.beer.presentation.view.fragment.BottomSheetDetailsFragment
import com.beer.presentation.viewmodel.BeerViewModel
import com.mido.beers.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

const val MIN_LOADING_TIME = 1000L

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: BeerViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModel()
        init()
    }

    private fun init() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.onStart(1, 80)
        }, MIN_LOADING_TIME)
    }

    private fun observeViewModel() {
        showLoading()
        viewModel.mainStateList.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(beersData: Data<List<Beer>>) {
        when (beersData.responseType) {
            Status.ERROR -> {
                hideLoading()
                beersData.error?.message?.let { showError(it) }
                beersData.data?.let { showBeersList(it) }
            }
            Status.LOADING -> {
                showLoading()
            }
            Status.SUCCESS -> {
                beersData.data?.let { showBeersList(it) }
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        binding.loadingSpinner.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingSpinner.visibility = View.GONE
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showBeersList(beerList: List<Beer>) {
        val adapter = renderListBeers(beerList)

        enableSeachView(adapter)
    }

    private fun renderListBeers(beerList: List<Beer>): BeerAdapter {
        val adapter = BeerAdapter(beerList)

        binding.rvListBeer.layoutManager = LinearLayoutManager(this)
        binding.rvListBeer.setHasFixedSize(true)
        adapter.setOnItemClickListener(itemClickListener())
        binding.rvListBeer.adapter = adapter

        return adapter
    }

    private fun enableSeachView(adapter: BeerAdapter) {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun callClickShowDetail(beer: Beer) {
        viewModel.beerSelected = beer
        BottomSheetDetailsFragment().show(supportFragmentManager, BottomSheetDetailsFragment().tag)
    }

    private fun itemClickListener() = object : BeerAdapter.OnItemClickListener {
        override fun onItemClick(item: Beer) {
            callClickShowDetail(item)
        }
    }
}