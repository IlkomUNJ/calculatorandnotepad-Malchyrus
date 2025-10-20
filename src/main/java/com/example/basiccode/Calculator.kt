package com.example.basiccode

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorUI() {
    var input by remember { mutableStateOf("") }
    val context = LocalContext.current
    val activity = context as? Activity
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        ScientificCalculatorUI(
            input = input,
            onInputChange = { input = it },
            onSwitchToBasic = {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        )
    } else {
        BasicCalculatorUI(
            input = input,
            onInputChange = { input = it },
            onSwitchToSci = {
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        )
    }
}

@Composable
fun BasicCalculatorUI(
    input: String,
    onInputChange: (String) -> Unit,
    onSwitchToSci: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.weight(5f))
        Text(
            text = input,
            fontSize = 36.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )
        Spacer(modifier = Modifier.weight(1f))
        Column {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CalcButton("C", backgroundColor = Color(0xFFF44336)) { onInputChange("") }
                CalcButton("%", backgroundColor = Color(0xFF9E9E9E)) { onInputChange(input + "%") }
                CalcButton("<-", backgroundColor = Color(0xFF9E9E9E)) {
                    if (input.isNotEmpty()) onInputChange(input.dropLast(1))
                }
                CalcButton("/", backgroundColor = Color(0xFF9E9E9E)) { onInputChange(input + "/") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CalcButton("7", Color.DarkGray) { onInputChange(input + "7") }
                CalcButton("8", Color.DarkGray) { onInputChange(input + "8") }
                CalcButton("9", Color.DarkGray) { onInputChange(input + "9") }
                CalcButton("X", Color(0xFF9E9E9E)) { onInputChange(input + "*") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CalcButton("4", Color.DarkGray) { onInputChange(input + "4") }
                CalcButton("5", Color.DarkGray) { onInputChange(input + "5") }
                CalcButton("6", Color.DarkGray) { onInputChange(input + "6") }
                CalcButton("-", Color(0xFF9E9E9E)) { onInputChange(input + "-") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CalcButton("1", Color.DarkGray) { onInputChange(input + "1") }
                CalcButton("2", Color.DarkGray) { onInputChange(input + "2") }
                CalcButton("3", Color.DarkGray) { onInputChange(input + "3") }
                CalcButton("+", Color(0xFF9E9E9E)) { onInputChange(input + "+") }
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                CalcButton("Sc", Color(0xFF9E9E9E)) { onSwitchToSci() }
                CalcButton("0", Color.DarkGray) { onInputChange(input + "0") }
                CalcButton(".", Color.DarkGray) { onInputChange(input + ".") }
                CalcButton("=", Color(0xFF4CAF50)) { }
            }
        }
    }
}

@Composable
fun ScientificCalculatorUI(
    input: String,
    onInputChange: (String) -> Unit,
    onSwitchToBasic: () -> Unit
) {
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Text(
            text = input,
            fontSize = 28.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )
        Text(
            text = result,
            fontSize = 20.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
        )
        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Row 1: Functions
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CalcButton("sin") { onInputChange(input + "sin(") }
            CalcButton("cos") { onInputChange(input + "cos(") }
            CalcButton("tan") { onInputChange(input + "tan(") }
            CalcButton("^") { onInputChange(input + "^") }
            CalcButton("√") { onInputChange(input + "√(") }
        }

        // 🔹 Row 2: More functions + controls
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CalcButton("ln") { onInputChange(input + "ln(") }
            CalcButton("log") { onInputChange(input + "log(") }
            CalcButton("π") { onInputChange(input + "π") }
            CalcButton("C", Color(0xFFE53935)) {
                onInputChange("")
                result = ""
            }
            CalcButton("/", Color(0xFF9E9E9E)) { onInputChange(input + "/") }
        }

        // 🔹 Row 3: Numbers 7–9 + × −
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CalcButton("7", Color.DarkGray) { onInputChange(input + "7") }
            CalcButton("8", Color.DarkGray) { onInputChange(input + "8") }
            CalcButton("9", Color.DarkGray) { onInputChange(input + "9") }
            CalcButton("×", Color(0xFF9E9E9E)) { onInputChange(input + "*") }
            CalcButton("-", Color(0xFF9E9E9E)) { onInputChange(input + "-") }
        }

        // 🔹 Row 4: Numbers 4–6 + + .
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CalcButton("4", Color.DarkGray) { onInputChange(input + "4") }
            CalcButton("5", Color.DarkGray) { onInputChange(input + "5") }
            CalcButton("6", Color.DarkGray) { onInputChange(input + "6") }
            CalcButton("+", Color(0xFF9E9E9E)) { onInputChange(input + "+") }
            CalcButton(".", Color.DarkGray) { onInputChange(input + ".") }
        }

        // 🔹 Row 5: Basic, numbers 0–3, equals
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            CalcButton("Basic", Color(0xFF9E9E9E)) { onSwitchToBasic() }
            CalcButton("0", Color.DarkGray) { onInputChange(input + "0") }
            CalcButton("1", Color.DarkGray) { onInputChange(input + "1") }
            CalcButton("2", Color.DarkGray) { onInputChange(input + "2") }
            CalcButton("3", Color.DarkGray) { onInputChange(input + "3") }
            CalcButton("=", Color(0xFF4CAF50)) {}
        }
    }
}


@Composable
fun CalcButton(
    text: String,
    backgroundColor: Color = Color(0xFF6200EE),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp).size(80.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
    ) {
        Text(text, fontSize = 24.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun CalcButton1(
    text: String,
    backgroundColor: Color = Color(0xFF6200EE),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp).size(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
    ) {
        Text(text, fontSize = 24.sp, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorUIPreview() {
    CalculatorUI()
}
