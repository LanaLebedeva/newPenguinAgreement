package com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel

import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract

const val TAG = "PENGUIN AGREEMENT"

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

    override fun updatePluralNumberViews(numberToString: String, @PluralsRes plurals: Int): String? {
        val parsInt: Int = try {
            Integer.parseInt(numberToString)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
            Log.e(TAG, "The number of penguins or days greater than Integer.MAX_INT")
        }
        return resources?.getQuantityString(
            plurals,
            parsInt
        )
    }

    override fun updateAgreement() {
        val title: String = binding?.tvTitle?.text.toString()
        val subject: String = binding?.tvSubject?.text.toString()
        val numberPenguins: String = binding?.tvPenguinsNumber?.text.toString()
        val numberDays: String = binding?.tvDaysNumber?.text.toString()

        val fieldsNotEmpty =
            title != "" && subject != "" && numberPenguins != "" && numberDays != ""
        binding?.tvAgreement?.text = if (fieldsNotEmpty) {
            resources?.getString(R.string.text_agreement, title, subject,numberPenguins, numberDays)
        } else {
            resources?.getString(R.string.text_there_will_be_an_agreement)
        }
        if (!fieldsNotEmpty) {
            binding?.linearLayout?.let {
                Snackbar.make(
                    it,
                    R.string.msg_fill_in_the_fields,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

//        binding?.tvAgreement?.text =
//            if (title != "" && subject != "" && numberPenguins != "" && numberDays != "") {
//                "${resources?.getString(R.string.text_begin_agreement)} $title, $subject, $numberPenguins, $numberDays. ${
//                    resources?.getString(R.string.text_end_agreement)
//                }"
//            } else {
//                binding?.linearLayout?.let {
//                    Snackbar.make(
//                        it,
//                        R.string.msg_fill_in_the_fields,
//                        Snackbar.LENGTH_SHORT
//                    )
//                        .show()
//                }
//                resources?.getString(R.string.text_there_will_be_an_agreement)
//            }
//    }
}
