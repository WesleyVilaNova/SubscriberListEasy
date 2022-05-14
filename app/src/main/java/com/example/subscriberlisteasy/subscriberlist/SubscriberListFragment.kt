package com.example.subscriberlisteasy.subscriberlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.subscriberlisteasy.R
import com.example.subscriberlisteasy.data.db.dao.SubscriberDAO
import com.example.subscriberlisteasy.data.db.database.DataBaseApp
import com.example.subscriberlisteasy.extension.navigateWithAnimations
import com.example.subscriberlisteasy.repository.DataBaseDataSource
import com.example.subscriberlisteasy.repository.ISubscriberRepository

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {

    lateinit var recyclerView: RecyclerView
    lateinit var fabAddSubscriber: View

    private val viewModel: SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    DataBaseApp.getInstance(requireContext()).subscriberDAO
                val repository: ISubscriberRepository = DataBaseDataSource(subscriberDAO)
                return SubscriberListViewModel(repository = repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_subscriber)
        fabAddSubscriber = view.findViewById(R.id.fabAddSubscriber)
        observerViewModelEvets()
        configureViewListeners()
    }

    private fun observerViewModelEvets() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner) {
            val subscriberListAdapter = SubscriberListAdapter().apply {
                onItemClick = { subscriber ->
                    val directions = SubscriberListFragmentDirections.actionSubscriberListFragmentToSubscriberFragment2(subscriber)
                    findNavController().navigateWithAnimations(directions)
                }
            }
            subscriberListAdapter.submitList(it)
            recyclerView.adapter = subscriberListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getSubscriber()
    }

    private fun configureViewListeners() {
        fabAddSubscriber.setOnClickListener {
            findNavController().navigateWithAnimations(R.id.action_subscriberListFragment_to_subscriberFragment2)
        }
    }
}
