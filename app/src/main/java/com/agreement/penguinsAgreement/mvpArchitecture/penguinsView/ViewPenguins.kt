package com.agreement.penguinsAgreement.mvpArchitecture.penguinsView

import android.content.SharedPreferences
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.google.android.material.snackbar.Snackbar


class ViewPenguins(private val binding: ActivityMainBinding) : PenguinsContract.PenguinView {
    private val presenter =
        com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter.PresenterPenguins(this)
    private val model: ModelPenguins = ModelPenguins

    override fun initView() {
        with(binding) {
            this.tvTitle.setText(model.getPreferences().getString(PREF_STR_TITLE, "test"))
            this.tvSubject.setText(model.getPreferences().getString(PREF_STR_SUBJECT, "test"))
            this.tvPenguinsNumber.setText(model.getPreferences()
                .getString(PREF_STR_NUMBER_PENGUINS, "0"))
            this.tvDaysNumber.setText(model.getPreferences().getString(PREF_STR_NUMBER_DAYS, "0"))
            this.tvPenguins.text =
                model.getPreferences().getString(PREF_STR_PENGUINS,
                    model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_penguins))
            this.tvDays.text = model.getPreferences()
                .getString(PREF_STR_DAYS, model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_days))
            this.tvAgreement.text = model.getPreferences().getString(
                PREF_STR_AGREEMENT,
                model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_there_will_be_an_agreement)
            )
        }
    }

    override fun initListeners() {

        binding.tvDaysNumber.doAfterTextChanged {
            presenter.updatePluralNumberTextViews(binding.tvDaysNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.days)
            presenter.updateAgreementTextView()
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            presenter.updatePluralNumberTextViews(binding.tvPenguinsNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.penguins)
        }
        binding.btnFormAgreement.setOnClickListener {
            presenter.updateAgreementTextView()
        }
        binding.tvDaysNumber.setOnKeyListener { _, keyCode, _ ->
           if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
               presenter.updateAgreementTextView()
           }
            true
        }
    }

    override fun onSaveViewPenguins() {
        val prefEditor: SharedPreferences.Editor? = model.getPreferences().edit()
        with(prefEditor) {
            this?.putString(PREF_STR_TITLE, binding.tvTitle.text.toString())
            this?.putString(PREF_STR_SUBJECT, binding.tvSubject.text.toString())
            this?.putString(PREF_STR_NUMBER_PENGUINS,
                binding.tvPenguinsNumber.text.toString())
            this?.putString(PREF_STR_NUMBER_DAYS, binding.tvDaysNumber.text.toString())
            this?.putString(PREF_STR_PENGUINS, binding.tvPenguins.text.toString())
            this?.putString(PREF_STR_DAYS, binding.tvDays.text.toString())
            this?.putString(PREF_STR_AGREEMENT, binding.tvAgreement.text.toString())
            this?.apply()
        }
    }

    override fun updatePenguins(pluralsNumber: String?) {
        binding.tvPenguins.text = pluralsNumber
    }

    override fun updateDays(pluralsNumber: String?) {
        binding.tvDays.text = pluralsNumber
    }

    override fun setAgreement(agreementReturn: String) {
        binding.tvAgreement.text = agreementReturn
    }

    fun setSnackbarAgreement() {
        binding.linearLayout.let {
            Snackbar.make(
                it,
                com.agreement.penguinsAgreement.R.string.msg_fill_in_the_fields,
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    fun updateAgreement() {
        binding.tvAgreement.text = model.getPreferences().getString(
            PREF_STR_AGREEMENT,
            model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_there_will_be_an_agreement)
        )
    }

    companion object {
        private const val PREF_STR_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val PREF_STR_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val PREF_STR_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val PREF_STR_TITLE = "TITLE_KEY"
        private const val PREF_STR_SUBJECT = "SUBJECT_KEY"
        private const val PREF_STR_PENGUINS = "PENGUINS"
        private const val PREF_STR_DAYS = "DAYS"
    }
}