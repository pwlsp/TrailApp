@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.trailapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api // TopAppBar jest experimental z jakiegoś powodu
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trailapp.ui.theme.TrailAppTheme

enum class Destination(
    val route: String,
    val label: String,
//    val icon: ImageVector,
) {
    // DODAĆ IKONY!
    RUNNING("running", "Running"),
    CYCLING("cycling", "Cycling"),
}

// TODO: Blah blah
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
                    MainView()
                }
            }
        }
    }
}

@Composable
fun MainView(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.RUNNING
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

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
        Column(modifier = Modifier.padding(innerPadding)) {
            PrimaryTabRow(
                selectedTabIndex = selectedDestination,
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    Tab(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        text = {
                            Text(
                                text = destination.label,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }
            }
            AppNavHost(navController, startDestination) // To będzie wybierało co wyświetlać
        }
    }
}

fun getDummyTrails(text: String = "Sample"): List<Trail> {
    return List(10) { index ->
        Trail(
            id = index,
            title = "$text Trail ${index + 1}",
            imageRes = android.R.drawable.ic_menu_compass, // Standard Android icon for quick testing
            distanceKm = 5.0 + index,
            elevationGainM = 150.0 + (index * 50),
            difficulty = if (index % 2 == 0) "Medium" else "Hard",
            durationMinutes = 120 + (index * 15),
            routeId = "details/$index"
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
) {
    val sampleRunningTrails = getDummyTrails(text = "Running")
    val sampleCyclingTrails = getDummyTrails(text = "Cycling")

    NavHost(
        navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.RUNNING -> TrailsGrid(sampleRunningTrails)
                    Destination.CYCLING -> TrailsGrid(sampleCyclingTrails)
                }
            }
        }
    }
}

@Composable
fun TrailsGrid(
    items: List<Trail>, // Pass your data model here
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
    ) {
        items(items) { item ->
            TrailCard(item)
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    TrailAppTheme {
        MainView()
    }
}


@Composable
fun TrailCard(trail: Trail, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(16.dp)) {
        Text(
            text = trail.title,
        )
    }
}