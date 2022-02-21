package com.dharamveer.assignment.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dharamveer.assignment.model.StateWithCity

class MainViewModel : ViewModel() {
    private val _cities = MutableLiveData(mutableListOf<StateWithCity>())
    val cities : LiveData<MutableList<StateWithCity>>
    get() = _cities

    val selectedCity = MutableLiveData<String>()

    fun getData() {
        _cities.value?.addAll(
            listOf(
                /*City from A-L*/
                StateWithCity("Andhra Pradesh", "Hyderabad"),
                StateWithCity("Arunachal Pradesh", "Itanagar"),
                StateWithCity("Assam", "Dispur"),
                StateWithCity("Bihar", "Patna"),
                StateWithCity("Chhattisgarh", "Raipur"),
                StateWithCity("Goa", "Panaji"),
                StateWithCity("Gujarat", "Gandhinagar"),
                StateWithCity("Haryana", "Chandigarh"),
                StateWithCity("Himachal Pradesh", "Shimla"),
                StateWithCity("Jammu & Kashmir", "Srinagar(Summer)/Jammu(Winter)"),
                StateWithCity("Jharkhand", "Ranchi"),
                StateWithCity("Karnataka", "Bengaluru"),
                StateWithCity("Kerala", "Thiruvananthapuram"),
                /*City from M-Z*/
                StateWithCity("Madhya Pradesh", "Bhopal"),
                StateWithCity("Maharashtra", "Mumbai"),
                StateWithCity("Manipur", "Imphal"),
                StateWithCity("Meghalaya", "Shillong"),
                StateWithCity("Mizoram", "Aizawl"),
                StateWithCity("Nagaland", "Kohima"),
                StateWithCity("Odisha", "Bhubaneswar"),
                StateWithCity("Punjab", "Chandigarh"),
                StateWithCity("Rajasthan", "Jaipur"),
                StateWithCity("Sikkim", "Gangtok"),
                StateWithCity("Tamil Nadu", "Chennai"),
                StateWithCity("Telangana", "Hyderabad"),
                StateWithCity("Tripura", "Agartala"),
                StateWithCity("Uttar Pradesh", "Lucknow"),
                StateWithCity("Uttarakhand", "Dehradun"),
                StateWithCity("West Bengal", "Kolkata"),
                /*Union Territories*/
                StateWithCity("Andaman & Nicobar Islands", "Port Blair"),
                StateWithCity("Chandigarh", "Chandigarh"),
                StateWithCity("Dadra & Nagar Haveli", "Silvassa"),
                StateWithCity("Daman & Diu", "Daman"),
                StateWithCity("Delhi", "New Delhi"),
                StateWithCity("Ladakh", "Leh"),
                StateWithCity("Lakshadweep", "Kavaratti"),
                StateWithCity("Puducherry", "Puducherry"),
            )
        )
    }

}