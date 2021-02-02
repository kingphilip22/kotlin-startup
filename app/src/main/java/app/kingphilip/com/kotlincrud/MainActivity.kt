package app.kingphilip.com.kotlincrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import app.kingphilip.com.kotlincrud.adapters.SubscribersRecyclerViewAdapter
import app.kingphilip.com.kotlincrud.databinding.ActivityMainBinding
import app.kingphilip.com.kotlincrud.db.Subscriber
import app.kingphilip.com.kotlincrud.db.SubscriberDB
import app.kingphilip.com.kotlincrud.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter: SubscribersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDB.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        initializeRecyclerView()
    }

    private fun initializeRecyclerView(){
        binding.subscribersListRv.layoutManager = LinearLayoutManager(this)
        adapter = SubscribersRecyclerViewAdapter { selectedItem: Subscriber ->
            onListItemClick(
                selectedItem
            )
        }
        binding.subscribersListRv.adapter = adapter
        displaySubscribersList()
    }

    private fun displaySubscribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
            adapter.setList(it)
        })
    }

    private fun onListItemClick(subscriber: Subscriber){
        Toast.makeText(this,"Selected Item: ${subscriber.name}",Toast.LENGTH_SHORT).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}