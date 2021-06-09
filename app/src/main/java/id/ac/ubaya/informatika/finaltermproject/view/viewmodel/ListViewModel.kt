package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLogDatabase

class ListViewModel {
    val foodLD = MutableLiveData<List<FoodLog>>()
    val foodLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {

        val studentList:ArrayList<FoodLog> = arrayListOf<FoodLog>()
        foodLD.value = studentList
        foodLoadErrorLD.value = false
        loadingLD.value = false

    }


}