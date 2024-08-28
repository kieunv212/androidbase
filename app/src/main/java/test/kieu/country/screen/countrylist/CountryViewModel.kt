package test.kieu.country.screen.countrylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import test.kieu.country.model.Country
import test.kieu.country.usecase.GetAllCountryUseCase
import test.kieu.country.network.Resource
import test.kieu.country.usecase.SearchCountryUseCase
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getAllCountryUseCase: GetAllCountryUseCase,
    private val searchCountryUseCase: SearchCountryUseCase
): ViewModel() {
    private val _countrys = MutableStateFlow(emptyList<Country>())
    val countrys: StateFlow<List<Country>> get() = _countrys

    init {
        getAllCountry()
    }

    fun getAllCountry() {
        viewModelScope.launch {
            try {
                getAllCountryUseCase().onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _countrys.value = result.data!!
                        }
                        is Resource.Error -> {
                            _countrys.value = emptyList<Country>()
                        }
                        is Resource.Loading -> {
                            _countrys.value = emptyList<Country>()
                        }
                    }
                }.launchIn(this)

            } catch (e: Exception) {
                Log.d("getAllCountry",e.message.toString())
            }
        }
    }

    fun searchCountry(common:String) {
        viewModelScope.launch {
            try {
                searchCountryUseCase(common).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _countrys.value = result.data!!
                        }
                        is Resource.Error -> {
                            _countrys.value = emptyList<Country>()
                        }
                        is Resource.Loading -> {
                            _countrys.value = emptyList<Country>()
                        }
                    }
                }.launchIn(this)

            } catch (e: Exception) {
                Log.d("getAllCountry",e.message.toString())
            }
        }
    }

}
