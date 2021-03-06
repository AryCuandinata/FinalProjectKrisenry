package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLogDatabase
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListUserViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    val userLD = MutableLiveData<List<User>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val userloadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    fun insertUser(list: List<User>){
        launch {
            val db = buildDb(getApplication())
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun update(name:String,age:Int,gender:Int, weight:Int, height:Int, bmr:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().update(name,age,gender,weight,height,bmr, uuid)
        }
    }


    fun fetch(uuid: Int){
        launch {
            val db = buildDb(getApplication())
            userLD.value = db.userDao().selectAllUser()
        }
    }



    fun refresh() {
        userLoadErrorLD.value = false
        userloadingLD.value = false
        launch {
            val db = buildDb(getApplication())

            userLD.value = db.userDao().selectAllUser()
        }
    }


}