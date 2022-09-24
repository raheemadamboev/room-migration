package xyz.teamgravity.roommigration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_user")
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    val email: String,
    val username: String,
)
