package com.appolica.sample.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.appolica.sample.R
import kotlinx.android.synthetic.main.info_window_form_fragment.*

class FormFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.info_window_form_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onClickListener = View.OnClickListener { Toast.makeText(context,
                "Well I don't have any...", Toast.LENGTH_SHORT).show() }

        button1.setOnClickListener(onClickListener)
        button2.setOnClickListener(onClickListener)
    }
}
