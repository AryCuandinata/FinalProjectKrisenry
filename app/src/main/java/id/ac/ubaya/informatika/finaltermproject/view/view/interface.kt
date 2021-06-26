package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.User

interface TodoEditClick {
    fun onTodoEditClick(v: View)
}

interface RadioClick {
    fun onRadioClick(v: View, priority: Int, obj: User)
}

interface RadioClickGender {
    fun onRadioClickGender(v: View, gender: Int, obj: User)
}



interface ProfileSaveChangesClick {
    fun onProfileSaveChangesClick(v: View, obj: User)
}

interface FabListener {
    fun onFabListener(v: View)
}

interface StartJourneyClick {
    fun onStartJourneyClick(v: View)
}
