package xyz.teamgravity.roommigration

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, SchoolEntity::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = MigrationAutoMigration.Migration2To3::class)
    ]
)
abstract class MigrationDatabase : RoomDatabase() {

    abstract fun migrationDao(): MigrationDao
}