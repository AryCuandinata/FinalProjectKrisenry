package id.ac.ubaya.informatika.finaltermproject.view.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FoodLog::class,Report::class), version = 1)
abstract class FoodLogDatabase: RoomDatabase(){
    abstract fun foodLogDao(): FoodLogDao
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile
        private var instance: FoodLogDatabase? = null
        private val LOCK = Any()
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FoodLogDatabase::class.java,
                "newfoodlogdb"
            ).build()

        operator fun invoke(context: Context) {
            if (instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }

}