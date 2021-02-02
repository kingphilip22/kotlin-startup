package app.kingphilip.com.kotlincrud

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.kingphilip.com.kotlincrud.db.SubscriberRepository

class SubscriberViewModelFactory(private val repository: SubscriberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
            return SubscriberViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown View Model Class")
    }
}