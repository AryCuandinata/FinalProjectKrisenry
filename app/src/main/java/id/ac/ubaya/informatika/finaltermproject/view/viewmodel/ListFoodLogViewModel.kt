package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLogDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListFoodLogViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val foodLD = MutableLiveData<List<FoodLog>>()
    val foodLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun logAMeal(list: List<FoodLog>){
        launch {
            val db = Room.databaseBuilder(
                getApplication(),FoodLogDatabase::class.java,"newfoodlogdb").build()

            db.foodLogDao().insertAll(*list.toTypedArray())
        }
    }

    fun refresh() {
        foodLoadErrorLD.value = false
        loadingLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(), FoodLogDatabase::class.java, "newfoodlogdb"
            ).build()

            foodLD.value = db.foodLogDao().selectAllFoodLog()
        }

    }

    fun clearTask(foodLog: FoodLog) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), FoodLogDatabase::class.java, "newfoodlogdb"
            ).build()
            db.foodLogDao().deleteFoodLog(foodLog)

            foodLD.value = db.foodLogDao().selectAllFoodLog()
        }
    }


}