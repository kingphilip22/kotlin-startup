package app.kingphilip.com.kotlincrud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.kingphilip.com.kotlincrud.R
import app.kingphilip.com.kotlincrud.databinding.SubscribersListItemBinding
import app.kingphilip.com.kotlincrud.db.Subscriber
import java.util.ArrayList

class SubscribersRecyclerViewAdapter(private val onItemClickListener:(Subscriber)->Unit)
    : RecyclerView.Adapter<SubscribersRecyclerViewAdapter.MyViewHolder>() {

    private val subscribers = ArrayList<Subscriber>()

    class MyViewHolder(val binding: SubscribersListItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(subscriber: Subscriber, onItemClickListener:(Subscriber)->Unit){
            binding.nameContent.text = subscriber.name
            binding.emailContent.text = subscriber.email
            binding.listItemLayout.setOnClickListener{
                onItemClickListener(subscriber)
            }
        }
    }

    fun setList(subscribersList: List<Subscriber>){
        subscribers.clear()
        subscribers.addAll(subscribersList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: SubscribersListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.subscribers_list_item,parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribers[position],onItemClickListener)
    }

    override fun getItemCount(): Int {
        return subscribers.size
    }
}