package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    // binding : objeto que vai acessar as views
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInFieldText = binding.etCostOfService.text.toString()
        val cost = stringInFieldText.toDouble()
        val selectedParcent = binding.tipOptions.checkedRadioButtonId
        val tipPorcentage = when (selectedParcent) {
            R.id.id_option_twenty_parcent -> 0.20
            R.id.id_option_eighteen_parcent -> 0.18
            else -> 0.15
        }
        var tip = tipPorcentage * cost // Calculando a porcentagem
        val roundUp = binding.roundUpSwitch.isChecked // passando a view através do recurso binding
        if (roundUp) { // se o switch de arrendondamento do dinheeiro estiver selecionado
            tip = kotlin.math.ceil(tip)  // kotlin.math.cell(tip) funcao existente no kotlin para arredondar
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip) // Isso fornece um formatador de número que pode ser usado para formatar números como moeda
        binding.tvTextResult.text = getString(R.string.cost_service, formattedTip)
    }
}
