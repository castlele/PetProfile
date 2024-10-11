package com.castlelecs.petprofile.android.profile

import app.cash.turbine.test
import com.castlelecs.petprofile.android.mocks.MockDataStore
import com.castlelecs.petprofile.android.profile.viewmodel.PetsProfileViewModel
import com.castlelecs.petprofile.interactors.PetsInteractorImpl
import com.castlelecs.petprofile.models.Breed
import com.castlelecs.petprofile.models.Gender
import com.castlelecs.petprofile.models.ID
import com.castlelecs.petprofile.models.Pet
import com.castlelecs.petprofile.repositories.PetsRepositoryImpl
import com.castlelecs.utils.datastore.DataStore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate

class PetsProfileViewModelTests {

    @Test
    fun assertDefaultState() = runTest {
        val expectedState =
            PetsProfileViewModel.State(
                name = "",
                lastName = "",
                gender = null,
                dateOfBirth = null,
                breed = Breed.NONE,
                isCastrated = false,
                activities = emptySet(),
            )
        val sut = createViewModel()

        sut.stateFlow.test { assertEquals(expectedState, awaitItem()) }
    }

    @Test
    fun assertChangingName() = runTest {
        val name = PET_NAME
        val sut = createViewModel()

        sut.changeFirstName(name)

        sut.stateFlow.test { assertEquals(name, awaitItem().name) }
    }

    @Test
    fun assertChangingLastName() = runTest {
        val lastName = PET_LAST_NAME
        val sut = createViewModel()

        sut.changeLastName(lastName)

        sut.stateFlow.test { assertEquals(lastName, awaitItem().lastName) }
    }

    @Test
    fun assertTogglingCastration() = runTest {
        val sut = createViewModel()

        sut.toggleCastration()

        sut.stateFlow.test { assertEquals(true, awaitItem().isCastrated) }
    }

    @Test
    fun assertChangingGender() = runTest {
        val updatedGender = PET_GENDER
        val sut = createViewModel()

        sut.changeGender(updatedGender)

        sut.stateFlow.test { assertEquals(updatedGender, awaitItem().gender) }
    }

    @Test
    fun assertPickingDateOfBirth() = runTest {
        val dateOfBirth = PET_DATE_OF_BIRTH
        val sut = createViewModel()

        sut.pickDateOfBirth(dateOfBirth)

        sut.stateFlow.test {
            assertEquals(dateOfBirth, awaitItem().dateOfBirth)
        }
    }

    @Test
    fun assertPickingBreed() = runTest {
        val breed = PET_BREED
        val sut = createViewModel()

        sut.pickBreed(breed)

        sut.stateFlow.test { assertEquals(breed, awaitItem().breed) }
    }

    @Test
    fun discardingChangesSetsDefaultState() = runTest {
        val expectedState = PetsProfileViewModel.State()
        val sut = createViewModel().also(::setAllFields)

        sut.discardCurrentState()

        sut.stateFlow.test { assertEquals(expectedState, awaitItem()) }
    }

    @Test
    fun savingCreatesPetInDataStore() = runTest {
        val expectedPet =
            Pet(
                id = PET_ID,
                name = PET_NAME,
                lastName = PET_LAST_NAME,
                gender = PET_GENDER,
                dateOfBirth = LocalDate.parse(PET_DATE_OF_BIRTH),
                breed = PET_BREED,
                isCastrated = true,
                activities = emptySet(),
            )
        val petsDataStore = MockDataStore<ID, Pet>()
        val sut = createViewModel(petsDataStore).also(::setAllFields)

        sut.save()

        sut.stateFlow.test {
            awaitItem()
            val pets = petsDataStore.getAll()

            assertEquals(1, pets.count())
            assertPetsWithoutId(pets.first(), expectedPet)
        }
    }

    private fun createViewModel(
        petsDataStore: DataStore<ID, Pet> = MockDataStore()
    ): PetsProfileViewModel {
        return PetsProfileViewModel(
            petsInteractor =
                PetsInteractorImpl(
                    petsRepository =
                        PetsRepositoryImpl(
                            petsDataStore = petsDataStore,
                            activitiesDataStore = MockDataStore(),
                        )
                )
        )
    }

    private fun setAllFields(vm: PetsProfileViewModel) {
        vm.changeFirstName(PET_NAME)
        vm.changeLastName(PET_LAST_NAME)
        vm.toggleCastration()
        vm.changeGender(PET_GENDER)
        vm.pickDateOfBirth(PET_DATE_OF_BIRTH)
        vm.pickBreed(PET_BREED)
    }

    private fun assertPetsWithoutId(expected: Pet, other: Pet) {
        assertEquals(expected.name, other.name)
        assertEquals(expected.lastName, other.lastName)
        assertEquals(expected.gender, other.gender)
        assertEquals(expected.dateOfBirth, other.dateOfBirth)
        assertEquals(expected.breed, other.breed)
        assertEquals(expected.isCastrated, other.isCastrated)
        assertEquals(expected.activities, other.activities)
    }

    companion object {
        private const val PET_ID = "pet-id"
        private const val PET_NAME = "Javie"
        private const val PET_LAST_NAME = "Esposito"
        private val PET_GENDER = Gender.MALE
        private const val PET_DATE_OF_BIRTH = "2023-07-07"
        private val PET_BREED = Breed.RAGDOLL
    }
}
