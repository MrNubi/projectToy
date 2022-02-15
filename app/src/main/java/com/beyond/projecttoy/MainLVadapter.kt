package com.beyond.projecttoy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MainLVadapter(val Lvlist : MutableList<LvModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return Lvlist.size
    }

    override fun getItem(p0: Int): Any {
        return Lvlist[p0]

    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, c1: View?, p2: ViewGroup?): View {


        var view = c1
        if (c1 == null) {

            view =
                LayoutInflater.from(p2?.context).inflate(R.layout.main_lv_item, p2, false)



        }

        val content = view?.findViewById<TextView>(R.id.listViewContentArea)
        val time = view?.findViewById<TextView>(R.id.listViewTimeArea)
        val title = view?.findViewById<TextView>(R.id.listViewTitleArea)

        time!!.text = Lvlist[p0].time
        content!!.text = Lvlist[p0].content
        title!!.text = Lvlist[p0].title




        return view!!



    }
}