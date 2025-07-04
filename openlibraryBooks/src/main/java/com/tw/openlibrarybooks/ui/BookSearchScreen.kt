package com.tw.openlibrarybooks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.tw.networking.Book
import com.tw.openlibrarybooks.R
import com.tw.common.Spacing

/**
 * External composable that handles ViewModel injection and state management.
 * This is the main entry point for the book search functionality.
 */
@Composable
fun BookSearchScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BookSearchViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    BookSearchScreenInternal(
        viewState = viewState,
        onSearchQueryChange = viewModel::updateSearchQuery,
        onSearchBooks = viewModel::searchBooks,
        onClearError = viewModel::clearError,
        modifier = modifier
    )
}

/**
 * Internal composable for the book search screen UI presentation.
 * This handles the UI layout and presentation logic.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookSearchScreenInternal(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit,
    onClearError: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.large)
    ) {
        BookSearchBar(viewState, onSearchQueryChange, onSearchBooks)

        Spacer(modifier = Modifier.Companion.height(Spacing.large))
        viewState.errorMessage?.let { error ->
            BookSearchError(error, onClearError)
        }
        BooksList(viewState)
    }
}

@Composable
private fun BookSearchBar(
    viewState: BookSearchViewState,
    onSearchQueryChange: (String) -> Unit,
    onSearchBooks: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Search Bar
        OutlinedTextField(
            value = viewState.searchQuery,
            onValueChange = onSearchQueryChange,
            label = { Text(stringResource(R.string.search_for_books)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search)
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchBooks()
                }
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.Companion.height(Spacing.large))

        // Search Button
        Button(
            onClick = {
                onSearchBooks()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = viewState.searchQuery.isNotBlank() && !viewState.isLoading
        ) {
            if (viewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.Companion.size(Spacing.large),
                    strokeWidth = Spacing.progressIndicatorStroke
                )
                Spacer(modifier = Modifier.Companion.width(Spacing.small))
            }
            Text(if (viewState.isLoading) stringResource(R.string.searching) else stringResource(R.string.search_books))
        }
    }
}

@Composable
private fun BooksList(
    viewState: BookSearchViewState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.small)
    ) {
        items(viewState.books) { book ->
            BookItem(book = book)
        }
    }
}

@Composable
private fun BookSearchError(
    error: String,
    onClearError: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Row(
                modifier = Modifier.Companion.padding(Spacing.large),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    modifier = Modifier.weight(1f)
                )
                TextButton(
                    onClick = onClearError
                ) {
                    Text(stringResource(R.string.dismiss))
                }
            }
        }
        Spacer(modifier = Modifier.Companion.height(Spacing.large))
    }
}

/**
 * Composable for displaying a single book item.
 */
@Composable
private fun BookItem(
    book: Book,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Spacing.cardElevation)
    ) {
        Row(
            modifier = Modifier.Companion.padding(Spacing.large),
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            // Book Cover
            AsyncImage(
                model = book.coverUrl,
                contentDescription = stringResource(R.string.book_cover_description, book.title),
                modifier = Modifier
                    .size(Spacing.bookCoverWidth, Spacing.bookCoverHeight)
                    .clip(RoundedCornerShape(Spacing.cornerRadius)),
                contentScale = ContentScale.Crop
            )

            // Book Details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                book.author?.let { author ->
                    Spacer(modifier = Modifier.Companion.height(Spacing.extraSmall))
                    Text(
                        text = stringResource(R.string.author_prefix, author),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                book.publishYear?.let { year ->
                    Spacer(modifier = Modifier.Companion.height(Spacing.extraSmall))
                    Text(
                        text = stringResource(R.string.published_prefix, year),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
