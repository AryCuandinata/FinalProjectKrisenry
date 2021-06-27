package id.ac.ubaya.informatika.finaltermproject.view.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodLogItemListBinding
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.User

class FoodLogAdapter(val foodLogList: ArrayList<FoodLog>, val userList: ArrayList<User>):RecyclerView.Adapter<FoodLogAdapter.FoodLogViewHolder>() {

    class FoodLogViewHolder(val view: FoodLogItemListBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val v = inflater.inflate(R.layout.food_log_item_list, parent, false)
        val v = DataBindingUtil.inflate<FoodLogItemListBinding>(inflater,R.layout.food_log_item_list,parent,false)
        return FoodLogViewHolder(v)
    }

    override fun getItemCount(): Int {
        return foodLogList.size
    }

    fun updateFoodLogList(newFoodLogList: List<FoodLog>) {
        foodLogList.clear()
        foodLogList.addAll(newFoodLogList)
        notifyDataSetChanged()
    }

    fun updateFoodLogListUser(newFoodLogListUser: List<User>) {
        userList.clear()
        userList.addAll(newFoodLogListUser)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FoodLogViewHolder, position: Int) {
        holder.view.foodlog = foodLogList[position]
        /*
        holder.view.txtName.setText(foodLogList[position].meal.toString())
        holder.view.txtCalories.setText(foodLogList[position].calories.toString())
        */
    }
}