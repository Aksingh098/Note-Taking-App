# Note Taking App

A simple offline note-taking app for Android, built with **Kotlin** and **Jetpack Compose**. Write a note with a title and description, save it to a local Room database, and tap any note to read it in full.

## Features

- Add a note with a title and description (Save is disabled until both are filled)
- Notes are stored locally with **Room**, so they persist across app restarts
- Live list of saved notes that updates automatically via `Flow`
- Tap a note to open a detail screen with the full text
- Material 3 UI with dynamic color on Android 12+ and light/dark theme support
- Type-safe navigation using Kotlin Serialization

## Tech Stack

| Area | Library / Tool |
| --- | --- |
| Language | Kotlin |
| UI | Jetpack Compose, Material 3, Material Icons Extended |
| Navigation | Navigation Compose (type-safe routes with `kotlinx-serialization`) |
| Local storage | Room (with KSP) |
| Async | Kotlin Coroutines, `Flow`, `StateFlow` |
| Architecture | MVVM with Repository pattern, manual dependency injection |
| Build | Gradle (Kotlin DSL), version catalog (`libs.versions.toml`) |

**SDK levels:** `minSdk 24` · `targetSdk 37` · `compileSdk 37`

## Architecture

The app follows a simple layered structure:

```
UI (Compose screens)
   ↓ observes StateFlow / calls functions
ViewModel (NotesViewModel)
   ↓
Repository (NotesRepository → OfflineNotesRepository)
   ↓
Data source (Room: NoteDao → NotesDataBase)
```

- **`Note`** – Room entity (`id`, `title`, `content`)
- **`NoteDao`** – insert a note and observe all notes as a `Flow<List<Note>>`
- **`NotesDataBase`** – singleton Room database
- **`NotesRepository` / `OfflineNotesRepository`** – abstraction over the DAO
- **`AppContainer` / `DefaultAppContainer`** – manual DI; creates the repository lazily
- **`NoteMakingApplication`** – holds the container for the whole app
- **`NotesViewModel`** – exposes `notes` as a `StateFlow` and an `addNote()` function; created through a `ViewModelProvider.Factory` that reads the repository from the application container
- **`NavGraph` / `NavRoutes`** – navigation host and serializable route definitions

## Project Structure

```
app/src/main/java/com/example/notetakingapp/
├── MainActivity.kt
├── NoteMakingApplication.kt
├── data/
│   ├── local/
│   │   ├── AppContainer.kt
│   │   ├── Note.kt
│   │   ├── NoteDao.kt
│   │   └── NotesDataBase.kt
│   └── repository/
│       ├── NotesRepository.kt
│       └── OfflineNotesRepository.kt
└── ui/
    ├── navigation/
    │   ├── NavGraph.kt
    │   └── NavRoutes.kt
    ├── screens/
    │   ├── notesScreen/
    │   │   ├── NotesScreen.kt
    │   │   └── NotesViewModel.kt
    │   └── notesDetailScreen/
    │       └── NotesDetailScreen.kt
    └── theme/
        ├── Color.kt
        ├── Theme.kt
        └── Type.kt
```

## Getting Started

### Prerequisites

- A recent version of **Android Studio** with support for the Android Gradle Plugin version used in this project
- JDK (the project's Gradle daemon is configured through a toolchain and will be resolved automatically by the Foojay resolver)
- An emulator or physical device running **Android 7.0 (API 24)** or higher

### Run the app

```bash
git clone https://github.com/aksingh098/note-taking-app.git
cd note-taking-app
```

1. Open the project in Android Studio.
2. Let Gradle sync finish.
3. Select a device or emulator and click **Run ▶**.

Or build from the command line:

```bash
./gradlew assembleDebug
```

## How It Works

1. On the home screen, enter a **title** and **description**, then tap **Save**.
2. `NotesViewModel.addNote()` inserts the note into Room on `Dispatchers.IO`.
3. The DAO's `Flow` emits the updated list, which the ViewModel exposes as a `StateFlow` and the UI collects to re-render "Your Notes".
4. Tapping a note navigates to `NotesDetailScreen`, passing the title and content as type-safe route arguments.

## Roadmap

- [ ] Edit and delete notes
- [ ] Pass the note `id` to the detail screen and load it from the database
- [ ] Search and sort notes
- [ ] Timestamps (created / updated)
- [ ] Clear the input fields after saving
- [ ] Unit and UI tests for the ViewModel, DAO and screens
- [ ] Replace manual DI with Hilt or Koin

## Author

**Aditya Kumar Singh** — [@aksingh098](https://github.com/aksingh098)
