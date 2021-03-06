package com.example.wb_week_4_1_2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_4_1_2.R
import com.example.wb_week_4_1_2.databinding.FragmentDetailsBinding
import com.example.wb_week_4_1_2.view.details.DetailsFragmentAdapter
import com.example.wb_week_4_1_2.viewmodel.AppStateListChatTexts
import com.example.wb_week_4_1_2.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }
    private val adapter: DetailsFragmentAdapter by lazy {
        DetailsFragmentAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsFragmentRecyclerView.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getChatTexts()
        swipeToRefresh()
    }

    private fun swipeToRefresh(){
        binding.detailsSwipeToRefresh.setOnRefreshListener {
            viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
            viewModel.getChatTexts()
            binding.detailsSwipeToRefresh.isRefreshing = false
        }

    }


    private fun renderData(appState: AppStateListChatTexts) {
        with(binding) {
            with(detailsFragmentRootView) {
                with(detailsFragmentLoadingLayout) {
                    when (appState) {
                        is AppStateListChatTexts.Success -> {
                            visibility = View.GONE
                            adapter.setChatData(appState.dataChats)
                            showSnackBarNoAction(R.string.success)
                        }
                        is AppStateListChatTexts.Loading -> {
                            visibility = View.VISIBLE
                            showSnackBarNoAction(R.string.load)
                        }
                        is AppStateListChatTexts.Error -> {
                            visibility = View.GONE
                            showSnackBar(
                                getString(R.string.error),
                                getString(R.string.reload),
                                {
                                    Toast.makeText(context, "????????????, ???????????????????? ???????????????? ????????????", Toast.LENGTH_SHORT).show()
                                })
                        }
                    }
                }

            }
        }


    }

    private fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    private fun View.showSnackBarNoAction(
        resourceID: Int,
        length: Int = Snackbar.LENGTH_SHORT
    ) {
        Snackbar.make(this, requireActivity().resources.getString(resourceID), length).show()
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val BUNDLE_EXTRA = "weather"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}