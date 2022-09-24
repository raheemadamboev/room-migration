package xyz.teamgravity.roommigration

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class MigrationDatabase : RoomDatabase() {

    abstract fun migrationDao(): MigrationDao
}