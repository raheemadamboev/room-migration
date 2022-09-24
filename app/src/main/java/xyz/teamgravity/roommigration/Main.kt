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
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUsers()
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
}