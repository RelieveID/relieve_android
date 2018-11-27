package com.relieve.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.relieve.android.R
import com.relieve.android.components.DisasterItem
import com.relieve.android.components.DiscoverItem
import com.relieve.android.components.TitleBarItem
import com.relieve.android.components.adapter.VerticalGridRecycler
import com.relieve.android.rsux.adapter.RvAdapter
import kotlinx.android.synthetic.main.recycler_view_full.view.*

class DiscoverFragment : Fragment() {
    companion object {
        const val NUMBER_OF_COLUMN = 2
    }

    private val adapter = RvAdapter()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.recycler_view_full, container, false).apply {
            this.rvFull.adapter = adapter
            this.rvFull.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        render()
    }

    private fun render() {
        adapter.apply {
            add(DisasterItem(0, 0,"Gunung Semeru Meletus", "Probolinggo, Jawa Timur"))
            add(TitleBarItem("Highlight Bencana", ""))
            add(VerticalGridRecycler(
                listOf(
                    DiscoverItem(0, 0, "Palu", 0, true),
                    DiscoverItem(0, 0, "Lombok", 100, true),
                    DiscoverItem(0, 0, "Jakarta", 200, true),
                    DiscoverItem(0, 0, "Bandung", 300, true),
                    DiscoverItem(0, 0, "Surabaya", 400, true),
                    DiscoverItem(0, 0, "Bali", 500, true),
                    DiscoverItem(0, 0, "Makassar", 600, true),
                    DiscoverItem(0, 0, "Lombok", 700, true),
                    DiscoverItem(0, 0, "Banjarmasin", 800, true),
                    DiscoverItem(0, 0, "Bali", 1_000, true),
                    DiscoverItem(0, 0, "Lombok", 2_000, true),
                    DiscoverItem(0, 0, "Surabaya", 2_500, true),
                    DiscoverItem(0, 0, "Jakarta", 3_000, true),
                    DiscoverItem(0, 0, "Jakarta", 3_010, true),
                    DiscoverItem(0, 0, "Jakarta", 4_000, true)
                ),2) { 1 }
            )
        }
    }
}