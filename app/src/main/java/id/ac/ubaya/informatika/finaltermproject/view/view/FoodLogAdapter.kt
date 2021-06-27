package id.ac.ubaya.informatika.finaltermproject.view.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.finaltermproject.R
import id.ac.ubaya.informatika.finaltermproject.databinding.FoodLogItemListBinding
import id.ac.ubaya.informatika.finaltermproject.view.model.FoodLog
import id.ac.ubaya.informatika.finaltermproject.view.model.User
import kotlinx.android.synthetic.main.food_log_item_list.view.*
import kotlinx.android.synthetic.main.fragment_food_log.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun updateFoodLogList(newTodoList: List<FoodLog>) {
        foodLogList.clear()
        foodLogList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    fun updateFoodLogListUser(newTodoListUser: List<User>) {
        userList.clear()
        userList.addAll(newTodoListUser)
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