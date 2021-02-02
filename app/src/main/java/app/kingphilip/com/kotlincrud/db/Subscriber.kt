package app.kingphilip.com.kotlincrud.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subscribers_table")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)

//    @ColumnInfo(name = "sub_id") - naming table columns
    var id: Int,
    var name: String,
    var email: String
)
