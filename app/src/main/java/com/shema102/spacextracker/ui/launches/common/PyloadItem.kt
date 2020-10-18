package com.shema102.spacextracker.ui.launches.common


import android.content.Context
import androidx.core.text.HtmlCompat
import com.shema102.spacextracker.R
import com.shema102.spacextracker.data.db.entity.Payload
import com.shema102.spacextracker.data.provider.UnitProvider
import com.shema102.spacextracker.internal.NoConnectivityException
import com.shema102.spacextracker.internal.UnitSystem
import com.shema102.spacextracker.internal.toBoldHtml
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.payload_details_item.*


class PayloadItem(
    val payload: Payload,
    private val unitProvider: UnitProvider,
    val context: Context?
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
    private val noInfo = context?.getString(R.string.no_info)

    override fun getLayout(): Int = R.layout.payload_details_item

    private fun ViewHolder.updatePayloadName() {
        val name = payload.name ?: noInfo
        textView_payload_name.text =
            HtmlCompat.fromHtml("${context?.getString(R.string.name)?.toBoldHtml()}: $name", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun ViewHolder.updatePayloadType() {
        val type = payload.type ?: noInfo
        textView_payload_type.text =
            HtmlCompat.fromHtml("${context?.getString(R.string.type)?.toBoldHtml()}: $type", HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    private fun ViewHolder.updatePayloadManufacturers() {
        val manufacturers =
            payload.manufacturers?.joinToString(separator = ", ") ?: noInfo
        textView_payload_manufacturers.text = HtmlCompat.fromHtml(
            "${context?.getString(R.string.manufacturers)?.toBoldHtml()}: $manufacturers",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun ViewHolder.updatePayloadCustomers() {
        val customers = payload.customers?.joinToString(separator = ", ") ?: noInfo
        textView_payload_customers.text = HtmlCompat.fromHtml(
            "${context?.getString(R.string.customers)?.toBoldHtml()}: $customers",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }

    private fun ViewHolder.updatePayloadMass() {
        val payloadMass: String =
            if (unitProvider.getUnitSystem() == UnitSystem.METRIC) {
                "${payload.mass_kg.toString()} ${context?.getString(R.string.kg)}"
            } else {
                "${payload.mass_lbs.toString()} ${context?.getString(R.string.lbs)}"
            }

        textView_payload_mass.text = HtmlCompat.fromHtml(
            "${context?.getString(R.string.mass)?.toBoldHtml()}: $payloadMass",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }


}