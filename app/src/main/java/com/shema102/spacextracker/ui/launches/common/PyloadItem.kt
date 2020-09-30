package com.shema102.spacextracker.ui.launches.common


import androidx.core.text.HtmlCompat
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
            updatePayloadName()
            updatePayloadType()
            updatePayloadManufacturers()
            updatePayloadCustomers()
            updatePayloadMass()
        }
    }

    override fun getLayout(): Int = R.layout.payload_details_item

    private fun ViewHolder.updatePayloadName() {
        val name: String = payload.name ?: "No info"
        textView_payload_name.text =
            HtmlCompat.fromHtml("<b>Name:</b> $name", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun ViewHolder.updatePayloadType() {
        val type: String = payload.type ?: "No info"
        textView_payload_type.text =
            HtmlCompat.fromHtml("<b>Type:</b> $type", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun ViewHolder.updatePayloadManufacturers() {
        val manufacturers: String =
            payload.manufacturers?.joinToString(separator = ", ") ?: "No info"
        textView_payload_manufacturers.text = HtmlCompat.fromHtml(
            "<b>Manufacturers:</b> $manufacturers",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun ViewHolder.updatePayloadCustomers() {
        val customers: String = payload.customers?.joinToString(separator = ", ") ?: "No info"
        textView_payload_customers.text = HtmlCompat.fromHtml(
            "<b>Customers:</b> $customers",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun ViewHolder.updatePayloadMass() {
        val payloadMass: String =
            if (unitProvider.getUnitSystem() == UnitSystem.METRIC) {
                "${payload.mass_kg.toString()} kg"
            } else {
                "${payload.mass_lbs.toString()} lbs"
            }

        textView_payload_mass.text = HtmlCompat.fromHtml(
            "<b>Mass:</b> $payloadMass",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


}