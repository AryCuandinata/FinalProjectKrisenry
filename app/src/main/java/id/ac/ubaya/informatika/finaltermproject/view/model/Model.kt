package id.ac.ubaya.informatika.finaltermproject.view.model

import java.util.*

data class Student(
    val name:String?,
    val age:Int?,
    val gender:String?,
    val weight:Int?,
    val height:Int?,
    val personalGoal:Int?
)

data class FoodLog(
    val meal:String?,
    val calories:Int?,
    val date:Date?
)