package com.roman.platformscienceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.roman.platformscienceapp.model.Driver
import com.roman.platformscienceapp.model.Shipment

/*
* Class: PlScViewModel
* Owner: Roman Edjlali
* Date Created: 08/23/2023 5:54 PM
*/
class PlScViewModel: ViewModel() {

    private val drivers = mutableListOf<Driver>()
    private val shipments = mutableListOf<Shipment>()

}