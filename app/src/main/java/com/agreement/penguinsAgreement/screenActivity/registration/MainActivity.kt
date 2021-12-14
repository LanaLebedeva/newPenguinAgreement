package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.screenActivity.agreement.AgreementActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),
    ContractRegistration.View {

    private lateinit var presenter: PresenterPenguins
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PresenterPenguins(this, ModelPenguins)

        //TODO как правильно передать ContractRegistration.View -> в коде ниже приведение по умолчанию?
        presenter.initPresenter(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            presenter.initView()
        }
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            tvDaysNumber.doAfterTextChanged { text ->
                presenter.onDaysNumberTextChange(text.toString())
            }
            tvPenguinsNumber.doAfterTextChanged { text ->
                presenter.onPenguinsNumberTextChange(text.toString())
            }
            btnFormAgreement.setOnClickListener {
                presenter.onFormAgreementButtonClick()
            }
            tvTitle.doAfterTextChanged { text ->
                presenter.onTitleTextChange(text.toString())
            }
            tvSubject.doAfterTextChanged { text ->
                presenter.onSubjectTextChange(text.toString())
            }
            tvDaysNumber.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                    presenter.onFormAgreementButtonClick()
                }
                false
            }
            btmConfirmIt.setOnClickListener {
                presenter.onConfirmItButtonClick()
            }
        }
    }

    override fun updateAgreement(agreement: String) {
        binding.tvAgreement.text = agreement
    }

    override fun updateTitle(title: String) {
        binding.tvTitle.setText(title)
    }

    override fun updateSubject(subject: String) {
        binding.tvSubject.setText(subject)
    }

    override fun updateNumberPenguins(numberPenguins: String) {
        binding.tvPenguinsNumber.setText(numberPenguins)
    }

    override fun updatePenguins(pluralsNumber: String) {
        binding.tvPenguins.text = pluralsNumber
    }

    override fun updateNumberDays(numberDays: String) {
        binding.tvDaysNumber.setText(numberDays)
    }

    override fun updateDays(pluralsNumber: String) {
        binding.tvDays.text = pluralsNumber
    }

    override fun setSnackbarAgreement() {
        binding.constraintLayout.let {
            Snackbar.make(
                it,
                R.string.msg_fill_in_the_fields,
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    override fun navigateToAgreement() {
        val intent = Intent(this, AgreementActivity::class.java)
        finish()
        startActivity(intent)
    }
}