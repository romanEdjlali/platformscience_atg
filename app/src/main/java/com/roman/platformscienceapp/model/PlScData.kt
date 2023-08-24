package com.roman.platformscienceapp.model

/*
* Class: PlScData
* Owner: Roman Edjlali
* Date Created: 08/23/2023 5:44 PM
*/
data class Driver(val name: String)
data class Shipment(val address: String)
data class ShipmentAssociateDriver(val driverNames: List<Driver>, val shipmentAddresses: List<Shipment>)

