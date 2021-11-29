package com.agreement.penguinsAgreement.mvpArchitecture.penguinsView

import android.content.SharedPreferences
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract

class ViewPenguins : PenguinsContract.PenguinView {
    private val presenter =
        com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter.PresenterPenguins(this)
    private val model: ModelPenguins = ModelPenguins

    override fun initView() {
        with(model.getBinding()) {
            this?.tvTitle?.setText(model.getPreferences()?.getString(PREF_STR_TITLE, ""))
            this?.tvSubject?.setText(model.getPreferences()?.getString(PREF_STR_SUBJECT, ""))
            this?.tvPenguinsNumber?.setText(model.getPreferences()
                ?.getString(PREF_STR_NUMBER_PENGUINS, ""))
            this?.tvDaysNumber?.setText(model.getPreferences()?.getString(PREF_STR_NUMBER_DAYS, ""))
            this?.tvPenguins?.text =
                model.getPreferences()?.getString(PREF_STR_PENGUINS,
                    model.getResources()?.getString(R.string.text_penguins))
            this?.tvDays?.text = model.getPreferences()
                ?.getString(PREF_STR_DAYS, model.getResources()?.getString(R.string.text_days))
            this?.tvAgreement?.text = model.getPreferences()?.getString(
                PREF_STR_AGREEMENT,
                model.getResources()?.getString(R.string.text_there_will_be_an_agreement)
            )
        }
    }

    override fun initListeners() {
        model.getBinding()?.tvDaysNumber?.doAfterTextChanged {
            presenter.updatePluralNumberTextViews(model.getBinding()?.tvDaysNumber?.text.toString(),
                R.plurals.days)
        }
        model.getBinding()?.tvPenguinsNumber?.doAfterTextChanged {
            presenter.updatePluralNumberTextViews(model.getBinding()?.tvPenguinsNumber?.text.toString(),
                R.plurals.penguins)
        }
        model.getBinding()?.btnFormAgreement?.setOnClickListener {
            presenter.updateAgreementTextView()
        }
    }

    override fun onStopViewPenguins() {
        val prefEditor: SharedPreferences.Editor? = model.getPreferences()?.edit()
        with(prefEditor) {
            this?.putString(PREF_STR_TITLE, model.getBinding()?.tvTitle?.text.toString())
            this?.putString(PREF_STR_SUBJECT, model.getBinding()?.tvSubject?.text.toString())
            this?.putString(PREF_STR_NUMBER_PENGUINS,
                model.getBinding()?.tvPenguinsNumber?.text.toString())
            this?.putString(PREF_STR_NUMBER_DAYS, model.getBinding()?.tvDaysNumber?.text.toString())
            this?.putString(PREF_STR_PENGUINS, model.getBinding()?.tvPenguins?.text.toString())
            this?.putString(PREF_STR_DAYS, model.getBinding()?.tvDays?.text.toString())
            this?.putString(PREF_STR_AGREEMENT, model.getBinding()?.tvAgreement?.text.toString())
            this?.apply()
        }
    }

    override fun setPenguins(pluralsNumber: String?) {
        model.getBinding()?.tvPenguins?.text = pluralsNumber
    }

    override fun setDays(pluralsNumber: String?) {
        model.getBinding()?.tvDays?.text = pluralsNumber
    }

    override fun setAgreement(agreementReturn: String) {
        model.getBinding()?.tvAgreement?.text = agreementReturn
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