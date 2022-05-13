package com.example.subscriberlisteasy.ui.subscriber

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.subscriberlisteasy.R
import com.example.subscriberlisteasy.data.db.dao.SubscriberDAO
import com.example.subscriberlisteasy.data.db.database.DataBaseApp
import com.example.subscriberlisteasy.extension.hideKeyboard
import com.example.subscriberlisteasy.repository.DataBaseDataSource
import com.example.subscriberlisteasy.repository.ISubscriberRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {

    lateinit var input_name: TextInputEditText
    lateinit var input_email: TextInputEditText
    lateinit var button_subscriber: Button

    private val viewModel: SubscriberViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val subscriberDAO: SubscriberDAO =
                    DataBaseApp.getInstance(requireContext()).subscriberDAO
                val repository: ISubscriberRepository = DataBaseDataSource(subscriberDAO)
                return SubscriberViewModel(repository = repository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        input_name = view.findViewById(R.id.input_name)
        input_email = view.findViewById(R.id.input_email)
        button_subscriber = view.findViewById(R.id.button_subscriber)

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner) {
            when (it) {
                is SubscriberViewModel.SubscriberState.Inserted -> {
                    clearFields()
                    hideKeyBoard()
                }
            }
        }
        viewModel.messageEventData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), R.string.subscriber_error_to_insert, Snackbar.LENGTH_LONG)
                .show()
        }
    }

    private fun clearFields() {
        input_name.text?.clear()
        input_email.text?.clear()
    }

    private fun hideKeyBoard() {
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity) {
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        button_subscriber.setOnClickListener {
            val name = input_name.text.toString()
            val email = input_email.text.toString()

            viewModel.addSubscriber(name, email)
        }
    }
}
