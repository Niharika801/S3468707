package uk.ac.tees.mad.token.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.token.data.model.TokenData
import uk.ac.tees.mad.token.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {

    private val _cryptoList = MutableStateFlow<List<TokenData>>(emptyList())
    val cryptoList:StateFlow<List<TokenData>> get() = _cryptoList

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        Log.e("Init HomeViewModel", "HomeViewModel Initialised")
        fetchCryptoData()
    }

    private fun fetchCryptoData() {
        viewModelScope.launch {
            try {
                _cryptoList.value = repository.getTokenData()
                Log.e("CryptoList Size", "Size:${_cryptoList.value.size}")
            } catch (e: Exception) {
                _error.value = "Error fetching data: ${e.message}"
            }
        }
    }
}