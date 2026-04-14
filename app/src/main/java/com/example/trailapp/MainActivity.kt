@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.trailapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api // TopAppBar jest experimental z jakiegoś powodu
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trailapp.ui.theme.TrailAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Przy nadpisywaniu najpierw odpalamy bazowy onCreate
        enableEdgeToEdge()
        setContent {
            TrailAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    /*  Ta funkcja podawana po Scaffold to
                        content: @Composable ((PaddingValues) -> Unit)
                        czyli innerPadding to parametr typu PaddingValues,
                        który obliczany jest podczas wykonywania Scaffold()
                        i potem podawany do funkcji strzałkowej. */
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = "Hello asd",
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TrailAppTheme {
        MainScreen()
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