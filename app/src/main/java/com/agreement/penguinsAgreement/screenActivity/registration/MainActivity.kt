package com.agreement.penguinsAgreement.screenActivity.registration

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.R
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.agreement.penguinsAgreement.screenActivity.agreement.AgreementActivity
import com.agreement.penguinsAgreement.screenActivity.reconfirmation.ReconfirmationActivity
import com.agreement.penguinsAgreement.utils.PreferenceUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: PresenterPenguins
    private lateinit var binding: ActivityMainBinding


    private var model: ModelPenguins = ModelPenguins

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val preferences = PreferenceUtil(this)
        presenter = PresenterPenguins(this)

        model.initPreferenceResources(preferences, resources)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            initView()
        }
        initListeners()
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    override fun onStop() {
        super.onStop()
        saveViewPenguins()
    }
    private fun initView() {
        with(binding) {
            tvTitle.setText(model.getPreferences().getPrefStrTitle())
            tvSubject.setText(model.getPreferences().getPrefStrSubject())
            tvPenguinsNumber.setText(model.getPreferences().getPrefStrNumberPenguins())
            tvDaysNumber.setText(model.getPreferences().getPrefStrNumberDays())
            tvPenguins.text =
                model.getPreferences().getPrefStrPenguins() ?: model.getResources().getString(
                    R.string.text_penguins
                )
            tvDays.text = model.getPreferences().getPrefStrDays() ?: model.getResources()
                .getString(R.string.text_days)
            tvAgreement.text =
                model.getPreferences().getPrefStrAgreement() ?: model.getResources()
                    .getString(R.string.text_there_will_be_an_agreement)
        }
    }

    private fun initListeners() {

        binding.tvDaysNumber.doAfterTextChanged {
            presenter.onPluralNumberTextViewsChange(
                binding.tvDaysNumber.text.toString(),
                R.plurals.days
            )
            presenter.onFormAgreementClick()
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            presenter.onPluralNumberTextViewsChange(
                binding.tvPenguinsNumber.text.toString(),
                R.plurals.penguins
            )
        }
        binding.btnFormAgreement.setOnClickListener {
            presenter.onFormAgreementClick()
        }
        binding.tvDaysNumber.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == android.view.KeyEvent.KEYCODE_ENTER) {
                presenter.onFormAgreementClick()
            }
            false
        }
        binding.btmConfirmIt.setOnClickListener {
            val intent = Intent(this, AgreementActivity::class.java)
            startActivity(intent)
        }
    }

    fun saveViewPenguins() {
        with(model.getPreferences()) {
            setPrefStrTitle(binding.tvTitle.text.toString())
            setPrefStrSubject(binding.tvSubject.text.toString())
            setPrefStrNumberPenguins(binding.tvPenguinsNumber.text.toString())
            setPrefStrNumberDays(binding.tvDaysNumber.text.toString())
            setPrefStrPenguins(binding.tvPenguins.text.toString())
            setPrefStrDays(binding.tvDays.text.toString())
            setPrefStrAgreement(binding.tvAgreement.text.toString())
        }
    }

    fun updatePenguins(pluralsNumber: String?) {
        binding.tvPenguins.text = pluralsNumber
    }

    fun updateDays(pluralsNumber: String?) {
        binding.tvDays.text = pluralsNumber
    }

    fun setAgreement(agreementReturn: String) {
        binding.tvAgreement.text = agreementReturn
    }

    fun setSnackbarAgreement() {
        binding.constraintLayout.let {
            Snackbar.make(
                it,
                R.string.msg_fill_in_the_fields,
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    fun updateAgreement() {
        binding.tvAgreement.text =
            model.getPreferences().getPrefStrAgreement() ?: model.getResources()
                .getString(R.string.text_there_will_be_an_agreement)
    }
}