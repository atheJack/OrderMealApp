package com.example.meal
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.common.network.CommonResponse
import com.example.common.recyclelist.CommonItemClickListener
import com.example.common.router.Navigation
import com.example.common.router.Router
import com.example.common.util.FragmentUtil
import com.example.common.util.ToastUtil
import com.example.meal.adapter.MealFoodListAdapter
import com.example.common.model.Food
import com.example.common.recyclelist.TypeAdapter
import com.example.meal.viewmodel.MealViewModel
import kotlinx.android.synthetic.main.activity_meal.*

class MealActivity: BaseActivity<MealViewModel>() {

    private var adapter: MealFoodListAdapter? = null
    private var typeAdapter: TypeAdapter? = null
    private var fragment: ShoppingDialogFragment = ShoppingDialogFragment()

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.foodTypeData.observe(this, {
            if(it.code == 200){
                viewModel.getFoodList()
                initTypeAdapter(getList(it.data))
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
        viewModel.foodData.observe(this, {
            if (it.code == 200) {
                initAdapter(it)
            } else {
                ToastUtil.showToastShort(this, it.message)
            }
        })
        viewModel.totalPrice.observe(this, {
            tv_sum_price.text = "￥${it}"
        })
        viewModel.shopCartFoodData.observe(this, {
            if(fragment.isAdded) {
                fragment.updateList(it)
            }
        })
    }

    private fun initView() {
        viewModel.getFoodTypeList()
        tv_go_order.setOnClickListener {
            Navigation.jumpWithBundle(this, Router.ORDER_DETAIL, Bundle().apply {
                putSerializable("food_list", viewModel.shopCartFoodData.value)
                putInt("launch_mode", 1)
                viewModel.totalNum.value?.let { num -> putInt("total_num", num) }
                viewModel.totalPrice.value?.let { price -> putFloat("total_price", price) }
            })
        }
        iv_open_shopping_cart.setOnClickListener {
            if (!fragment.isAdded) {
                viewModel.shopCartFoodData.value?.let {
                        data -> fragment.setInitListData(data)
                }
                FragmentUtil.addFragment(this, fragment, R.id.fc_shopping_cart_detail, "")
            } else if(!fragment.isHidden) {
                FragmentUtil.hideFragment(this, fragment)
            } else if(fragment.isHidden) {
                FragmentUtil.showFragment(this, fragment)
            }
        }
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initTypeAdapter(list: List<Int>) {
        if(typeAdapter == null) {
            typeAdapter = TypeAdapter(this, list, 0)
            typeAdapter!!.listener = object:CommonItemClickListener<Int>{
                override fun onClick(itemView: View, data: List<Int>, position: Int) {
                    itemView.setOnClickListener {
                        typeAdapter!!.selectPosition = position
                        typeAdapter!!.notifyDataSetChanged()
                        showTypeFoodData(data, position)
                    }
                }
            }
            val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            rv_type_list.layoutManager = layoutManager
            rv_type_list.adapter = typeAdapter
        } else {
            typeAdapter!!.notifyDataSetChanged()
        }
    }

    private fun showTypeFoodData(data: List<Int>, position: Int) {
        val foodList = viewModel.foodData.value?.data
        if(!foodList.isNullOrEmpty()) {
            val type = data[position]
            val newFoodList = ArrayList<Food>()
            when(type){
                -1 -> {
                    newFoodList.addAll(foodList)
                }
                else -> {
                    for(food in foodList) {
                        if(food.type == type) {
                            newFoodList.add(food)
                        }
                    }
                }
            }
            adapter?.let {
                adapter!!.data = newFoodList
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    private fun getList(list: List<Int>) : List<Int>{
        val arrayList = ArrayList<Int>()
        arrayList.add(-1)
        arrayList.addAll(list)
        return arrayList
    }

    private fun initAdapter(commonResponse: CommonResponse<List<Food>>) {
        if (adapter == null) {
            adapter = MealFoodListAdapter(this,commonResponse.data)
            adapter?.listener = object: CommonItemClickListener<Food>{
                override fun onClick(
                    itemView: View,
                    data: List<Food>,
                    position: Int
                ) {
                    val addFood = itemView.findViewById<ImageView>(R.id.iv_food_add)
                    val reduceFood = itemView.findViewById<ImageView>(R.id.iv_food_reduce)
                    addFood.setOnClickListener {
                        data[position].num = data[position].num + 1
                        viewModel.addFood(data[position])
                        adapter!!.notifyDataSetChanged()
                    }
                    reduceFood.setOnClickListener {
                        if(data[position].num > 0) {
                            data[position].num--
                            viewModel.reduceFood(data[position])
                            adapter!!.notifyDataSetChanged()
                        }
                    }
                }
            }
            val layoutManager = LinearLayoutManager(this)
            rv_meal_list.layoutManager = layoutManager
            rv_meal_list.adapter = adapter
        } else {
            adapter?.notifyDataSetChanged()
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_meal
    }

    override fun createVm(): MealViewModel {
        return MealViewModel()
    }
}