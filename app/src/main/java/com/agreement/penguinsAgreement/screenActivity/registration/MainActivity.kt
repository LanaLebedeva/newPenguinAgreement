package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.screenActivity.agreement.AgreementActivity
import com.agreement.penguinsAgreement.utils.PreferenceUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), ViewRegistration {

    private lateinit var presenter: PresenterPenguins
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PresenterPenguins()
        presenter.initPresenter(this)
        presenter.checkLeadingActivity()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            presenter.initView()
        }
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        presenter.initView()
    }

    override fun onStop() {
        presenter.saveViewPenguins(
            binding.tvTitle.text.toString(),
            binding.tvSubject.text.toString(),
            binding.tvPenguinsNumber.text.toString(),
            binding.tvDaysNumber.text.toString()
        )
        super.onStop()

    }

    override fun initListeners() {
        binding.tvDaysNumber.doAfterTextChanged {
            presenter.onDaysNumberTextViewChange(binding.tvDaysNumber.text.toString())
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            presenter.onPenguinsNumberTextViewChange(binding.tvPenguinsNumber.text.toString())
        }
        binding.btnFormAgreement.setOnClickListener {
            presenter.onFormAgreementClick()
        }
        binding.tvTitle.doAfterTextChanged {
            presenter.onTitleTextViewChange(binding.tvTitle.text.toString())
        }
        binding.tvSubject.doAfterTextChanged {
            presenter.onSubjectTextViewChange(binding.tvSubject.text.toString())
        }
        binding.tvDaysNumber.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                presenter.onFormAgreementClick()
            }
            false
        }
        binding.btmConfirmIt.setOnClickListener {
            presenter.onConfirmItClick()
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

    override fun startAgreement() {
        val intent = Intent(this, AgreementActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}