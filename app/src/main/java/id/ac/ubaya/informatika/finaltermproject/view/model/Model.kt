package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class User(
    @ColumnInfo(name="name")
    var name:String?,
    @ColumnInfo(name="age")
    var age:Int?,
    @ColumnInfo(name="gender")
    var gender:Int?,
    @ColumnInfo(name="weight")
    var weight:Int?,
    @ColumnInfo(name="height")
    var height:Int?,
    @ColumnInfo(name="personalGoal")
    var personalGoal:Int?,
    @ColumnInfo(name="bmr")
    var bmr:Int?
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity
data class FoodLog(
    @ColumnInfo(name="meal")
    var meal:String?,
    @ColumnInfo(name="calories")
    var calories:Int?,
    @ColumnInfo(name="date")
    var date:String?
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

@Entity
data class Report(
    @ColumnInfo(name="Date")
    var date:String,
    @ColumnInfo(name="Calories")
    var calories: Int?,
    @ColumnInfo(name="Status")
    var status: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}