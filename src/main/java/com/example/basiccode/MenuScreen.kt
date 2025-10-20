package com.example.basiccode

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MenuScreen"){
        composable("MenuScreen"){
            MenuScreen(onNavigateToCalculator = { navController.navigate("CalculatorScreen")
            }, onNavigateToTextEditor = { navController.navigate("TextEditorScreen")})
        }
        composable("CalculatorScreen"){
            ScientificCalculatorScreen(onBackToMenu = { navController.navigate("MenuScreen")})
        }
        composable("TextEditorScreen"){
            TextEditorScreen(onBackToMenu = { navController.navigate("MenuScreen")})
        }
    }
}

@Composable
fun MenuScreen(onNavigateToCalculator: () -> Unit, onNavigateToTextEditor: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to SuperApp",
            fontSize = 6.em,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row {
            MenuButton(text = "Calculator", onClick = onNavigateToCalculator)
        }
        Row {
            MenuButton(text = "Text Editor", onClick = onNavigateToTextEditor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MenuPreview() {
    MenuScreen(onNavigateToCalculator = {}, onNavigateToTextEditor = {})
}



@Composable
fun MenuButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFF333333),
    textColor: Color = Color.White,
    height: Dp = 40.dp,
    width: Dp = 200.dp
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(3.dp)
            .size(width = width, height = height),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp
        )
    }
}