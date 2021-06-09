package id.ac.ubaya.informatika.finaltermproject.view.util

import android.content.Context
import androidx.room.Room
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLogDatabase

val DB_NAME = "newfoodlogdb"
fun buildDb(context: Context):FoodLogDatabase {
    val db = Room.databaseBuilder(context, FoodLogDatabase::class.java, DB_NAME)
        .build()
    return db
}
