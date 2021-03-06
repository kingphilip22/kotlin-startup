package app.kingphilip.com.kotlincrud.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscribers_table")
    suspend fun deleteAllSubscribers()

    @Query("SELECT * FROM subscribers_table")
    fun getAllSubscribers() : LiveData<List<Subscriber>>
}