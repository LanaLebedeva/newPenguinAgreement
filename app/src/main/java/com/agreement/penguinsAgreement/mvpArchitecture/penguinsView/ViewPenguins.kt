package com.agreement.penguinsAgreement.mvpArchitecture.penguinsView

import android.content.SharedPreferences
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter.PresenterPenguins
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract

class ViewPenguins: PenguinsContract.PenguinView {
    private val presenter = PresenterPenguins
    private val model: ModelPenguins = ModelPenguins
    override fun initView() {
        with(model.binding) {
            this?.tvTitle?.setText(model.preferences?.getString(STR_KEY_TITLE, ""))
            this?.tvSubject?.setText(model.preferences?.getString(STR_KEY_SUBJECT, ""))
            this?.tvPenguinsNumber?.setText(model.preferences?.getString(STR_KEY_NUMBER_PENGUINS, ""))
            this?.tvDaysNumber?.setText(model.preferences?.getString(STR_KEY_NUMBER_DAYS, ""))
            this?.tvPenguins?.text =
                model.preferences?.getString(STR_PENGUINS, model.resources?.getString(R.string.text_penguins))
            this?.tvDays?.text = model.preferences?.getString(STR_DAYS, model.resources?.getString(R.string.text_days))
            this?.tvAgreement?.text = model.preferences?.getString(
                STR_KEY_AGREEMENT,
                model.resources?.getString(R.string.text_there_will_be_an_agreement)
            )
        }
    }

    override fun initListeners() {
        model.binding?.tvDaysNumber?.doAfterTextChanged {
            presenter.makePlural(model.binding?.tvDaysNumber?.text.toString(), R.plurals.days)
        }
        model.binding?.tvPenguinsNumber?.doAfterTextChanged {
            presenter.makePlural(model.binding?.tvPenguinsNumber?.text.toString(), R.plurals.penguins)
        }
        model.binding?.btnConfirm?.setOnClickListener {
            presenter.makeAgreement()

        }
    }

    override fun onPauseViewPenguins() {
        val prefEditor: SharedPreferences.Editor? = model.preferences?.edit()
        with(prefEditor) {
            this?.putString(STR_KEY_TITLE, model.binding?.tvTitle?.text.toString())
            this?.putString(STR_KEY_SUBJECT, model.binding?.tvSubject?.text.toString())
            this?.putString(STR_KEY_NUMBER_PENGUINS, model.binding?.tvPenguinsNumber?.text.toString())
            this?.putString(STR_KEY_NUMBER_DAYS, model.binding?.tvDaysNumber?.text.toString())
            this?.putString(STR_PENGUINS, model.binding?.tvPenguins?.text.toString())
            this?.putString(STR_DAYS, model.binding?.tvDays?.text.toString())
            this?.putString(STR_KEY_AGREEMENT, model.binding?.tvAgreement?.text.toString())
            this?.apply()
        }
    }

    companion object {
        private const val STR_KEY_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val STR_KEY_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val STR_KEY_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val STR_KEY_TITLE = "TITLE_KEY"
        private const val STR_KEY_SUBJECT = "SUBJECT_KEY"
        private const val STR_PENGUINS = "PENGUINS"
        private const val STR_DAYS = "DAYS"
    }
}