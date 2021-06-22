package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListEditUser (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val userLD = MutableLiveData<User>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun fetch(uuid: Int) {
        launch {
            val db = buildDb(getApplication())
            userLD.value = db.userDao().selectTodo(uuid)
        }
    }

    fun update(name:String,age:Int,gender:Int, weight:Int, height:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.userDao().update(name,age,gender, weight, height, uuid)
        }
    }
}