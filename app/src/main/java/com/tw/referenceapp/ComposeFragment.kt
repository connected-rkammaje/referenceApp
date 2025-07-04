package com.tw.referenceapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tw.common.ui.theme.ReferenceAppTheme
import com.tw.openlibrarybooks.ui.BookSearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComposeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ReferenceAppTheme {
                    val navController = rememberNavController()

                    // Compose Navigation within this fragment
                    NavHost(
                        navController = navController,
                        startDestination = "book_search"
                    ) {
                        composable("book_search") {
                            BookSearchScreen()
                        }
                        // Additional compose destinations can be added here
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = ComposeFragment()
    }
}
