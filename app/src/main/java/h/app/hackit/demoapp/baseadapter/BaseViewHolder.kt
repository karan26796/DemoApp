package h.app.hackit.demoapp.baseadapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.view.View

abstract class BaseViewHolder<T, L : BaseRecyclerListener>
(itemView: View, protected var listener: L)
    : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    var binder: ViewDataBinding = DataBindingUtil.bind(itemView)!!
    abstract fun onBind(item: T)
}