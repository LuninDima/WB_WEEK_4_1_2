package com.example.wb_week_4_1_2.view.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.example.wb_week_4_1_2.R
import com.example.wb_week_4_1_2.databinding.FragmentMainBinding
import com.example.wb_week_4_1_2.model.Chat
import com.example.wb_week_4_1_2.model.getListChatTexts
import com.example.wb_week_4_1_2.model.listChat
import com.example.wb_week_4_1_2.view.DetailsFragment
import com.example.wb_week_4_1_2.viewmodel.AppStateListChats
import com.example.wb_week_4_1_2.viewmodel.MainViewModel




class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val adapter = MainFragmentAdapter(object : OnItemViewClickListener {
        override fun onItemViewClick(chat: Chat) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(R.id.fragment_conteiner, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, chat)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentRecyclerView.adapter = adapter
     viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getChats()
        swipeToRefresh()


    }



    private fun swipeToRefresh(){
        binding.mainSwipeToRefresh.setOnRefreshListener {
            var chat = Chat(100, R.drawable.image, "Заголовок нового чата", getListChatTexts(), "12:06", 10)
            viewModel.updateChat(chat)
            viewModel.addChat(chat)
            viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
            viewModel.getListChatsUpdate()
            binding.mainSwipeToRefresh.isRefreshing = false
        }

    }


    private fun renderData(appState: AppStateListChats) {
        with(binding) {
            with(mainFragmentRootView) {
                with(mainFragmentLoadingLayout) {
                    when (appState) {
                        is AppStateListChats.Success -> {
                            visibility = View.GONE
                         adapter.setChatData(appState.dataChats)
                            showSnackBarNoAction(R.string.success)
                        }
                        is AppStateListChats.Loading -> {
                            visibility = View.VISIBLE
                            showSnackBarNoAction(R.string.load)
                        }
                        is AppStateListChats.Error -> {
                            visibility = View.GONE
                            showSnackBar(
                                getString(R.string.error),
                                getString(R.string.reload),
                                {
                                  Toast.makeText(context, "Ошибка, невозможно получить данные", Toast.LENGTH_SHORT).show()
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
        adapter.removeListener()
        super.onDestroy()
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(chat: Chat)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}

