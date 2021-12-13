package com.agreement.penguinsAgreement.screenActivity.registration

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

private const val FAKE_STRING_DAYS = "days"
private const val FAKE_STRING_AGREEMENTS = "there will be an agreement here"
private const val FAKE_STRING_NUMBER_DAYS = "21"

@RunWith(MockitoJUnitRunner::class)
class PresenterPenguinsTest : TestCase() {

    //    @Mock
//    private lateinit var model: ModelPenguins
    @Mock
    private lateinit var viewPenguin: ContractRegistration.View


    public override fun setUp() {
        super.setUp()
    }

    public override fun tearDown() {}

    fun testInitPresenter() {}

    fun testInitView() {}

    fun testSaveViewPenguins() {}

    @Test
    fun testOnDaysNumberTextChange() {
//        `when`(model.getPluralDays())
//            .thenReturn(FAKE_STRING_DAYS)
//        `when`(model.getAgreement())
//            .thenReturn(FAKE_STRING_AGREEMENTS)
//        `when`(viewPenguin.updateDays(FAKE_STRING_NUMBER_DAYS))
//            .thenReturn(Unit)
//        `when`(viewPenguin.updateAgreement(FAKE_STRING_AGREEMENTS))
//                .thenThrow(IllegalStateException.class)
//            .thenReturn(Unit)
//
//        doNothing().when(viewPenguin) .updateAgreement(FAKE_STRING_NUMBER_DAYS)){  }
//        val myObjectUnderTest = PresenterPenguins()
//        myObjectUnderTest.onDaysNumberTextChange(FAKE_STRING_NUMBER_DAYS)
//        val result = ModelPenguins.getNumberDays()
//        Log.e("TEST", "result = $result")
//        assertThat(result, `is`(FAKE_STRING_NUMBER_DAYS))
    }

    fun testOnPenguinsNumberTextChange() {}

    fun testOnFormAgreementChange() {}

    fun testOnFormAgreementButtonClick() {}

    fun testOnConfirmItButtonClick() {}

    fun testOnTitleTextChange() {}

    fun testOnSubjectTextChange() {}
}