package id.ac.ubaya.informatika.finaltermproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodJurnal

class ListViewModel {
    val foodLD = MutableLiveData<List<FoodJurnal>>()
    val foodLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {

        val studentList:ArrayList<FoodJurnal> = arrayListOf<FoodJurnal>()
        foodLD.value = studentList
        foodLoadErrorLD.value = false
        loadingLD.value = false

    }


}