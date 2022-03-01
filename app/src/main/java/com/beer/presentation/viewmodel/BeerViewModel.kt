package com.beer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.beer.domain.common.BaseResult
import com.beer.domain.common.Data
import com.beer.domain.common.Status
import com.beer.domain.dto.Beer
import com.beer.domain.usecase.GetBeersListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val getBeersListUseCase: GetBeersListUseCase
) : BaseViewModel() {

    var beerSelected = Beer()
    private var mutableStateList = MutableLiveData<Data<List<Beer>>>()
    val mainStateList: LiveData<Data<List<Beer>>>
        get() {
            return mutableStateList
        }

    fun onStart(page: Int, perPage: Int) = launch {
        mutableStateList.value = Data(responseType = Status.LOADING)
        when (val result =
            withContext(Dispatchers.IO) { getBeersListUseCase.invoke(page, perPage) }) {
            is BaseResult.Error -> {
                mutableStateList.value =
                    Data(responseType = Status.ERROR, error = result.exception)
            }
            is BaseResult.Success -> {
                mutableStateList.value =
                    Data(responseType = Status.SUCCESS, data = result.data)
            }
        }
    }
}