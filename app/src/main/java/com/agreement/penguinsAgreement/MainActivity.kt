package com.agreement.penguinsAgreement

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import leakcanary.AppWatcher
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences
    private lateinit var agreement: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initArgument()
        initPreferences()
        updateFields()
    }

    private fun initArgument() {
        agreement = getString(R.string.main_there_will_be_an_agreement)
        binding.tvAgreement.text = getString(R.string.main_there_will_be_an_agreement)
    }

    private fun initPreferences() {
        preferences = getSharedPreferences(STR_FILE_SHARED_PREFERENCES, MODE_PRIVATE)
        // получаем настройки                                        
        with(binding) {
            tvTitle.setText(preferences.getString(STR_KEY_TITLE, ""))
            tvSubject.setText(preferences.getString(STR_KEY_SUBJECT, ""))
            tvPenguinsNumber.setText(preferences.getString(STR_KEY_NUMBER_PENGUINS, ""))
            tvDaysNumber.setText(preferences.getString(STR_KEY_NUMBER_DAYS, ""))
            tvPenguins.text =
                preferences.getString(STR_PENGUINS, resources.getString(R.string.main_penguins))
            tvDays.text = preferences.getString(STR_DAYS, resources.getString(R.string.main_days))
        }
    }

    private fun updateFields() {
        checkFocusChangeListener()
        checkButtonClickListener()
    }

    private fun checkFocusChangeListener() {
        binding.tvPenguinsNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                makePlural(binding.tvPenguinsNumber.text.toString(), R.plurals.pluralsPenguins)
            }
        }
        binding.tvDaysNumber.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                makePlural(binding.tvDaysNumber.text.toString(), R.plurals.pluralsDays)
            }
        }
    }

    private fun checkButtonClickListener() {
        binding.btnConfirm.setOnClickListener {
            makePlural(binding.tvDaysNumber.text.toString(), R.plurals.pluralsDays)
            makePlural(binding.tvPenguinsNumber.text.toString(), R.plurals.pluralsPenguins)
            makeAgreement()
        }
    }

    private fun updatePinguins(text: String) {
        binding.tvPenguins.text = text
    }

    private fun updateDays(text: String) {
        binding.tvDays.text = text
    }

    // сохранение состояния при повороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STR_KEY_AGREEMENT, binding.tvAgreement.text.toString())
    }

    // получение ранее сохраненного состояния при повороте экрана
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvAgreement.text = savedInstanceState.getString(STR_KEY_AGREEMENT)
    }

    private fun makePlural(number: String, plurals: Int) {
        val parsInt: Int = try {
            Integer.parseInt(number)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
        }
        val plural = resources.getQuantityString(
            plurals,
            parsInt
        )
        when (plurals) {
            R.plurals.pluralsPenguins -> updatePinguins(plural)
            R.plurals.pluralsDays -> updateDays(plural)
        }
    }

    private fun makeAgreement() {
        val title: String = binding.tvTitle.text.toString()
        val subject: String = binding.tvSubject.text.toString()
        val numberPenguins: String = binding.tvPenguinsNumber.text.toString()
        val numberDays: String = binding.tvDaysNumber.text.toString()

        agreement = if (title != "" && subject != "" && numberPenguins != "" && numberDays != "") {
            "${resources.getString(R.string.model_begin_agreement)} $title, $subject, $numberPenguins, $numberDays. ${
                resources.getString(R.string.model_end_agreement)
            }"
        } else {
            val contextView = findViewById<View>(R.id.linearLayout)
            Snackbar.make(contextView, R.string.main_fill_in_the_fields, Snackbar.LENGTH_SHORT)
                .show()
            resources.getString(R.string.main_there_will_be_an_agreement)
        }
        binding.tvAgreement.text = agreement
    }

    override fun onPause() {
        super.onPause()
        val prefEditor: SharedPreferences.Editor = preferences.edit()
        with(prefEditor) {
            putString(STR_KEY_TITLE, binding.tvTitle.text.toString())
            putString(STR_KEY_SUBJECT, binding.tvSubject.text.toString())
            putString(STR_KEY_NUMBER_PENGUINS, binding.tvPenguinsNumber.text.toString())
            putString(STR_KEY_NUMBER_DAYS, binding.tvDaysNumber.text.toString())
            putString(STR_PENGUINS, binding.tvPenguins.text.toString())
            putString(STR_DAYS, binding.tvDays.text.toString())
            apply()
        }
    }

    companion object {
        private const val STR_FILE_SHARED_PREFERENCES = "TASK_PENGUIN"
        private const val STR_KEY_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val STR_KEY_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val STR_KEY_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val STR_KEY_TITLE = "TITLE_KEY"
        private const val STR_KEY_SUBJECT = "SUBJECT_KEY"
        private const val STR_PENGUINS = "PENGUINS"
        private const val STR_DAYS = "DAYS"
    }
}