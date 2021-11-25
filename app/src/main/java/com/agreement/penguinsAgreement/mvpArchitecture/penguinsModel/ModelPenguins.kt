package com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.R
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract

object ModelPenguins: PenguinsContract.PenguinModel {
    private var binding: ActivityMainBinding? = null
    private var preferences: SharedPreferences? = null
    private var resources: Resources? = null

    override fun initModelPinguins(_binding: ActivityMainBinding, _preferences: SharedPreferences, _resources: Resources) {
        binding = _binding
        preferences = _preferences
        resources = _resources
    }

    override fun getBinding() = binding

    override fun getPreferences() = preferences

    override fun getResources() = resources

    override fun makePlural(numberToString: String, plurals: Int) {
        val parsInt: Int = try {
            Integer.parseInt(numberToString)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
        }
        val plural = resources?.getQuantityString(
            plurals,
            parsInt
        )
        when (plurals) {
            R.plurals.penguins -> binding?.tvPenguins?.text = plural
            R.plurals.days -> binding?.tvDays?.text = plural
        }
    }

    override fun makeAgreement() {
        val title: String = binding?.tvTitle?.text.toString()
        val subject: String = binding?.tvSubject?.text.toString()
        val numberPenguins: String = binding?.tvPenguinsNumber?.text.toString()
        val numberDays: String = binding?.tvDaysNumber?.text.toString()

        binding?.tvAgreement?.text =
            if (title != "" && subject != "" && numberPenguins != "" && numberDays != "") {
                "${resources?.getString(R.string.text_begin_agreement)} $title, $subject, $numberPenguins, $numberDays. ${
                    resources?.getString(R.string.text_end_agreement)
                }"
            } else {
                binding?.linearLayout?.let {
                    Snackbar.make(
                        it,
                        R.string.msg_fill_in_the_fields,
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
                resources?.getString(R.string.text_there_will_be_an_agreement)
            }
    }
}
