# PetProfile

## Components

@startuml

class Activity {
    name: String,
    repeateEvery: number of { DAY, WEEK, MONTH, YEAR }
    end: { Date, Times }
}

class Pet {
    name: String
    photo: URI
    castrationDate: Date?
    breed: String
    regularActivities: List<Activity>
}

Activity <|--- Pet

PetsRepository ---|> Pet

interface PetsRepository {
    getPets(): List<Pet>
}

interface PetProfileInteractor {
    repository: PetsRepository
}

PetProfileInteractor *--- PetsRepository

@enduml
