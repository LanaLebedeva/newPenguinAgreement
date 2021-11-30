package com.agreement.penguinsAgreement.mvpArchitecture.penguinsPresenter

import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsContract.PenguinsContract
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.mvpArchitecture.penguinsView.ViewPenguins

class PresenterPenguins(private val viewPenguin: ViewPenguins) : PenguinsContract.PenguinPresenter {

    override fun changedPluralNumberTextViews(
        numberToString: String,
        @PluralsRes plurals: Int,
    ): String {
        val pluralsModel: String = ModelPenguins.updatePlural(numberToString, plurals)
        when (plurals) {
            R.plurals.penguins -> viewPenguin.updatePenguins(pluralsModel)
            R.plurals.days -> viewPenguin.updateDays(pluralsModel)
        }
        return pluralsModel
    }

    override fun changedAgreementTextView() {
        viewPenguin.onSaveViewPenguins()
        if (!ModelPenguins.updateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement()
    }
}