package com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel

import android.content.SharedPreferences
import android.content.res.Resources
import com.agreement.penguinsAgreement.R
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding

object ModelPenguins {
    internal var binding: ActivityMainBinding? = null
    internal var preferences: SharedPreferences? = null
    internal var resources: Resources? = null

    fun setBinding(_binding: ActivityMainBinding) {
        binding = _binding
    }

    fun setPreference(_preferences: SharedPreferences) {
        preferences = _preferences
    }

    fun setResources(_resources: Resources) {
        resources = _resources
    }

    fun makePlural(numberToString: String, plurals: Int) {
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

    fun makeAgreement() {
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
