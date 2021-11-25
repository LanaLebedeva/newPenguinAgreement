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
        with(model.getBinding()) {
            this?.tvTitle?.setText(model.getPreferences()?.getString(STR_KEY_TITLE, ""))
            this?.tvSubject?.setText(model.getPreferences()?.getString(STR_KEY_SUBJECT, ""))
            this?.tvPenguinsNumber?.setText(model.getPreferences()?.getString(STR_KEY_NUMBER_PENGUINS, ""))
            this?.tvDaysNumber?.setText(model.getPreferences()?.getString(STR_KEY_NUMBER_DAYS, ""))
            this?.tvPenguins?.text =
                model.getPreferences()?.getString(STR_PENGUINS, model.getResources()?.getString(R.string.text_penguins))
            this?.tvDays?.text = model.getPreferences()?.getString(STR_DAYS, model.getResources()?.getString(R.string.text_days))
            this?.tvAgreement?.text = model.getPreferences()?.getString(
                STR_KEY_AGREEMENT,
                model.getResources()?.getString(R.string.text_there_will_be_an_agreement)
            )
        }
    }

    override fun initListeners() {
        model.getBinding()?.tvDaysNumber?.doAfterTextChanged {
            presenter.makePlural(model.getBinding()?.tvDaysNumber?.text.toString(), R.plurals.days)
        }
        model.getBinding()?.tvPenguinsNumber?.doAfterTextChanged {
            presenter.makePlural(model.getBinding()?.tvPenguinsNumber?.text.toString(), R.plurals.penguins)
        }
        model.getBinding()?.btnFormAgreement?.setOnClickListener {
            presenter.makeAgreement()

        }
    }

    override fun onPauseViewPenguins() {
        val prefEditor: SharedPreferences.Editor? = model.getPreferences()?.edit()
        with(prefEditor) {
            this?.putString(STR_KEY_TITLE, model.getBinding()?.tvTitle?.text.toString())
            this?.putString(STR_KEY_SUBJECT, model.getBinding()?.tvSubject?.text.toString())
            this?.putString(STR_KEY_NUMBER_PENGUINS, model.getBinding()?.tvPenguinsNumber?.text.toString())
            this?.putString(STR_KEY_NUMBER_DAYS, model.getBinding()?.tvDaysNumber?.text.toString())
            this?.putString(STR_PENGUINS, model.getBinding()?.tvPenguins?.text.toString())
            this?.putString(STR_DAYS, model.getBinding()?.tvDays?.text.toString())
            this?.putString(STR_KEY_AGREEMENT, model.getBinding()?.tvAgreement?.text.toString())
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