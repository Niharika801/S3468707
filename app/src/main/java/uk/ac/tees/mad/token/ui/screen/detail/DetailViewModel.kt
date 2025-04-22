package uk.ac.tees.mad.token.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.token.data.DataStoreManager
import uk.ac.tees.mad.token.data.model.DetailData
import uk.ac.tees.mad.token.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: Repository,
    private val dataStoreManager: DataStoreManager
):ViewModel() {

    private val _tokenDetail = MutableStateFlow<DetailData?>(null)
    val tokenDetail:StateFlow<DetailData?> get() = _tokenDetail

    private val _selectedCurrency = MutableStateFlow("usd")
    val selectedCurrency:StateFlow<String> get() = _selectedCurrency

     fun getDetailData(id:String){
        viewModelScope.launch {
            try {
                _tokenDetail.value = repository.getTokenDetails(id, _selectedCurrency.value)
            }catch (e:Exception){
                Log.e("Error in getting detail data", e.message.toString())
            }
        }

         viewModelScope.launch {
             dataStoreManager.selectedCurrencyFlow
                 .collect{_selectedCurrency.value = it}
         }
    }
}