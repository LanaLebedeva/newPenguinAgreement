package com.agreement.penguinsAgreement

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.contract.Contract
import com.agreement.penguinsAgreement.presenter.PresenterMainActivity
import leakcanary.AppWatcher

class MainActivity : AppCompatActivity(), Contract.ViewMain {

    private lateinit var binding: ActivityMainBinding
    private lateinit var settings: SharedPreferences
    private lateinit var presenter: PresenterMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = PresenterMainActivity(this)
        initValue()
        myGetSettingsPreferences()
        checkButton()
        AppWatcher.objectWatcher.watch(MainActivity, "View was detached")
    }

    override fun initValue() {
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

    override fun checkButton() {
        binding.tvNumberPenguins.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                presenter.makePlural(
                    binding.tvNumberPenguins.text.toString(), R.plurals.pluralsPenguins
                )
            }
        }
        binding.tvNumberDays.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                presenter.makePlural(binding.tvNumberDays.text.toString(), R.plurals.pluralsDays)
            }
        }
        binding.btnConfirm.setOnClickListener {
            presenter.makeAgreement(
                binding.tvText1.text.toString(),
                binding.tvText2.text.toString(),
                binding.tvNumberPenguins.text.toString(),
                binding.tvNumberDays.text.toString()
            )
        }
    }

    override fun updateAgreement() {
        binding.tvAgreement.text = presenter.getAgreement()
    }

    override fun updatePlPinguins(text: String) {
        binding.tvPluralsPenguins.text = text
    }

    override fun updatePlDays(text: String) {
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