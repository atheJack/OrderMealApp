package com.example.meal
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
import com.example.meal.model.Food
import com.example.meal.viewmodel.MealViewModel
import kotlinx.android.synthetic.main.activity_meal.*

class MealActivity: BaseActivity<MealViewModel>() {

    private var adapter: MealFoodListAdapter? = null
    private var fragment: ShoppingDialogFragment = ShoppingDialogFragment()

    override fun onInit() {
        initView()
        initObserver()
    }

    private fun initObserver() {
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
        viewModel.getFoodList()
        tv_go_pay.setOnClickListener {
            Navigation.jump(this, Router.ORDER)
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