package com.castlelecs.petprofile.android.screens.petlist

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.castlelecs.petprofile.android.R
import com.castlelecs.petprofile.android.views.NavigationViewModel
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repository.PetsRepository
import java.util.UUID

@Composable
fun PetsListScreen(viewModel: PetsListViewModel) {
    val scrollState = remember { ScrollState(0) }
    val pets = viewModel.pets

    Column(
        modifier = Modifier
            .verticalScroll(scrollState),
    ) {
        if (pets.isEmpty()) {
            Text(
                text = stringResource(id = R.string.empty_pets_list)
            )
        }

        pets.forEach { pet ->
            // TODO: Move to a separate view
            ListItem(
                headlineContent = {
                    Text(pet.name)
                },
            )

            Divider()
        }
    }
}

@Preview
@Composable
private fun PetsListScreenPreview() {
    val pets = mutableMapOf<ID, Pet>()
    (0..20).forEach {
        val id = UUID.randomUUID().toString()

        pets[id] = Pet(id = id, name = it.toString())
    }

    PetListScreenAssembler.Assemble(
        NavigationViewModel(PetsInteractorImpl(PetsRepository(pets)))
    )
}
