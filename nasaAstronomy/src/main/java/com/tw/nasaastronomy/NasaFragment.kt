package com.tw.nasaastronomy

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
import com.tw.common.Spacing
import com.tw.common.ui.theme.ReferenceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NasaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ReferenceAppTheme {
                    nasaMainScreen(
                        onNavigateToDetail = {
                            navigateToNasaDetail()
                        }
                    )
                }
            }
        }
    }

    private fun navigateToNasaDetail() {
        // Use the same container that this fragment is currently in
        val containerId = (view?.parent as? ViewGroup)?.id ?: android.R.id.content
        parentFragmentManager.beginTransaction()
            .replace(containerId, NasaAstronomyDetailFragment.newInstance())
            .addToBackStack("NasaAstronomyDetail")
            .commit()
    }

    companion object {
        fun newInstance() = NasaFragment()
    }
}

@Composable
private fun nasaMainScreen(
    onNavigateToDetail: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "NASA Astronomy",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.large * 2))

        Text(
            text = "This is the main NASA screen using fragment navigation.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(Spacing.large * 2))

        Button(
            onClick = onNavigateToDetail,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Navigate to NASA Detail Fragment",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        Text(
            text = "This demonstrates fragment navigation to another fragment.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
