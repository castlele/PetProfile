# PetProfile

## How to work with the project

This project uses [ktfmt](https://github.com/cortinico/ktfmt-gradle) plugin. For code style official kotlin language style is used.

For checking code formatting the following task can be used:

```bash
./gradle ktfmtCheck
```

For formatting the following task can be used:

```bash
./gradle ktfmtFormat
```

## Dictionary

1. Pet object:

    - photo
    - name
    - castrations + it's date
    - breed
    - gender
    - list of activities

2. Activity object:

    - name
    - description
    - time of the notification
    - date and time when activity starts
    - frequency: how often the activity occures (for example every two weeks)
    - date and time when activity ends

## UX

**Main Screen:**

1. User should observe his pets
2. User should know if the list of pets is empty
3. User should have the ability to add pet if the list of pets is empty
4. Every row in a list of pets should preview the most useful information about pet to distinguish it from others. For example: name, breed, gender, photo and etc.
5. User should have the ability to go to the full pet's profile from the list of pets
6. User should have the ability to go to the screen (or view) with pet's profile editing from the list of pets

**Pet's Profile Screen:**

1. Generally this screen should have three states (But technically it can be three different screens (or views)):

    - Create Profile: in this state the user should fill pet's information and save it in the permanent storage
    - Edit Profile: in this state the user can edit current pet's information and update information in the permanent storage
    - View Profile: in this state the user can only view the information about the pet and add new activities for that pet. All new activities are saved in the permanent storage and opens the acitivity editor screen (or view)

2. Save button available for tapping only if mandatory fields are filled in
3. User should be protected from from losing pet's data on errors and other accidents:

    - if the user filled some information about pet by moving from the profile screen info should be saved as draft or the user should be alerted about losing his data. This rule applies to editing and creating
    - *Optional* if application crashed pet's info should be saved as a draft

**Notifications About Activities:**

1. The user should get notifications from the app about active activites of his pets. For example, on the following date one of the user's pets have grooming appointment. On the following time the app should send notification about that
2. Notification should contain information about activity (name, titile) and let the user know to which pet this message belongs

## Components

```plantuml

class Activity {
    name: String,
    repeatEvery: number of { DAY, WEEK, MONTH, YEAR }
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

```
