package ru.yushka.firstotushomework.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.yushka.firstotushomework.R

class MainFragment : Fragment() {

    private lateinit var vm: MainViewModel
    private var rvList: RecyclerView? = null
    private var flError: FrameLayout? = null
    private var flProgress: FrameLayout? = null
    private var fabUp: FloatingActionButton? = null
    private val adapter get() = rvList?.adapter as VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList = view.findViewById(R.id.rvList)
        fabUp = view.findViewById(R.id.fabUp)
        flError = view.findViewById(R.id.flError)
        flProgress = view.findViewById(R.id.flProgress)

        rvList?.apply {
            adapter = VideoListAdapter()
            addItemDecoration(
                DefaultItemDecoration(
                    isShowMiddle = true
                )
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    fabUp?.isShowing =
                        dy < 0 && (layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0
                }
            })
        }

        fabUp?.setOnClickListener {
            rvList?.layoutManager?.scrollToPosition(0)
            (it as FloatingActionButton).hide()
        }

        observeViewModel()
    }

    override fun onDestroyView() {
        rvList = null
        fabUp = null
        flError = null
        flProgress = null
        super.onDestroyView()
    }

    private fun observeViewModel() {
        vm.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is MainViewModel.ScreenState.Error -> {
                    flError?.visibility = View.VISIBLE
                    flProgress?.visibility = View.GONE
                }
                is MainViewModel.ScreenState.Progress -> {
                    flProgress?.visibility = View.VISIBLE
                    flError?.visibility = View.GONE
                }
                is MainViewModel.ScreenState.Content -> {
                    flProgress?.visibility = View.GONE
                    flError?.visibility = View.GONE

                    adapter.items = it.list
                }
            }
        })
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
