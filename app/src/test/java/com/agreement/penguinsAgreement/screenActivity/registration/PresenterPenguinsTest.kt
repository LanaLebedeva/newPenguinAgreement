package com.agreement.penguinsAgreement.screenActivity.registration

import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

private const val FAKE_STRING_DAYS = "days"
private const val FAKE_STRING_AGREEMENTS = "agreement"
private const val FAKE_STRING_NUMBER_DAYS = "21"

@RunWith(MockitoJUnitRunner::class)
class PresenterPenguinsTest : TestCase() {
    @Mock
    private lateinit var viewPenguin: ContractRegistration.View

    //    @Mock
    private lateinit var presenter: PresenterPenguins

    //    @Mock
    private lateinit var model: ModelPenguins

    //    @Mock
//    private val viewPenguin = mock(ContractRegistration.View.javaClass)
//    @Mock
//    private val viewPenguin: ContractRegistration.View = mock<ContractRegistration.View>()
//    @Mock
//    private val model: ModelPenguins = mock(ModelPenguins.class,)
//    @Mock
//    private val resources: Resources = mock(Resources)
//
//    private val presenter: PresenterPenguins = PresenterPenguins()
//

    @Before
    public override fun setUp() {
        super.setUp()
        // В Mockito есть очень удобный способ инъекции mocks с помощью аннотации @Mock.
        // Чтобы внедрить макеты в тест необходимо вызвать метод initMocks.
        MockitoAnnotations.openMocks(this)
        // Получение ссылки на тестируемый класс
        presenter = PresenterPenguins()
        // инициализация тестируемого класса
        presenter.initPresenter(viewPenguin)
        // Ведущий не будет обновлять представление, пока оно не станет активным.
//        `when`(viewPenguin.isActive).thenReturn(true)
        model = ModelPenguins
//        val sharedPreference: SharedPreferences = mock(SharedPreferences.class)
//        val contex: Contex = mock(Contex.class)
//        ModelPenguins.initPreferenceResources(PreferenceUtil(Penguins), resources)
    }

    public override fun tearDown() {}

    fun testInitPresenter() {}

    fun testInitView() {}

    fun testSaveViewPenguins() {}

    @Test
    fun testOnDaysNumberTextChange() {
        //       val mockviewPenguin = mock(ContractRegistration.View::class.java)
        //       doNothing().doThrow(RuntimeException()).when(mockviewPenguin.updateAgreement(FAKE_STRING_NUMBER_DAYS)) { }
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
//        doNothing().doThrow(RuntimeException())
//            .when (viewPenguin).updateAgreement(
//                FAKE_STRING_AGREEMENTS,
//            )
//
//        )when(viewPenguin.updateAgreement() {(FAKE_STRING_NUMBER_DAYS)) {}
//        val myObjectUnderTest = PresenterPenguins()
//        myObjectUnderTest.onDaysNumberTextChange(FAKE_STRING_NUMBER_DAYS)
//        val result = ModelPenguins.getNumberDays()
//        Log.e("TEST", "result = $result")
//        assertThat(result, `is`(FAKE_STRING_NUMBER_DAYS))

    }

    fun testOnPenguinsNumberTextChange() {}

    fun testOnFormAgreementChange() {}

    fun testOnFormAgreementButtonClick() {}

    fun testOnConfirmItButtonClick() {
        //нажата кнопка confirmIt
        presenter.onConfirmItButtonClick()
        //
        verify(viewPenguin).setSnackbarAgreement()
    }

    @Test
    fun testOnTitleTextChange() {
        presenter.onTitleTextChange(FAKE_STRING_AGREEMENTS)
        verify(viewPenguin).updateTitle(FAKE_STRING_AGREEMENTS)
    }

    fun testOnSubjectTextChange() {}
}

//https://github.com/android/architecture-samples/blob/todo-mvp-kotlin/todoapp/app/src/test/java/com/example/android/architecture/blueprints/todoapp/tasks/TasksPresenterTest.kt