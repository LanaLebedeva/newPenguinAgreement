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

    private fun getPreferences() = preferences

    fun getResources() = resources

    fun getPluralPenguins(): String {
        val parsInt: Int = try {
            Integer.parseInt(
                getNumberPenguinsOrNull()
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
                getNumberDaysOrNull()
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
        val title: String = getTitle()
        val subject: String = getSubject()
        val numberPenguins: String = getNumberPenguins()
        val numberDays: String = getNumberDays()

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

    fun checkUpdateAgreement(): Boolean {
        if (getAgreement() == resources.getString(R.string.text_there_will_be_an_agreement)) {
            return false
        }
        return true
    }

    fun getTitle(): String =
        preferences.title ?: ""

    fun getSubject(): String =
        preferences.subject ?: ""

    fun getNumberPenguins(): String =
        preferences.numberPenguins ?: ""

    fun getNumberPenguinsOrNull(): String? =
        preferences.numberPenguins

    fun getNumberDays(): String =
        preferences.numberDays ?: ""

    fun getNumberDaysOrNull(): String? =
        preferences.numberDays

    fun setTitle(title: String) =
        title.also { preferences.title = it }

    fun setSubject(subject: String) =
        subject.also { preferences.subject = it }


    fun setPenguinsNumber(numberPenguins: String) =
        numberPenguins.also { preferences.numberPenguins = it }

    fun setDaysNumber(numberDays: String) =
        numberDays.also { preferences.numberDays = it }

    fun setBoolOnAgreement(onAgreement: Boolean) =
        onAgreement.also { getPreferences().onAgreement = it }

    fun getBoolOnAgreement(): Boolean =
        getPreferences().onAgreement
}
