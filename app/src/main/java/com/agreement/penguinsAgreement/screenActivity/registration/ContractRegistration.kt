package com.agreement.penguinsAgreement.screenActivity.registration

interface ViewRegistration {
    fun initListeners()
    fun updateAgreement(agreement: String)
    fun updateTitle(title: String)
    fun updateSubject(subject: String)
    fun updateNumberPenguins(numberPenguins: String)
    fun updatePenguins(pluralsNumber: String)
    fun updateNumberDays(numberDays: String)
    fun updateDays(pluralsNumber: String)
    fun setSnackbarAgreement()
    fun startAgreement()
}

interface PresenterPenguin {
    fun initPresenter(_viewPenguin: MainActivity)
    fun initView()
    fun saveViewPenguins(title: String, subject: String, penguinsNumber: String, daysNumber: String)
    fun onDaysNumberTextViewChange(numberDays: String) {

    }
}