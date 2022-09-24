package xyz.teamgravity.roommigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class Main : ComponentActivity() {

    private val database: MigrationDatabase by lazy {
        Room.databaseBuilder(applicationContext, MigrationDatabase::class.java, "migration")
            .addMigrations(MigrationMigration.MIGRATION_3_4)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        insertUsers()
//        getUsers()
//        insertSchools()
        getSchools()
    }

    private fun insertUsers() {
        lifecycleScope.launch {
            repeat(10) { index ->
                database.migrationDao().insertUser(
                    UserEntity(
                        email = "test$index@fuse.tea",
                        username = "user-$index"
                    )
                )
            }
        }
    }

    private fun getUsers() {
        lifecycleScope.launch {
            database.migrationDao().getUsers().first().forEach(::println)
        }
    }

    private fun insertSchools() {
        lifecycleScope.launch {
            repeat(5) { index ->
                database.migrationDao().insertSchool(
                    SchoolEntity(
                        id = "school-$index"
                    )
                )
            }
        }
    }

    private fun getSchools() {
        lifecycleScope.launch {
            database.migrationDao().getSchools().first().forEach(::println)
        }
    }
}