package com.agreement.penguinsAgreement

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.agreement.penguinsAgreement.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(STR_FILE_SHARED_PREFERENCES, MODE_PRIVATE)
        initView()
        initListeners()
    }

    private fun initView() {
        with(binding) {
            tvTitle.setText(preferences.getString(STR_KEY_TITLE, ""))
            tvSubject.setText(preferences.getString(STR_KEY_SUBJECT, ""))
            tvPenguinsNumber.setText(preferences.getString(STR_KEY_NUMBER_PENGUINS, ""))
            tvDaysNumber.setText(preferences.getString(STR_KEY_NUMBER_DAYS, ""))
            tvPenguins.text =
                preferences.getString(STR_PENGUINS, resources.getString(R.string.text_penguins))
            tvDays.text = preferences.getString(STR_DAYS, resources.getString(R.string.text_days))
            tvAgreement.text = preferences.getString(
                STR_KEY_AGREEMENT,
                resources.getString(R.string.text_there_will_be_an_agreement)
            )
        }
    }

    private fun initListeners() {
        binding.tvDaysNumber.doAfterTextChanged {
            makePlural(binding.tvDaysNumber.text.toString(), R.plurals.days)
        }
        binding.tvPenguinsNumber.doAfterTextChanged {
            makePlural(binding.tvPenguinsNumber.text.toString(), R.plurals.penguins)
        }
        binding.btnConfirm.setOnClickListener {
            makeAgreement()
        }
    }

    private fun makePlural(number: String, plurals: Int) {
        val parsInt: Int = try {
            Integer.parseInt(number)
        } catch (e: NumberFormatException) {
            Integer.MAX_VALUE
        }
        val plural = resources.getQuantityString(
            plurals,
            parsInt
        )
        when (plurals) {
            R.plurals.penguins -> binding.tvPenguins.text = plural
            R.plurals.days -> binding.tvDays.text = plural
        }
    }

    private fun makeAgreement() {
        val title: String = binding.tvTitle.text.toString()
        val subject: String = binding.tvSubject.text.toString()
        val numberPenguins: String = binding.tvPenguinsNumber.text.toString()
        val numberDays: String = binding.tvDaysNumber.text.toString()

        binding.tvAgreement.text =
            if (title != "" && subject != "" && numberPenguins != "" && numberDays != "") {
                "${resources.getString(R.string.text_begin_agreement)} $title, $subject, $numberPenguins, $numberDays. ${
                    resources.getString(R.string.text_end_agreement)
                }"
            } else {
                val contextView = findViewById<View>(R.id.linearLayout)
                Snackbar.make(
                    contextView,
                    R.string.msg_fill_in_the_fields,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                resources.getString(R.string.text_there_will_be_an_agreement)
            }
    }

    override fun onPause() {
        super.onPause()
        val prefEditor: SharedPreferences.Editor = preferences.edit()
        with(prefEditor) {
            putString(STR_KEY_TITLE, binding.tvTitle.text.toString())
            putString(STR_KEY_SUBJECT, binding.tvSubject.text.toString())
            putString(STR_KEY_NUMBER_PENGUINS, binding.tvPenguinsNumber.text.toString())
            putString(STR_KEY_NUMBER_DAYS, binding.tvDaysNumber.text.toString())
            putString(STR_PENGUINS, binding.tvPenguins.text.toString())
            putString(STR_DAYS, binding.tvDays.text.toString())
            putString(STR_KEY_AGREEMENT, binding.tvAgreement.text.toString())
            apply()
        }
    }

    companion object {
        private const val STR_FILE_SHARED_PREFERENCES = "TASK_PENGUIN"
        private const val STR_KEY_AGREEMENT = "AGREEMENT_VARIABLE"
        private const val STR_KEY_NUMBER_PENGUINS = "NUMBER_PENGUIN_VARIABLE"
        private const val STR_KEY_NUMBER_DAYS = "NUMBER_DAYS_VARIABLE"
        private const val STR_KEY_TITLE = "TITLE_KEY"
        private const val STR_KEY_SUBJECT = "SUBJECT_KEY"
        private const val STR_PENGUINS = "PENGUINS"
        private const val STR_DAYS = "DAYS"
    }
}