package h.app.hackit.demoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import h.app.hackit.demoapp.adapter.ItemsAdapter.ItemsClickListener
import h.app.hackit.demoapp.baseadapter.BaseRecyclerAdapter
import h.app.hackit.demoapp.baseadapter.BaseRecyclerListener
import h.app.hackit.demoapp.baseadapter.BaseViewHolder
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.animation.ObjectAnimator
import h.app.hackit.demoapp.BR
import h.app.hackit.demoapp.model.Item
import h.app.hackit.demoapp.R

class ItemsAdapter(mList: ArrayList<Item>, listener: ItemsClickListener, layoutInflater: LayoutInflater) :
    BaseRecyclerAdapter<Item, ItemsClickListener, ItemsAdapter.ItemsHolder>(mList, listener, layoutInflater) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val view: View = inflate(getLayoutID(viewType), parent, false)
        return ItemsHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.onBind(mList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getLayoutID(pos: Int): Int {
        return R.layout.item_card
    }

    interface ItemsClickListener : BaseRecyclerListener {
        fun onItemClicked(option: Item, position: Int)
    }

    inner class ItemsHolder(itemView: View, listener: ItemsClickListener) :
        BaseViewHolder<Item, ItemsClickListener>(itemView, listener), View.OnClickListener {
        override fun onBind(item: Item) {
            binder.setVariable(BR.item, item)
            binder.executePendingBindings()
        }

        override fun onClick(v: View?) {
            val oa1 = ObjectAnimator.ofFloat(v, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(v, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    oa2.start()
                }
            })
            oa1.start()
            listener.onItemClicked(mList[adapterPosition], adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}