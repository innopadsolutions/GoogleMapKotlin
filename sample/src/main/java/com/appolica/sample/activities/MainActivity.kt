package com.appolica.sample.activities

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.appolica.sample.R
import kotlinx.android.synthetic.main.activity_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    private var adapter: ActivitiesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        recyclerViewActivities!!.layoutManager = LinearLayoutManager(this)
        recyclerViewActivities!!.setHasFixedSize(true)

        adapter = ActivitiesAdapter()
        recyclerViewActivities!!.adapter = adapter

        val activityClasses = ArrayList<Class<out Activity>>()
        activityClasses.add(MapFragmentActivity::class.java)
        activityClasses.add(MapViewActivity::class.java)

        adapter!!.addData(activityClasses)

    }
}
