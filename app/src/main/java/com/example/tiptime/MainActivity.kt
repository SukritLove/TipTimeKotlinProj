package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val byn : Button = findViewById(R.id.calculate_Btn)


        // Old way with findViewById()
        binding.calculateBtn.setOnClickListener{calculateTip()}
        binding.costServiceInput.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}


}
    private fun calculateTip(){
        val costStr = binding.costServiceInput.text.toString()
        val cost = costStr.toDoubleOrNull()
        if(cost!= null) {
            var tip = cost * getSelectedId()
            val roundUp = binding.roundUpSw.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.resultTip.text = getString(R.string.tip_amount, formattedTip)
        }
        else{
            binding.resultTip.text = ""
        }


    }

    private fun getSelectedId():Double {
        val tipPercentage = when(binding.tipOption.checkedRadioButtonId){
            R.id.option_amazing_20 -> 0.20
            R.id.option_good_18 -> 0.18
            else -> 0.15
        }
        return tipPercentage
    }

    private fun handleKeyEvent(view: View, keyCode: Int):Boolean{
        if(keyCode==KeyEvent.KEYCODE_ENTER){
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
            return true
        }
        return false
    }
}