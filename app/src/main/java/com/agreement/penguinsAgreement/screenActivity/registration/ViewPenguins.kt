package com.agreement.penguinsAgreement.screenActivity.registration

import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.R
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
            this.tvPenguins.text =
                model.getPreferences().getPrefStrPenguins() ?: model.getResources().getString(
                    R.string.text_penguins
                )
            this.tvDays.text = model.getPreferences().getPrefStrDays() ?: model.getResources()
                .getString(R.string.text_days)
            this.tvAgreement.text =
                model.getPreferences().getPrefStrAgreement() ?: model.getResources()
                    .getString(R.string.text_there_will_be_an_agreement)
        }
    }

    override fun initListeners() {

        binding.tvDaysNumber.doAfterTextChanged {
            presenter.onPluralNumberTextViewsChange(
                binding.tvDaysNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.days
            )
            presenter.onFormAgreementClick()
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            presenter.onPluralNumberTextViewsChange(
                binding.tvPenguinsNumber.text.toString(),
                com.agreement.penguinsAgreement.R.plurals.penguins
            )
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
        binding.btmConfirmIt.setOnClickListener {
            presenter.onConfirmClick()
        }
    }

    override fun saveViewPenguins() {
        with(model.getPreferences()) {
            setPrefStrTitle(binding.tvTitle.text.toString())
            setPrefStrSubject(binding.tvSubject.text.toString())
            setPrefStrNumberPenguins(binding.tvPenguinsNumber.text.toString())
            setPrefStrNumberDays(binding.tvDaysNumber.text.toString())
            setPrefStrPenguins(binding.tvPenguins.text.toString())
            setPrefStrDays(binding.tvDays.text.toString())
            setPrefStrAgreement(binding.tvAgreement.text.toString())
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
        binding.tvAgreement.text =
            model.getPreferences().getPrefStrAgreement() ?: model.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
    }
}