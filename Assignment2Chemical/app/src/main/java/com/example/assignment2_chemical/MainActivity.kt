package com.example.assignment2_chemical

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.assignment2_chemical.ui.theme.Assignment2chemicalTheme
import com.example.assignment2_chemical.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    var chemicals = mutableListOf("Gold", "Charcoal", "Aluminium", "Wood", "Plastic")
    private lateinit var binding: ActivityMainBinding

    fun getRandomChemical():String{
        return chemicals[Random.nextInt(chemicals.size)]
    }


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.chemicalText.text= getRandomChemical()

        binding.guessbtn.setOnClickListener {
            val newChemicalName = binding.newChemicalInput.text.toString().trim()
            val randomChemicalName = getRandomChemical()

            binding.chemicalText.text = randomChemicalName

            println(newChemicalName)
            println(randomChemicalName)

            if(newChemicalName == randomChemicalName) {
                binding.infoText.text= "Congrats you guessed it!"

                return@setOnClickListener
            }

            binding.infoText.text = "Sorry, wrong guess"
        }

        binding.addChemicalbtn.setOnClickListener {
            val newChemicalName = binding.newChemicalInput.text.toString().trim()

            if(chemicals.indexOf(newChemicalName) > -1) {
                binding.infoText.text= "Chemical already exists in the list"

                return@setOnClickListener
            }
            binding.infoText.text= ""
            chemicals.add(newChemicalName)
            binding.newChemicalInput.text.clear()
            // Close the keyboard
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.newChemicalInput.windowToken, 0)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2chemicalTheme {
        Greeting("Android")
    }
}