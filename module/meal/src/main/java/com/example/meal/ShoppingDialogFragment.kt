package com.example.meal
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseFragment
import com.example.common.BaseViewModel
import com.example.common.util.FragmentUtil
import com.example.common.util.LogUtil
import com.example.meal.adapter.ShopCartFoodListAdapter
import com.example.common.model.Food
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingDialogFragment: BaseFragment<BaseViewModel>() {

    private var shopCartFoodListAdapter: ShopCartFoodListAdapter? = null
    private var list: ArrayList<Food> = ArrayList()

    override fun createVm(): BaseViewModel {
        return BaseViewModel()
    }

    override fun onInit() {
        //设置整个layout可点击来屏蔽被覆盖的activity的点击
        try {
            fl_shop_container.setOnClickListener {
                requireActivity().let {
                    FragmentUtil.hideFragment(it, this)
                }
            }
            if(shopCartFoodListAdapter == null) {
                shopCartFoodListAdapter = ShopCartFoodListAdapter(requireContext(), list)
                rv_shop_food_list.layoutManager = LinearLayoutManager(requireContext())
                rv_shop_food_list.adapter = shopCartFoodListAdapter
                if(list.isEmpty()) {
                    rv_shop_food_list.visibility = View.GONE
                    iv_no_food.visibility = View.VISIBLE
                } else {
                    rv_shop_food_list.visibility = View.VISIBLE
                    iv_no_food.visibility = View.GONE
                }
            }
        } catch (e: IllegalStateException) {
            e.message?.let { LogUtil.d(it) }
        }
    }

    fun updateList(newData: List<Food>) {
        if (newData.isEmpty()) {
            rv_shop_food_list.visibility = View.GONE
            iv_no_food.visibility = View.VISIBLE
        } else {
            shopCartFoodListAdapter?.let {
                it.data = newData
                it.notifyDataSetChanged()
            }
            rv_shop_food_list.visibility = View.VISIBLE
            iv_no_food.visibility = View.GONE
        }
    }

    fun setInitListData(newData: List<Food>) {
        list.addAll(newData)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_shopping_cart
    }
}