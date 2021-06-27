package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLogDatabase
import id.ac.ubaya.informatika.finaltermproject.view.model.Report
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import id.ac.ubaya.informatika.finaltermproject.view.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListReportViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val reportLD = MutableLiveData<List<Report>>()
    val reportLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
    get() = job +Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        reportLoadErrorLD.value=false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),FoodLogDatabase::class.java, "newfoodlogdb"
            ).build()
            reportLD.value=db.reportDao().selectAllReport()
        }
    }

    fun insertReport(list: List<Report>){
        launch {
            val db = buildDb(getApplication())
            db.reportDao().insertAll(*list.toTypedArray())
        }
    }

    fun update(date:String, calories:Int, status:String, meal:Int) {
        launch {
            val db = buildDb(getApplication())
            db.reportDao().update(date, calories, status, meal)
        }
    }
}