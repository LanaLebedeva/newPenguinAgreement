package com.agreement.penguinsAgreement.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class PreferenceUtil(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences(FILE_STR_SHARED_PREFERENCES, MODE_PRIVATE)

    var numberPenguins: String?
        get() = preferences.getString(PREF_STR_NUMBER_PENGUINS, null)
        set(new) =
            preferences.edit().putString(PREF_STR_NUMBER_PENGUINS, new).apply()

    var numberDays: String?
        get() = preferences.getString(PREF_STR_NUMBER_DAYS, null)
        set(new) = preferences.edit().putString(PREF_STR_NUMBER_DAYS, new).apply()

    var title: String?
        get() = preferences.getString(PREF_STR_TITLE, null)
        set(new) = preferences.edit().putString(PREF_STR_TITLE, new).apply()

    var subject: String?
        get() = preferences.getString(PREF_STR_SUBJECT, null)
        set(new) = preferences.edit().putString(PREF_STR_SUBJECT, new).apply()

    var onAgreement: Boolean
        get() = preferences.getBoolean(PREF_BOOL_ON_AGREEMENT, false)
        set(new) = preferences.edit().putBoolean(PREF_BOOL_ON_AGREEMENT, new).apply()

    companion object {
        private const val FILE_STR_SHARED_PREFERENCES = "TASK_PENGUIN"
        private const val PREF_STR_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val PREF_STR_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val PREF_STR_TITLE = "TITLE_KEY"
        private const val PREF_STR_SUBJECT = "SUBJECT_KEY"
        private const val PREF_BOOL_ON_AGREEMENT = "ON_AGREEMENT"
    }
}