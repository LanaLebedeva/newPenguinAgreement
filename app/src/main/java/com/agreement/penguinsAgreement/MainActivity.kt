package com.agreement.penguinsAgreement

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var settings: SharedPreferences
    private lateinit var agreement: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initValue()
        myGetSettingsPreferences()
        checkButton()
    }

    private fun initValue() {
        agreement = getString(R.string.main_there_will_be_an_agreement_here)
        with(binding) {
            tvAgreement.text = getString(R.string.main_there_will_be_an_agreement_here)
            tvPluralsPenguins.text = getString(R.string.main_pluralsPenguins)
            tvPluralsDays.text = getString(R.string.main_pluralsDays)
        }
    }

    private fun myGetSettingsPreferences() {
        settings = getSharedPreferences(FILE_PREFS, MODE_PRIVATE)
        // получаем настройки                                        
        with(binding) {
            tvText1.setText(settings.getString(KEY_TEXT1, ""))
            tvText2.setText(settings.getString(KEY_TEXT2, ""))
            tvNumberPenguins.setText(settings.getString(KEY_NUMBER_PENGUINS, ""))
            tvNumberDays.setText(settings.getString(KEY_NUMBER_DAYS, ""))
        }
    }

    private fun checkButton() {
        binding.tvNumberPenguins.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                makePlural(binding.tvNumberPenguins.text.toString(), R.plurals.pluralsPenguins)
            }
        }
        binding.tvNumberDays.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                makePlural(binding.tvNumberDays.text.toString(), R.plurals.pluralsDays)
            }
        }
        binding.btnConfirm.setOnClickListener {
            makePlural(binding.tvNumberDays.text.toString(), R.plurals.pluralsDays)
            makePlural(binding.tvNumberPenguins.text.toString(), R.plurals.pluralsPenguins)
            makeAgreement(
                binding.tvText1.text.toString(),
                binding.tvText2.text.toString(),
                binding.tvNumberPenguins.text.toString(),
                binding.tvNumberDays.text.toString()
            )
        }
    }

    fun updateAgreement() {
        binding.tvAgreement.text = agreement
    }

    fun updatePlPinguins(text: String) {
        binding.tvPluralsPenguins.text = text
    }

    fun updatePlDays(text: String) {
        binding.tvPluralsDays.text = text
    }

    // сохранение состояния при повороте экрана
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_AGREEMENT, binding.tvAgreement.text.toString())
        super.onSaveInstanceState(outState)
    }

    // получение ранее сохраненного состояния при повороте экрана
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.tvAgreement.text = savedInstanceState.getString(KEY_AGREEMENT)
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
            R.plurals.pluralsPenguins -> updatePlPinguins(plural)
            R.plurals.pluralsDays -> updatePlDays(plural)
        }
    }

    private fun makeAgreement(
        text1: String,
        text2: String,
        number1: String,
        number2: String,
    ) {
        agreement = if (text1 != "" && text2 != "" && number1 != "" && number2 != "") {
            "${resources.getString(R.string.model_begin_agreement)} $text1, $text2, $number1, $number2 ${
                resources.getString(R.string.model_end_agreement)
            }"
        } else {
            resources.getString(R.string.main_there_will_be_an_agreement_here)
        }
        binding.tvAgreement.text = agreement
    }

    override fun onPause() {
        super.onPause()
        val prefEditor: SharedPreferences.Editor = settings.edit()
        with(prefEditor) {
            putString(KEY_TEXT1, binding.tvText1.text.toString())
            putString(KEY_TEXT2, binding.tvText2.text.toString())
            putString(KEY_NUMBER_PENGUINS, binding.tvNumberPenguins.text.toString())
            putString(KEY_NUMBER_DAYS, binding.tvNumberDays.text.toString())
            apply()
        }
    }

    companion object {
        private const val FILE_PREFS = "TASK_PENGUIN"
        private const val KEY_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val KEY_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val KEY_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val KEY_TEXT1 = "TEXT1_KEY"
        private const val KEY_TEXT2 = "TEXT2_KEY"
    }
}