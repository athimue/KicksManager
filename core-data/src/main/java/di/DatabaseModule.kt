package di

import android.content.Context
import androidx.room.Room
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
        builder.build()
    }

    @Singleton
    @Provides
    fun provideSellDao(database: Database): SellDao = database.sellDao()
}