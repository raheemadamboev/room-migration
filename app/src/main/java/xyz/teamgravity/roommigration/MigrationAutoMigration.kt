package xyz.teamgravity.roommigration

import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

class MigrationAutoMigration {

    @RenameColumn(tableName = "table_user", fromColumnName = "created", toColumnName = "createdAt")
    class Migration2To3 : AutoMigrationSpec
}