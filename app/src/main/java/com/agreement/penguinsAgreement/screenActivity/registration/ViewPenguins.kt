package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.SharedPreferences
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.google.android.material.snackbar.Snackbar


class ViewPenguins(private val binding: ActivityMainBinding) : PenguinsContract.PenguinView {
    private val presenter =
        PresenterPenguins(this)
    private val model: ModelPenguins = ModelPenguins

    override fun initView() {
        with(binding) {
            this.tvTitle.setText(model.getPreferences().getPrefStrTitle())
            this.tvSubject.setText(model.getPreferences().getPrefStrSubject())
            this.tvPenguinsNumber.setText(model.getPreferences().getPrefStrNumberPenguins())
            this.tvDaysNumber.setText(model.getPreferences().getPrefStrNumberDays())
            this.tvPenguins.text = model.getPreferences().getPrefStrPenguins()
            this.tvDays.text = model.getPreferences().getPrefStrDays()
            this.tvAgreement.text = model.getPreferences().getPrefStrAgreement()
            // todo если null  то значение из ресурсов
//            this.tvAgreement.text = model.getPreferences().getString(
//                PREF_STR_AGREEMENT,
//                model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_there_will_be_an_agreement)
//            )
        }
    }

    override fun initListeners() {

        binding.tvDaysNumber.doAfterTextChanged {
            presenter.changedPluralNumberTextViews(binding.tvDaysNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.days)
            presenter.onFormAgreementClick()
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            presenter.changedPluralNumberTextViews(binding.tvPenguinsNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.penguins)
        }
        binding.btnFormAgreement.setOnClickListener {
            presenter.onFormAgreementClick()
        }
        binding.tvDaysNumber.setOnKeyListener { _, keyCode, _ ->
           if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
               presenter.onFormAgreementClick()
           }
            false
        }
        binding.btmConfirmIt.setOnClickListener{
            presenter.onConfirmClick()
        }
    }

    override fun onSaveViewPenguins() {
//        val prefEditor: SharedPreferences.Editor? = model.getPreferences().edit()
//        with(prefEditor) {
//            this?.putString(PREF_STR_TITLE, binding.tvTitle.text.toString())
//            this?.putString(PREF_STR_SUBJECT, binding.tvSubject.text.toString())
//            this?.putString(
//                PREF_STR_NUMBER_PENGUINS,
//                binding.tvPenguinsNumber.text.toString())
//            this?.putString(PREF_STR_NUMBER_DAYS, binding.tvDaysNumber.text.toString())
//            this?.putString(PREF_STR_PENGUINS, binding.tvPenguins.text.toString())
//            this?.putString(PREF_STR_DAYS, binding.tvDays.text.toString())
//            this?.putString(PREF_STR_AGREEMENT, binding.tvAgreement.text.toString())
//            this?.apply()
//        }
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
        binding.constraintLayout.let {
            Snackbar.make(
                it,
                com.agreement.penguinsAgreement.R.string.msg_fill_in_the_fields,
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    fun updateAgreement() {
        binding.tvAgreement.text = model.getPreferences().getPrefStrAgreement()
//        binding.tvAgreement.text = model.getPreferences().getString(
//            PREF_STR_AGREEMENT,
//            model.getResources().getString(com.agreement.penguinsAgreement.R.string.text_there_will_be_an_agreement)
//        )
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