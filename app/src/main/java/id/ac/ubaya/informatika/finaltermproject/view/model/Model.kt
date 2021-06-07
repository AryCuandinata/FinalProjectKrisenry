package id.ac.ubaya.informatika.finaltermproject.view.model

import java.util.*

data class User(
    val name:String?,
    val age:Int?,
    val gender:String?,
    val weight:Int?,
    val height:Int?,
    val personalGoal:Int?
)

data class FoodJurnal(
    val meal:String?,
    val calories:Int?,
    val date:Date?
)