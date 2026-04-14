package com.example.trailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trailapp.ui.theme.TrailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Przy nadpisywaniu najpierw odpalamy bazowy onCreate
        enableEdgeToEdge()
        setContent {
            TrailAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    /*  Ta funkcja podawana po Scaffold to
                        content: @Composable ((PaddingValues) -> Unit)
                        czyli innerPadding to parametr typu PaddingValues,
                        który obliczany jest podczas wykonywania Scaffold()
                        i potem podawany do funkcji strzałkowej. */
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
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
    TrailAppTheme {
        Greeting("Android")
    }
}