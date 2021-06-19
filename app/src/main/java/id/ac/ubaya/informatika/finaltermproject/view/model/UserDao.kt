package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user: User)

    @Query("SELECT * FROM user")
    suspend fun selectAllUser(): List<User>

    @Query("UPDATE user SET name=:name, age=:age, gender=:gender, weight=:weight, height=:height, personalGoal=:personalGoal WHERE uuid=:uuid")
    suspend fun update(name:String,age:Int,gender:String, weight:Int, height:Int, personalGoal:Int, uuid:Int)

}