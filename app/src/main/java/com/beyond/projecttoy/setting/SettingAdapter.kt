package com.beyond.projecttoy.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.beyond.projecttoy.R
import com.beyond.projecttoy.comment.CommentModel

class SettingAdapter (val iconCodeList : MutableList<String>) : BaseAdapter() {


        override fun getCount(): Int {
            return iconCodeList.size
        }

        override fun getItem(position: Int): Any {
            return iconCodeList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var view = convertView

            if (view == null) {
                view = LayoutInflater.from(parent?.context).inflate(R.layout.setting_item, parent, false)
            }

            val setter = view?.findViewById<TextView>(R.id.setter)
            val time = view?.findViewById<TextView>(R.id.timeArea)

            setter!!.text = iconCodeList[position]


            return view!!
        }

    }


