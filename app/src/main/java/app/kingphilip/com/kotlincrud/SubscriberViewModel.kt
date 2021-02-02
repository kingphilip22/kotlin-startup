package app.kingphilip.com.kotlincrud

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.kingphilip.com.kotlincrud.db.Subscriber
import app.kingphilip.com.kotlincrud.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel(),Observable {

    val subscribers = repository.subscribers
    private var isUpdateOrDelete =false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButton = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            subscriberToUpdateOrDelete.name = inputName.value!!
            subscriberToUpdateOrDelete.email = inputEmail.value!!
            update(subscriberToUpdateOrDelete)
        }else{
            val name : String = inputName.value!!
            val email : String = inputEmail.value!!
            insert(Subscriber(0,name,email))
            inputName.value = null
            inputEmail.value = null
        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            clearAll()
        }
    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch {
        repository.insert(subscriber)
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        repository.update(subscriber)
    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        repository.delete(subscriber)
        setInitialValues()
    }

    fun clearAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButton.value = "Update"
        clearAllOrDeleteButton.value = "Delete"
    }

    private fun setInitialValues(){
        inputName.value = null
        inputEmail.value = null
        isUpdateOrDelete = false
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}