package com.agreement.penguinsAgreement.penguinsModel

import android.content.res.Resources
import android.util.Log
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.utils.PreferenceUtil

object ModelPenguins {
    private lateinit var preferences: PreferenceUtil
    private lateinit var resources: Resources

    fun initPreferenceResources(_preferences: PreferenceUtil, _resources: Resources) {
        preferences = _preferences
        resources = _resources
    }

    fun getPreferences() = preferences

    fun getResources() = resources

    fun getPluralPenguins(): String {
        val parsInt: Int = try {
            Integer.parseInt(
                getPreferences().getPrefStrNumberPenguins()
                    ?: resources.getString(R.string.text_penguins)
            )
        } catch (e: NumberFormatException) {
            Log.e(
                "ModelPenguins",
                "The number of penguins greater than Integer.MAX_INT"
            )
            Integer.MAX_VALUE
        }
        return resources.getQuantityString(R.plurals.penguins, parsInt)
    }

    fun getPluralDays(): String {
        val parsInt: Int = try {
            Integer.parseInt(
                getPreferences().getPrefStrNumberDays()
                    ?: resources.getString(R.string.text_days)
            )
        } catch (e: NumberFormatException) {
            Log.e(
                "ModelPenguins",
                "The number of days greater than Integer.MAX_INT"
            )
            Integer.MAX_VALUE
        }
        return resources.getQuantityString(R.plurals.days, parsInt)
    }

    fun getAgreement(): String {
        val title: String? = preferences.getPrefStrTitle()
        val subject: String? = preferences.getPrefStrSubject()
        val numberPenguins: String? = preferences.getPrefStrNumberPenguins()
        val numberDays: String? = preferences.getPrefStrNumberDays()

        val fieldsNotEmpty =
            title != "" && subject != "" && numberPenguins != "" && numberDays != ""
        val agreementStr = if (fieldsNotEmpty) {
            resources.getString(
                R.string.text_agreement,
                title,
                subject,
                numberPenguins,
                numberDays
            )
        } else {
            resources.getString(R.string.text_there_will_be_an_agreement)
        }
        return agreementStr
    }

    fun updateAgreement(): Boolean {
        if (getAgreement() == resources.getString(R.string.text_there_will_be_an_agreement)) {
            return false
        }
        return true
    }

    fun updatePenguinsNumber(numberPenguins: String) {
        preferences.setPrefStrNumberPenguins(numberPenguins)
    }

    fun updateDaysNumber(numberDays: String) {
        preferences.setPrefStrNumberDays(numberDays)
    }
}
