package xyz.teamgravity.roommigration

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_school")
data class SchoolEntity(

    @PrimaryKey(autoGenerate = false)
    val id: String,
)
