package com.agreement.penguinsAgreement.model

import com.agreement.penguinsAgreement.contract.Contract

class ModelAgreementSource(_agreement:String): Contract.ModelAgreement {
    private var agreement: String = _agreement
    private val agreementNull = _agreement

    override fun getAgreement(): String =
        agreement

    override fun makeAgreement(
        text1: String,
        text2: String,
        number1: String,
        number2: String,
        agreementBegin: String,
        agreementEnd: String
    ) {
        agreement = if (text1 !="" && text2 != "" && number1 != "" && number2 !="") {
            "$agreementBegin $text1, $text2, $number1, $number2 $agreementEnd \n\n\n"
        } else {
            agreementNull
        }

    }
}