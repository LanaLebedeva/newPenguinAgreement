package com.agreement.penguinsAgreement.presenter

import com.agreement.penguinsAgreement.MainActivity
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.contract.Contract
import com.agreement.penguinsAgreement.model.ModelAgreementSource
import java.lang.NumberFormatException

class PresenterMainActivity(mainActivity: MainActivity) : Contract.PresenterMain {
    private val view: MainActivity = mainActivity
    private val model = ModelAgreementSource(view.resources)

    override fun getAgreement(): String {
        return model.getAgreement()
    }

    override fun makeAgreement(
        text1: String,
        text2: String,
        numberPenguins: String,
        numberDays: String
    ) {
        model.makeAgreement(
            text1,
            text2,
            numberPenguins,
            numberDays,
        )
        view.updateAgreement()
    }

    override fun makePlural(number: String, plurals: Int) {
        val parsInt: Int = try {
            Integer.parseInt(number)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
        }
        val plural = view.resources.getQuantityString(
            plurals,
            parsInt
        )
        when (plurals) {
            R.plurals.pluralsPenguins -> view.updatePlPinguins(plural)
            R.plurals.pluralsDays -> view.updatePlDays(plural)
        }
    }
}