package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.*

@Dao
interface FoodLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg foodLog: FoodLog)

    @Query("SELECT * FROM foodlog")
    suspend fun selectAllFoodLog(): List<FoodLog>

    @Query("SELECT * FROM foodlog WHERE date=:date")
    suspend fun selectAllFoodLog(date:String): List<FoodLog>

    @Delete
    suspend fun deleteFoodLog(foodLog: FoodLog)
}

