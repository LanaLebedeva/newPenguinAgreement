package com.agreement.penguinsAgreement.model

import android.content.res.Resources
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.contract.Contract

class ModelAgreementSource(private val resources: Resources) : Contract.ModelAgreement {
    private var agreement = resources.getString(R.string.main_there_will_be_an_agreement_here)

    override fun getAgreement(): String =
        agreement

    override fun makeAgreement(
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

    }
}