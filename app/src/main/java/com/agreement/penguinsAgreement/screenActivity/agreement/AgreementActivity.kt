package com.agreement.penguinsAgreement.screenActivity.agreement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agreement.penguinsAgreement.databinding.ActivityAgreementBinding
import com.agreement.penguinsAgreement.penguinsModel.ModelPenguins
import com.agreement.penguinsAgreement.screenActivity.registration.MainActivity


class AgreementActivity : AppCompatActivity() {
    private lateinit var bindingAgreement: ActivityAgreementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAgreement = ActivityAgreementBinding.inflate(layoutInflater)
        val view = bindingAgreement.root
        setContentView(view)

        bindingAgreement.tvAgreementText.text = ModelPenguins.getAgreement()
        initListeners()
    }

    private fun initListeners() {
        bindingAgreement.btnAgree.setOnClickListener {}
        bindingAgreement.btnDisagree.setOnClickListener {
            ModelPenguins.getPreferences().setPrefBoolOnAgreement(false)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        ModelPenguins.getPreferences().setPrefBoolOnAgreement(false)
        super.onBackPressed()
    }
}