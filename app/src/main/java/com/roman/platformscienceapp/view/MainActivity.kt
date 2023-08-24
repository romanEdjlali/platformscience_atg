package com.roman.platformscienceapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.platformscienceapp.R
import com.roman.platformscienceapp.model.Driver
import com.roman.platformscienceapp.model.Shipment
import kotlinx.android.synthetic.main.activity_main.recyclerView
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

/*
* Class: MainActivity
* Owner: Roman Edjlali
* Date Created: 08/23/2023 5:17 PM
*/
class MainActivity : AppCompatActivity() {
    private val tag = javaClass.simpleName
    private val driverList = mutableListOf<Driver>()
    private val shipmentList = mutableListOf<Shipment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Parse JSON data and populate drivers and shipments lists
        loadJsonFile()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PlScDriverAdapter(driverList, shipmentList)
        recyclerView.adapter = adapter

    }

    // Read JSON data from the assets folder
    private fun loadJsonFile() {

        val jsonFileName = "data.json"
        val jsonFileContent = getJsonDataFromAsset(jsonFileName)

        // Parse JSON string to JSON object
        val jsonObject = JSONObject(jsonFileContent)

        // Get JSON arrays
        val shipmentsArray = jsonObject.getJSONArray("shipments")
        val driversArray = jsonObject.getJSONArray("drivers")

        Log.d(tag,"\nDrivers:")

        for (i in 0 until driversArray.length()) {
            Log.d(tag,"DriverArray -> ${driversArray.getString(i)}")
            driverList.add(Driver(driversArray.getString(i)))

        }

        Log.d(tag,"\nShipments:")
        //val shipmentList = mutableListOf<String>()
        for (i in 0 until shipmentsArray.length()) {
            Log.d(tag,"ShipmentArray -> ${shipmentsArray.getString(i)}")
            shipmentList.add(Shipment(shipmentsArray.getString(i)))

        }
        Log.d(tag,"####### DriveList Size is: -> ${driverList.size} #######")
        Log.d(tag,"-> Driver List -> $driverList")
        Log.d(tag,"####### Shipment Size is: -> ${shipmentList.size} #######")
        Log.d(tag,"-> Shipment List -> $shipmentList")
    }

    private fun getJsonDataFromAsset(fileName: String): String {
        val json: String
        try {
            val inputStream: InputStream = assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}