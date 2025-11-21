# Al-Fatih Quran App

A modern Android Quran application built with Clean Architecture, Jetpack Compose, and Hilt Dependency Injection.

## Architecture

This project follows **Clean Architecture** principles with a modular structure:

```
Al-Fatih/
├── app/                          # Main application module
├── core/
│   ├── design/                   # Design system (colors, typography, themes)
│   ├── ui/                       # Reusable UI components
│   └── common/                   # Common utilities
├── domain/                       # Business logic layer
│   ├── model/                    # Domain models
│   ├── repository/               # Repository interfaces
│   └── usecase/                  # Use cases
├── data/                         # Data layer
│   ├── local/                    # Room database
│   ├── repository/               # Repository implementations
│   └── di/                       # Data module DI
└── feature/
    └── quran/                    # Quran feature module
        ├── home/                 # Home screen (Surah list)
        ├── detail/               # Surah detail screen
        └── navigation/           # Navigation setup
```

## Tech Stack

### Core
- **Kotlin** - Programming language
- **Jetpack Compose** - Modern UI toolkit
- **Material 3** - Design system
- **Hilt Dagger** - Dependency injection
- **Coroutines & Flow** - Asynchronous programming

### Architecture Components
- **ViewModel** - UI state management
- **Navigation Compose** - Navigation framework
- **Room** - Local database
- **Lifecycle** - Lifecycle-aware components

### Additional Libraries
- **Retrofit** - Network requests (ready for API integration)
- **OkHttp** - HTTP client
- **Coil** - Image loading
- **Gson** - JSON serialization

## Design System

### Color Scheme

**Light Theme:**
- Primary: Teal Green (#0D7377) - Islamic theme
- Secondary: Gold (#D4AF37) - Accent color
- Background: Light (#F8F9FA)
- Surface: White (#FFFFFF)

**Dark Theme:**
- Primary: Bright Cyan (#14FFEC)
- Secondary: Bright Gold (#FFD700)
- Background: Dark (#121212)
- Surface: Dark Gray (#1E1E1E)

### Typography
- Display styles for headers
- Title styles for section headers
- Body styles for content
- Custom Arabic typography with larger line height

### Components
The app includes reusable components:

#### Buttons
- `PrimaryButton` - Primary action button
- `SecondaryButton` - Secondary action button
- `TextActionButton` - Text-only button

#### Cards
- `SurahCard` - Displays Surah information
- `AyahCard` - Displays Ayah with Arabic text and translation

#### States
- `LoadingIndicator` - Loading state
- `EmptyState` - Empty content state
- `ErrorState` - Error state with retry

#### Other
- `AlFatihTopAppBar` - Custom app bar
- `NumberBadge` - Circular number badge
- `Spacer` - Flexible spacing component

## Modules

### :app
Main application module that ties everything together.
- Contains `MainActivity` and `AlFatihApplication`
- Navigation setup
- Dependency injection configuration

### :core:design
Design system module containing:
- Color definitions (Light & Dark themes)
- Typography styles
- Material 3 theme
- Shapes and dimensions

### :core:ui
Reusable UI components:
- Buttons (Primary, Secondary, Text)
- Cards (Surah, Ayah)
- State components (Loading, Empty, Error)
- Top app bar
- Badges and spacers

### :core:common
Common utilities:
- `Result` sealed class for network/database operations
- Extension functions
- Constants

### :domain
Business logic layer:
- **Models**: `Surah`, `Ayah`, `RevelationType`
- **Repository Interfaces**: `QuranRepository`
- **Use Cases**:
  - `GetAllSurahsUseCase`
  - `GetSurahByNumberUseCase`
  - `GetAyahsBySurahUseCase`
  - `SearchAyahsUseCase`

### :data
Data layer implementation:
- **Local Database**: Room entities, DAOs, Database
- **Repository Implementation**: `QuranRepositoryImpl`
- **Data Seeding**: Sample data for testing
- **Mappers**: Entity to Domain model conversion

### :feature:quran
Quran feature module with MVVM pattern:

**Home Screen:**
- Lists all Surahs
- Beautiful card design
- Click to view details

**Surah Detail Screen:**
- Displays Surah header
- Lists all Ayahs with Arabic text
- Shows translation
- Scrollable content

## Features

### Implemented
- ✅ Modular Clean Architecture
- ✅ Jetpack Compose UI
- ✅ Hilt Dependency Injection
- ✅ Material 3 Design System
- ✅ Light & Dark Theme Support
- ✅ Room Database
- ✅ Surah List Screen
- ✅ Surah Detail Screen
- ✅ Sample Data Seeding
- ✅ Reusable Components
- ✅ MVVM Pattern
- ✅ Navigation Component

### Future Enhancements
- 🔄 Complete Quran data integration
- 🔄 Audio playback
- 🔄 Bookmarks and favorites
- 🔄 Search functionality
- 🔄 Multiple translations
- 🔄 Tafsir (commentary)
- 🔄 Prayer times
- 🔄 Qibla direction
- 🔄 Reading progress tracking
- 🔄 Night reading mode
- 🔄 Text size adjustment

## Building the Project

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle
4. Run the app

```bash
./gradlew clean build
```

## Project Structure Benefits

### Separation of Concerns
Each module has a single responsibility:
- **UI layer** (feature modules) - Presentation logic
- **Domain layer** - Business logic
- **Data layer** - Data management

### Testability
- Unit test domain logic independently
- Mock repositories for ViewModel tests
- UI tests with Compose testing framework

### Scalability
- Easy to add new features
- Reusable components across features
- Independent module development

### Maintainability
- Clear code organization
- Easy to navigate
- Consistent patterns

## Dependencies Management

All dependencies are managed in `gradle/libs.versions.toml` using Gradle Version Catalog:
- Centralized version management
- Type-safe dependency references
- Easy updates

## License

This project is open source and available under the MIT License.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Credits

Developed with ❤️ for the Muslim community.
