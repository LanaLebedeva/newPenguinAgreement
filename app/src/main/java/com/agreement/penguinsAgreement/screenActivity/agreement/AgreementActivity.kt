package com.agreement.penguinsAgreement.screenActivity.agreement

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.agreement.penguinsAgreement.databinding.ActivityAgreementBinding
import com.agreement.penguinsAgreement.screenActivity.registration.MainActivity


class AgreementActivity : AppCompatActivity(), AgreementActivityI {
    private lateinit var bindingAgreement: ActivityAgreementBinding
    private lateinit var presenter: PresenterAgreement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAgreement = ActivityAgreementBinding.inflate(layoutInflater)
        val view = bindingAgreement.root
        setContentView(view)
        presenter = PresenterAgreement(this)
        presenter.initAgreement()
        initListeners()
    }


    private fun initListeners() {
        bindingAgreement.btnAgree.setOnClickListener {}
        bindingAgreement.btnDisagree.setOnClickListener {
            presenter.onBtnDisagreeClick()
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressedClick()
        super.onBackPressed()
    }

    override fun updateAgreement(agreement: String) {
        bindingAgreement.tvAgreementText.text = agreement
    }

    override fun navigateToRegistration() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}