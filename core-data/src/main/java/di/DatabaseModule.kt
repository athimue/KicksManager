package di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import database.dao.SellDao
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /** v1 -> v2 : ajout des frais de vente (les ventes existantes gardent 0 € de frais). */
    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE sell ADD COLUMN fees REAL NOT NULL DEFAULT 0")
        }
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): Database = runBlocking {
        val builder = Room.databaseBuilder(
            context,
            Database::class.java,
            Database.DATABASE_NAME
        )
        builder.enableMultiInstanceInvalidation()
        builder.addMigrations(MIGRATION_1_2)
        builder.build()
    }

    @Singleton
    @Provides
    fun provideSellDao(database: Database): SellDao = database.sellDao()
}