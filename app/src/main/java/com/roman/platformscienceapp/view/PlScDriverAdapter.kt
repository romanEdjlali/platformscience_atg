package com.roman.platformscienceapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.roman.platformscienceapp.R
import com.roman.platformscienceapp.model.Driver
import com.roman.platformscienceapp.model.Shipment

/*
* Class: PlScDriverAdapter
* Owner: Roman Edjlali
* Date Created: 08/23/2023 6:43 PM
*/
class PlScDriverAdapter(
    private val drivers: List<Driver>,
    private val shipments: List<Shipment>
) : RecyclerView.Adapter<PlScDriverAdapter.DriverViewHolder>() {

    private val tag = javaClass.simpleName
    class DriverViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val driverNameTextView: TextView = itemView.findViewById(R.id.tvDriver)
        val shipmentAddressTextView: TextView = itemView.findViewById(R.id.tvShipment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)
        return DriverViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        //Setting the data in items
        val driver = drivers[position]
        val shipment = shipments.maxByOrNull{ calSuitabilityScore(it.address, driver.name)}
        val destination = shipment?.address ?: ""

        holder.driverNameTextView.text = driver.name
        holder.shipmentAddressTextView.text = shipment?.address
        //holder.shipmentAddressTextView.text = destination
        holder.itemView.setOnClickListener {
            // Display shipment destination for the selected driver
            Toast.makeText(
                holder.itemView.context,
                "Shipment destination for ${driver.name}: $destination",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    private fun calSuitabilityScore(shipmentAddress: String, driverName: String): Double {
        val shipmentLength = shipmentAddress.replace("\\s".toRegex(), "").length
        val driverLength = driverName.replace("\\s".toRegex(), "").length

        //I need to review this carefully! the third condition cause  am not sure about it.
        //Conditions:
        val baseSS: Double = if (shipmentLength % 2 == 0) { //Condition I
            val numVowels = driverName.count { it.lowercase() in "aeiou" }
            numVowels * 1.5
        } else { //Condition II
            val numConsonants = driverName.length - driverName.count { it.lowercase() in "aeiou" }
            numConsonants * 1.0
        }
        //Condition III
        val hasCommonFactors = hasCommonFactors(shipmentLength, driverLength)
        return if (hasCommonFactors) baseSS * 1.5 else baseSS
    }
    //Logic for Condition III
    private fun hasCommonFactors(a: Int, b: Int): Boolean {
        if (a <= 1 || b <= 1)
            return false

        return greatCommonDivisor(a, b) > 1
    }
    private fun greatCommonDivisor(a: Int, b: Int): Int {
        if (b == 0)
            return a

        return greatCommonDivisor(b, a % b)
    }
}
