package com.relieve.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.R
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class ChatFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view_full, container, false).apply {
            //            adapter = Adapter(context)
            this.rvHome.layoutManager = LinearLayoutManager(context)
//            rvHome.adapter = adapter
        }
    }
}