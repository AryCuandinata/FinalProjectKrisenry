package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FoodLogDao {
    @Dao
    interface TodoDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(vararg todo:FoodLog)
        @Query("SELECT * FROM foodlog")
        suspend fun selectAllTodo(): List<FoodLog>
    }
}