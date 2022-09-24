package xyz.teamgravity.roommigration

import androidx.room.Room
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DB_NAME = "test"

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    @get:Rule
    val helper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        MigrationDatabase::class.java,
        listOf(MigrationAutoMigration.Migration2To3()),
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migration1To2_containsCorrectData() {
        var db = helper.createDatabase(DB_NAME, 1)
        db.execSQL("INSERT INTO table_user VALUES('test@test.com', 'Raheem')")
        db.close()

        db = helper.runMigrationsAndValidate(DB_NAME, 2, true)

        db.query("SELECT * FROM table_user").apply {
            assertThat(moveToFirst()).isTrue()
            assertThat(getLong(getColumnIndex("created"))).isEqualTo(0)
        }
    }

    @Test
    fun testAllMigrations() {
        helper.createDatabase(DB_NAME, 1).apply { close() }

        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MigrationDatabase::class.java,
            DB_NAME
        ).addMigrations(MigrationMigration.MIGRATION_3_4).build().apply {
            openHelper.writableDatabase.close()
        }
    }
}