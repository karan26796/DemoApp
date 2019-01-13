package h.app.hackit.demoapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import h.app.hackit.demoapp.R
import h.app.hackit.demoapp.adapter.ItemsAdapter
import h.app.hackit.demoapp.model.Item
import h.app.hackit.demoapp.utils.MarginItemDecoration
import jp.wasabeef.recyclerview.animators.LandingAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ItemsAdapter.ItemsClickListener {

    override fun onItemClicked(option: Item, position: Int) {
        list.remove(option)
        recyclerItems.adapter!!.notifyItemRemoved(position)
    }

    private val list: ArrayList<Item> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        menu!!.getItem(0).isVisible = list.size == 0
        return super.onCreateOptionsMenu(menu)
    }

    @SuppressLint("WrongConstant")
    private fun setList() {
        for (i in 1..20) {
            list.add(Item(i.toString(), null))
        }

        recyclerItems.layoutManager = GridLayoutManager(
            this, getGridSize(),
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerItems.adapter = ItemsAdapter(list, this, layoutInflater)
        recyclerItems.itemAnimator = LandingAnimator()
        recyclerItems.addItemDecoration(MarginItemDecoration(getGridSpacing()))
        recyclerItems.itemAnimator?.apply {
            addDuration = animationSpeed.toLong()
            removeDuration = animationSpeed.toLong()
            moveDuration = animationSpeed.toLong()
            changeDuration = animationSpeed.toLong()
        }
        //recyclerItems.adapter!!.setHasStableIds(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_refresh -> {
                if (list.size == 0)
                    setList()
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
