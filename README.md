# Android Finance Manager App

A comprehensive Android application for managing personal finances, built with Kotlin and Jetpack Compose. This application allows users to manage multiple accounts, track transactions, and manage stock portfolios.

## Features

- **Account Management**: Create, edit, and view multiple financial accounts
- **Transaction Tracking**: Record and view transaction history
- **Stock Portfolio**: Manage stock holdings and perform stock exchanges
- **Fund Transfers**: Transfer funds between accounts with history tracking
- **Local Database**: Persistent storage using Room database
- **Material Design 3**: Modern UI built with Jetpack Compose

## Tech Stack

- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose
- **Database**: Room (with Kotlin Symbol Processing - KSP)
- **Build System**: Gradle (Kotlin DSL)
- **Minimum SDK**: API 29
- **Target SDK**: API 34
- **Compile SDK**: API 34

## Project Structure

```
Android-Finance-Manager-App/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/accountmanagertrialzone/
│   │   │   │   ├── MainActivity.kt              # Main entry point
│   │   │   │   ├── AccountEditorActivity.kt     # Account creation/editing
│   │   │   │   ├── AccountViewerActivity.kt     # View account details
│   │   │   │   ├── AccountViewerAdapter.kt      # Adapter for account list
│   │   │   │   ├── AccountScreen.kt             # Compose UI for accounts
│   │   │   │   ├── StockEditorActivity.kt       # Stock management
│   │   │   │   ├── StockExchangeActivity.kt     # Stock trading
│   │   │   │   ├── TransactionHistoryActivity.kt # Transaction history
│   │   │   │   ├── TransferActivity.kt          # Fund transfers
│   │   │   │   ├── TransferHistoryActivity.kt   # Transfer history
│   │   │   │   ├── room/                        # Room database entities
│   │   │   │   ├── stocks/                      # Stock-related logic
│   │   │   │   ├── screen/                      # Compose screen components
│   │   │   │   └── ui/                          # UI utilities
│   │   │   └── res/                             # Resources (layouts, strings, etc.)
│   │   ├── test/                                # Unit tests
│   │   └── androidTest/                         # Instrumented tests
│   └── build.gradle.kts                         # App-level build configuration
├── build.gradle.kts                             # Project-level build configuration
├── settings.gradle.kts                          # Gradle settings
└── gradle/                                      # Gradle wrapper files
```

## Key Activities

### MainActivity
The main entry point of the application, displaying the dashboard and primary navigation.

### AccountEditorActivity
Allows users to create new accounts or edit existing ones. Parent activity for account management.

### AccountViewerActivity
Displays detailed information about selected accounts with list of all accounts.

### StockExchangeActivity
Handles stock portfolio management and trading operations.

### TransferActivity
Facilitates fund transfers between accounts.

### Transaction & Transfer History
Activities to view historical records of transactions and transfers.

## Dependencies

### Room Database
- `androidx.room:room-runtime:2.5.2`
- `androidx.room:room-ktx:2.5.2`
- `androidx.room:room-rxjava2:2.5.2`
- Compiler: Uses Kotlin Symbol Processing (KSP)

### Jetpack Compose & UI
- `androidx.activity:activity-compose`
- `androidx.compose.bom`
- `androidx.material3`
- `androidx.appcompat`
- `com.google.android.material:material`
- `androidx.constraintlayout`

### Core Libraries
- `androidx.core:core-ktx`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `androidx.junit`
- `androidx.test.espresso:espresso-core`

## Getting Started

### Prerequisites
- Android Studio (latest)
- JDK 8 or higher
- Android SDK with API level 34

### Building the Project

1. **Clone the repository**
   ```bash
   git clone https://github.com/WoorimCho/Android-Finance-Manager-App.git
   cd Android-Finance-Manager-App
   ```

2. **Open in Android Studio**
   - File → Open → Select the project directory

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run the application**
   ```bash
   ./gradlew installDebug
   ```
   Or use Android Studio's Run button to install and run on an emulator/device.

## Gradle Configuration

The project uses modern Gradle features:
- **Kotlin DSL** for build scripts
- **Kotlin Symbol Processing (KSP)** for annotation processing
- **Version Catalog** for dependency management

### Build Options
- **Minimum SDK**: 29
- **Target SDK**: 34
- **Java Compatibility**: 1.8
- **Kotlin JVM Target**: 1.8

## Database

The app uses Room for local data persistence. Database operations are handled through:
- Entity definitions in the `room/` package
- Data Access Objects (DAOs)
- Kotlin coroutines for async database operations

## Testing

The project includes support for:
- **Unit Tests**: Located in `app/src/test/`
- **Instrumented Tests**: Located in `app/src/androidTest/`
- Test runner: `androidx.test.runner.AndroidJUnitRunner`

Run tests with:
```bash
./gradlew test                    # Unit tests
./gradlew connectedAndroidTest   # Instrumented tests
```

## Architecture

The application follows modern Android architectural patterns:
- **Activities** for screen navigation
- **Jetpack Compose** for declarative UI
- **Room Database** for data persistence
- **Coroutines** for asynchronous operations

## Permissions

The application requires appropriate permissions defined in `AndroidManifest.xml` for:
- Data backup and extraction
- Icon and theme resources
- RTL (Right-to-Left) layout support

## Future Enhancements

Potential areas for expansion:
- Cloud synchronization
- Budget planning features
- Financial reports and analytics
- Receipt scanning
- Multi-user support
- Export to PDF/CSV

## License

This project is created by Woorim Cho. For license information, please check the repository.

## Contributing

Contributions are welcome! Please feel free to submit issues and pull requests to improve the application.

## Author

**Woorim Cho**
- GitHub: [@WoorimCho](https://github.com/WoorimCho)

## Support

For questions or issues, please open an issue on the GitHub repository.

---

**Note**: This is a trial/learning project ("AccountManagerTrialZone") demonstrating Android development practices with Kotlin, Jetpack Compose, and Room database.
