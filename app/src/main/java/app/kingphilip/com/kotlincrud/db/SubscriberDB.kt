package app.kingphilip.com.kotlincrud.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDB : RoomDatabase() {

    abstract val subscriberDao : SubscriberDAO

    companion object {
        @Volatile
        private var INSTANCE : SubscriberDB? = null

        fun getInstance(context: Context) : SubscriberDB{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            SubscriberDB::class.java,
                            "subscriber_db")
                        .build()
                }

                return instance
            }

        }
    }
}