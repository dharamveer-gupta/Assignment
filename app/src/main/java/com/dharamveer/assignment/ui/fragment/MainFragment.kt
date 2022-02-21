package com.dharamveer.assignment.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dharamveer.assignment.databinding.MainFragmentBinding
import com.dharamveer.assignment.ui.adapter.CityAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    private lateinit var cityAdapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getData()
        initRecycler()

        /*viewModel.cities.observe(viewLifecycleOwner) {
            cityAdapter.differ.submitList(it ?: mutableListOf())
        }*/

        viewModel.selectedCity.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.buttonConfirm.isEnabled = true
            }
        }

        binding.searchViewCity.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cityAdapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun initRecycler() {
        cityAdapter = CityAdapter(
            viewModel.cities.value ?: mutableListOf(),
            viewModel.selectedCity.value ?: ""
        ) { selected, cityName ->

            when(selected) {
                true -> {
                    viewModel.selectedCity.value = cityName
                }
                else -> {
                    return@CityAdapter
                }
            }
        }
        binding.recyclerViewCity.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCity.adapter = cityAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}