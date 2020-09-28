package com.shema102.spacextracker.ui.launches.next


import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.Payload
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.internal.UnitSystem
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.payload_details_item.*


class PayloadItem(
    val payload: Payload,
    private val unitProvider: UnitProvider
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_payload_name.text = payload.name
            textView_payload_type.text = payload.type
            updatePayloadManufacturers()
            updatePayloadCustomers()
            updatePayloadMass()
        }
    }

    override fun getLayout(): Int = R.layout.payload_details_item

    private fun ViewHolder.updatePayloadManufacturers() {
        val manufacturers: String = payload.manufacturers?.joinToString(separator = ", ") ?: ""
        textView_payload_manufacturers.text = manufacturers
    }

    private fun ViewHolder.updatePayloadCustomers() {
        val launchDate: String = payload.customers?.joinToString(separator = ", ") ?: ""
        textView_payload_customers.text = launchDate
    }

    private fun ViewHolder.updatePayloadMass() {
        val payloadMass: String =
            if (unitProvider.getUnitSystem() == UnitSystem.METRIC) {
                "${payload.mass_kg.toString()} kg"
            } else {
                "${payload.mass_lbs.toString()} lbs"
            }

        textView_payload_mass.text = payloadMass
    }


}