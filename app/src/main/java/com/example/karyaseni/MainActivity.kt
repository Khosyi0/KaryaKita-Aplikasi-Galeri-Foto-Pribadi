package com.example.karyaseni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

// Model data untuk foto
data class ImageModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val date: String
)

// Repository untuk menyimpan data dummy
object ImageRepository {
    fun getDummyImages(): List<ImageModel> {
        return listOf(
            ImageModel(
                id = "1",
                title = "Unsupervised Learning",
                imageUrl = "https://i.imgur.com/8Y8arY8.png",
                description = "Agglomerative Clustering Scatter Plot with Centoroids",
                date = "23 Maret 2025"
            ),
            ImageModel(
                id = "2",
                title = "Event",
                imageUrl = "https://i.ibb.co.com/pjyTYhR2/PXL-20230910-041754167-2.jpg",
                description = "Event Wakuwaku (wibu x wota)",
                date = "22 April 2025"
            ),
            ImageModel(
                id = "3",
                title = "Hikawa Sister",
                imageUrl = "https://static.wikia.nocookie.net/bandori/images/f/f2/Boppin%27_Twinsies%E2%99%AA.png/revision/latest?cb=20231030124252",
                description = "Card Name: Boppin' Twinsies",
                date = "21 April 2025"
            ),
            ImageModel(
                id = "4",
                title = "TC ITS",
                imageUrl = "https://i.imgur.com/e32oJzW.jpg",
                description = "Gedung Teknik Informatika ITS",
                date = "20 April 2025"
            ),
            ImageModel(
                id = "5",
                title = "Home Linux",
                imageUrl = "https://i.imgur.com/vXUDjvl.png",
                description = "Tampilan Home Linux Akmal Sulthon",
                date = "19 April 2025"
            ),
            ImageModel(
                id = "6",
                title = "KOCHENG",
                imageUrl = "https://i.imgur.com/NaOxukE.jpg",
                description = "Kucing duduk seenaknya di atas laptop",
                date = "18 April 2025"
            ),
            ImageModel(
                id = "7",
                title = "Istri Akmal",
                imageUrl = "https://pbs.twimg.com/media/GoWQOwgXMAAhWaY?format=jpg&name=large",
                description = "Oshi akmal sulthon yang berasal dari agensi jkt48",
                date = "17 April 2025"
            ),
            ImageModel(
                id = "8",
                title = "Istri Khosyi'",
                imageUrl = "https://i.pinimg.com/736x/0c/80/85/0c8085a3557ea00190f70506c874379c.jpg",
                description = "Oshi khosyi yang berasal dari agensi bandori",
                date = "16 April 2025"
            ),
            ImageModel(
                id = "9",
                title = "Sawah Terasering",
                imageUrl = "https://picsum.photos/id/18/800/800",
                description = "Sawah terasering yang menakjubkan",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "10",
                title = "Langit Bintang",
                imageUrl = "https://picsum.photos/id/19/800/800",
                description = "Langit malam yang dipenuhi bintang",
                date = "14 April 2025"
            ),
            ImageModel(
                id = "11",
                title = "Sawah Terasering",
                imageUrl = "https://picsum.photos/id/18/800/800",
                description = "Sawah terasering yang menakjubkan",
                date = "15 April 2025"
            ),
            ImageModel(
                id = "12",
                title = "Langit Bintang",
                imageUrl = "https://picsum.photos/id/19/800/800",
                description = "Langit malam yang dipenuhi bintang",
                date = "14 April 2025"
            )
        )
    }
}

private val DarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    secondary = Color(0xFF81D4FA),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF1976D2),
    secondary = Color(0xFF0288D1),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

// Theme configuration
@Composable
fun MyGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

// Navigation for the app
@Composable
fun MyAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "gallery"
    ) {
        composable("gallery") {
            GalleryScreen(
                onImageClick = { imageId ->
                    navController.navigate("detail/$imageId")
                },
                onUploadClick = {
                    navController.navigate("upload")
                }
            )
        }

        composable(
            route = "detail/{imageId}",
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId") ?: ""
            DetailScreen(
                imageId = imageId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("upload") {
            UploadScreen(
                onBack = {
                    navController.popBackStack()
                },
                onUploadSuccess = {
                    navController.navigate("gallery") {
                        popUpTo("gallery") { inclusive = true }
                    }
                }
            )
        }
    }
}

// Gallery Screen - Grid view of images
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    onImageClick: (String) -> Unit,
    onUploadClick: () -> Unit
) {
    val images = ImageRepository.getDummyImages()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 8.dp)
                        )
                        Text("KaryaKita")
                    }
                },
                actions = {
                    IconButton(onClick = onUploadClick) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Upload Image"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = paddingValues.calculateTopPadding() + 8.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(images) { image ->
                ImageItem(
                    image = image,
                    onImageClick = onImageClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageItem(
    image: ImageModel,
    onImageClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onImageClick(image.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            AsyncImage(
                model = image.imageUrl,
                contentDescription = image.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
            ) {
                Text(
                    text = image.title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

// Detail Screen - Show image details
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    imageId: String,
    onBackClick: () -> Unit
) {
    val image = ImageRepository.getDummyImages().find { it.id == imageId }

    if (image == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Image not found")
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(image.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = image.imageUrl,
                    contentDescription = image.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = image.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Taken on: ${image.date}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = image.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

// Upload Screen - Simulate image upload
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadScreen(
    onBack: () -> Unit,
    onUploadSuccess: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isUploading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upload Image") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Placeholder for image preview
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Select Image",
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // "Select Image" Button
            Button(
                onClick = { /* Simulation only */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Select Image")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Title field
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description field
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            val coroutineScope = rememberCoroutineScope()

            // Upload Button
            Button(

                onClick = {
                    // Simulate upload process
                    isUploading = true

                    // Simulate network delay
                    coroutineScope.launch {
                        delay(2000)
                        isUploading = false
                        onUploadSuccess()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() && !isUploading
            ) {
                if (isUploading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Upload")
                }
            }
        }
    }
}