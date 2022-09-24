package xyz.teamgravity.roommigration

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    val email: String,
    val username: String,

    @ColumnInfo(name = "createdAt", defaultValue = "0")
    val createdAt: Long = System.currentTimeMillis(),
)
