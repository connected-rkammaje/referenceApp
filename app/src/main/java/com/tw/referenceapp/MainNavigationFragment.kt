package com.tw.referenceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tw.common.Spacing
import com.tw.common.ui.theme.ReferenceAppTheme
import com.tw.nasaastronomy.NasaFragment
import com.tw.openlibrarybooks.ui.BookSearchScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainNavigationFragment : Fragment() {

    @Inject
    lateinit var nasaFragment: NasaFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ReferenceAppTheme {
                    val navController = rememberNavController()

                    // Compose Navigation for the entire app
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            mainNavigationScreen(
                                navController = navController,
                                onNasaAstronomyClick = {
                                    navigateToNasaAstronomy()
                                }
                            )
                        }
                        composable("book_search") {
                            BookSearchScreen()
                        }
                    }
                }
            }
        }
    }

    private fun navigateToNasaAstronomy() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nasaFragment)
            .addToBackStack("NasaAstronomy")
            .commit()
    }

    companion object {
        fun newInstance() = MainNavigationFragment()
    }
}

@Composable
private fun mainNavigationScreen(
    navController: NavHostController,
    onNasaAstronomyClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Reference App",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.large * 2))

        Text(
            text = "Choose a feature to explore:",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.large * 2))

        Button(
            onClick = { navController.navigate("book_search") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Open Library Book Search",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        Button(
            onClick = onNasaAstronomyClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "NASA Astronomy",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
