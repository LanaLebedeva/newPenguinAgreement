package com.agreement.penguinsAgreement.screenActivity.registration

import androidx.annotation.PluralsRes
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins

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

    override fun onFormAgreementClick() {
        viewPenguin.onSaveViewPenguins()
        if (!ModelPenguins.updateAgreement()) {
            viewPenguin.setSnackbarAgreement()
        }
        viewPenguin.updateAgreement()
    }

    fun onConfirmClick() {
        //TODO перейти на новый экрвн
    }
}