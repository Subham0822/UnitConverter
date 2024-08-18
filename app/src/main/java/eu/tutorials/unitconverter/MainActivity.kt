package eu.tutorials.unitconverter
//the imports that you do not need, then do not add them otherwise it will occupy memory in the user's phone
import android.os.Bundle
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember{mutableStateOf("")}
    var inputUnit by remember{mutableStateOf("Select")}
    var outputUnit by remember{mutableStateOf("Select")}
    var iExpanded by remember{mutableStateOf(false)}
    var oExpanded by remember{mutableStateOf(false)}
    val conversionFactor = remember{mutableStateOf(1.0)}
    val oConversionFactor = remember{mutableStateOf(1.0)}

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull()?:0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).toInt() / 100.0
        outputValue = result.toString()

    }

    Column(
        modifier = Modifier.fillMaxSize( ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        //Here all the UI elements will be stacked below each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
        label = { Text(text = "Enter the value")})
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Here all the UI elements will be stacked next to each other
            //Input Box
            Box {
                //input button
                Button(onClick = {iExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()

                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //Output Box
            Box {
                //Text("Select")
                //Output Button
                Button(onClick = {oExpanded = true}) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()

                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue", style = MaterialTheme.typography.headlineSmall)
    }
}

//this is preview of the fun we are coding
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){ //the fun name should be of this format: fun <nameOfTheFunction>Preview
    UnitConverter() //then calling the fun we want to show preview for

}

