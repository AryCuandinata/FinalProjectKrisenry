package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog

class FoodLogAdapter(val foodLogList: ArrayList<FoodLog>):RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {
    class FoodLogViewHolder(val view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.food_log_item_list, parent, false)
        return FoodLogViewHolder(v)
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }
}