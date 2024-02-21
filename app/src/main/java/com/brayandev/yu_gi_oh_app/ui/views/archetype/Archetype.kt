package com.brayandev.yu_gi_oh_app.ui.views.archetype

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.brayandev.yu_gi_oh_app.R
import com.brayandev.yu_gi_oh_app.data.repository.models.ArchetypeModel
import com.brayandev.yu_gi_oh_app.ui.navigation.Routes

@Composable
fun ArchetypeScreen(archetypeViewModel: ArchetypeViewModel, navController: NavHostController) {
    val loadingState by archetypeViewModel.loading.observeAsState(false)
    val listState by archetypeViewModel.list.observeAsState(emptyList())

    if (loadingState) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }

    if (listState.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            GlobalTitle("Archetypes", Icons.Rounded.Category)
            Box(modifier = Modifier.fillMaxSize()) {
                ArchetypeList(
                    listState,
                    navController,
                )
            }
        }
    }
}

@Composable()
fun GlobalTitle(title: String, imageVector: ImageVector) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            modifier = Modifier.padding(horizontal = 16.dp),
            imageVector = imageVector,
            contentDescription = "Icon_title",
        )
        Text(
            text = title,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchetypeList(
    list: List<ArchetypeModel>,
    navController: NavHostController,
) {
    var filterText by remember { mutableStateOf(TextFieldValue()) }

    val filteredItems = remember(filterText) {
        list.filter {
            it.archetypeName.contains(filterText.text, ignoreCase = true)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = filterText,
            onValueChange = { filterText = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.archetype_ic),
                    contentDescription = "Icon_store_dialog",
                    modifier = Modifier.size(18.dp),
                )
            },
            label = { Text(text = "Nombre de la Arqueotipo") },
            placeholder = {
                Text(text = "Añadir nombre")
            },
            singleLine = true,
        )
        LazyColumn {
            items(filteredItems, key = { it.archetypeName }) { item ->
                ItemArchetype(item, navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemArchetype(
    archetype: ArchetypeModel,
    navController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = {
            navController.navigate(Routes.DetailsArchetype.createRoute(archetype.archetypeName))
        },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
        ) {
            Icon(
                modifier = Modifier.padding(horizontal = 16.dp),
                painter = painterResource(id = R.drawable.archetype_ic),
                contentDescription = "Icon_store",
                tint = Color.Black,
            )
            Text(
                text = archetype.archetypeName,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                color = Color.Black,
            )
        }
    }
}
