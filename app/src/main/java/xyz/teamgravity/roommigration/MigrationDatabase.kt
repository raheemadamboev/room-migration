package xyz.teamgravity.roommigration

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [UserEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
//        AutoMigration(from = 2, to = 3, spec = MigrationDatabase.Migration2To3::class)
    ]
)
abstract class MigrationDatabase : RoomDatabase() {

    abstract fun migrationDao(): MigrationDao

    @RenameColumn(tableName = "table_user", fromColumnName = "created", toColumnName = "createdAt")
    class Migration2To3: AutoMigrationSpec
}