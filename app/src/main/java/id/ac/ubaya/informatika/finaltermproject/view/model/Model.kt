package id.ac.ubaya.informatika.finaltermproject.view.model

import androidx.room.Entity
import java.util.*

@Entity
data class User(
    val name:String?,
    val age:Int?,
    val gender:String?,
    val weight:Int?,
    val height:Int?,
    val personalGoal:Int?
)

@Entity
data class FoodLog(
    val meal:String?,
    val calories:Int?,
    val date:Date?
)