package test.kieu.country.screen.countrydetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import test.kieu.country.model.Country
import test.kieu.country.model.Flag
import test.kieu.country.model.Name
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _country: MutableStateFlow<Country> = MutableStateFlow(Country(Flag("","",""),Name("","")))
    val country: StateFlow<Country> get() = _country

    init {
        savedStateHandle.get<String>("countryDetail")?.let {
            val countryDetail = Gson().fromJson(it, Country::class.java)
            _country.value = countryDetail
        }
    }
}

