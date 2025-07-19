package com.example.dogsapp.ui.screens.dog_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.Function
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dogsapp.R
import com.example.dogsapp.ui.theme.DogsAppTheme
import com.example.dogsapp.utils.TestsUtil
import com.example.dogsapp.utils.toAgeAlmost
import com.example.domain.models.Dog

@Composable
fun DogsListScreen(
    state: () -> DogsListData,
    goToDetail: Function<Dog?, Unit>,
    retry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clearAndSetSemantics {
                contentDescription = TestsUtil.homeScreen
            }
    ) {
        TitleAppBar()

        Box(modifier = Modifier.fillMaxSize()) {
            ErrorScreen(state().isErrorVisible, retry)
            LoadingState(state().isLoadingVisible)
            EmptyState(state().isEmptyVisible)
            SuccessScreen(state().isSuccessVisible, state().dogs, goToDetail)
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.dogs_list_title)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
            }
        }
    )
}

@Composable
fun ErrorScreen(isVisible: Boolean, retry: () -> Unit = {}) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxSize()
                    .clickable {
                        retry()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.msg_error_with_retry),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun LoadingState(isVisible: Boolean) {
    val loadingText = stringResource(R.string.loading)
    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                Modifier
                    .size(48.dp)
                    .clearAndSetSemantics {
                        contentDescription = loadingText
                    }
            )
        }
    }
}

@Composable
fun EmptyState(isVisible: Boolean) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(text = stringResource(id = R.string.empty_text))
        }
    }
}

@Composable
fun SuccessScreen(
    isVisible: Boolean,
    dogs: List<Dog>,
    goToDetail: Function<Dog?, Unit>,
) {
    val scaffoldDescription = stringResource(R.string.dogs_list_success_description)

    AnimatedVisibility(
        visibleState = MutableTransitionState(isVisible),
        enter = expandHorizontally(),
        exit = shrinkHorizontally()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .clearAndSetSemantics {
                    contentDescription = scaffoldDescription
                    onClick {
                        //goToDetail.apply()
                        true
                    }
                },
            verticalArrangement = Arrangement.Top
        ) {
            dogs.forEach { item ->
                CharacterCard(goToDetail, item)
            }
        }
    }
}

@Composable
fun CharacterCard(
    goToDetail: Function<Dog?, Unit>,
    item: Dog
) {
    Row(
        modifier = Modifier
            .clickable{
                goToDetail.apply(item)
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.image)
                .build(),
            contentDescription = stringResource(R.string.dogs_list_item_description),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(width = 140.dp, height = 200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topEnd = 16.dp,
                    bottomEnd = 16.dp
                )
            ),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.age.toAgeAlmost(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


private val sampleDogs = listOf(
    Dog(
        name = "Chief",
        description = "Black (formerly) White with black spots, he don't trust anyone",
        age = 2,
        image = ""
    ),
    Dog(
        name = "Rex",
        description = "Golden retriever, very friendly and loves to play fetch",
        age = 3,
        image = ""
    )
)

@Preview(name = "Error Screen - Visible", showBackground = true)
@Composable
fun ErrorScreenVisiblePreview() {
    DogsAppTheme {
        ErrorScreen(isVisible = true, retry = { })
    }
}

@Preview(name = "Loading State - Visible", showBackground = true)
@Composable
fun LoadingStateVisiblePreview() {
    DogsAppTheme {
        LoadingState(isVisible = true)
    }
}

@Preview(name = "Empty State - Visible", showBackground = true)
@Composable
fun EmptyStateVisiblePreview() {
    DogsAppTheme {
        EmptyState(isVisible = true)
    }
}

@Preview(name = "Success Screen - With Dogs", showBackground = true)
@Composable
fun SuccessScreenWithDogsPreview() {
    DogsAppTheme {
        SuccessScreen(
            isVisible = true,
            dogs = sampleDogs.take(3),
            goToDetail = { }
        )
    }
}

@Preview(name = "Title App Bar", showBackground = true)
@Composable
fun TitleAppBarPreview() {
    DogsAppTheme {
        TitleAppBar()
    }
}

