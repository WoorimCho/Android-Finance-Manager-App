# Android Finance Manager App

A personal finance management app for Android. Manage multiple accounts, track transactions, and maintain a stock portfolio.

## What This Is

A View-system app built with XML layouts and Activities. An unfinished Jetpack Compose migration exists in the codebase (AccountScreen.kt and fragments) but is not integrated into the running app and can be ignored for now.

## Features

- **Account Management**: Create, edit, and view multiple financial accounts
- **Transaction Tracking**: Record and view transaction history
- **Stock Portfolio**: Manage stock holdings and perform stock exchanges
- **Fund Transfers**: Transfer funds between accounts with transfer history
- **Local Database**: Persistent storage using Room database

## Tech Stack

- **Language**: Kotlin 100%
- **UI**: XML layouts with Activity-based navigation
- **Database**: Room 2.5.2 with Kotlin Symbol Processing (KSP)
- **Build System**: Gradle with Kotlin DSL and version catalog
- **Minimum SDK**: 29
- **Target SDK**: 34

## Project Structure

```
Android-Finance-Manager-App/
├── app/src/main/
│   ├── java/com/example/accountmanagertrialzone/
│   │   ├── MainActivity.kt              # Main entry point
│   │   ├── AccountEditorActivity.kt     # Create/edit accounts
│   │   ├── AccountViewerActivity.kt     # View account details
│   │   ├── AccountViewerAdapter.kt      # RecyclerView adapter
│   │   ├── AccountScreen.kt             # Unfinished Compose (not used)
│   │   ├── StockEditorActivity.kt       # Stock management
│   │   ├── StockExchangeActivity.kt     # Stock trading
│   │   ├── TransactionHistoryActivity.kt
│   │   ├── TransferActivity.kt
│   │   ├── TransferHistoryActivity.kt
│   │   ├── room/                        # Room entities and DAOs
│   │   ├── stocks/                      # Stock business logic
│   │   ├── screen/                      # Compose fragments (not used)
│   │   └── ui/                          # UI utilities
│   ├── res/                             # XML layouts, strings, themes
│   └── AndroidManifest.xml
├── app/build.gradle.kts
├── settings.gradle.kts
└── gradle/
```

## Activities

| Activity | Purpose |
|----------|---------|
| MainActivity | Dashboard and primary navigation |
| AccountEditorActivity | Create or edit an account |
| AccountViewerActivity | View all accounts and account details |
| TransferActivity | Transfer funds between accounts |
| TransferHistoryActivity | View transfer history |
| StockEditorActivity | Add or modify stock holdings |
| StockExchangeActivity | Execute stock trades |
| TransactionHistoryActivity | View all transactions |

## Database

Room with Kotlin annotation processing. Entities and DAOs are in `room/` package. Database operations use Kotlin coroutines.

## Compose Migration (In Progress)

AccountScreen.kt contains Composable functions and theme definitions for a Material 3 UI, but is not referenced by any Activity. This was the start of a migration to Compose that has been paused. The XML layout system is currently in use throughout the app.

If you plan to finish the migration, start by integrating AccountScreen into AccountViewerActivity as setContent instead of setContentView.

## Building

```bash
# Clone
git clone https://github.com/WoorimCho/Android-Finance-Manager-App.git
cd Android-Finance-Manager-App

# Build
./gradlew build

# Install and run
./gradlew installDebug
```

Or open in Android Studio and use the Run button.

## Dependencies

**Database**
- androidx.room:room-runtime:2.5.2
- androidx.room:room-ktx:2.5.2
- androidx.room:room-rxjava2:2.5.2
- Annotation processor: KSP (Kotlin Symbol Processing)

**UI (XML + Activities)**
- androidx.appcompat:appcompat
- com.google.android.material:material
- androidx.constraintlayout:constraintlayout

**Compose (unused, present for migration)**
- androidx.activity:activity-compose
- androidx.compose.bom and related libraries
- androidx.material3

**Core & Testing**
- androidx.core:core-ktx
- androidx.lifecycle:lifecycle-runtime-ktx
- androidx.test.espresso:espresso-core
- junit

## Testing

Both test files (`ExampleUnitTest.kt` and the instrumented test) are untouched Android Studio templates and do not represent project test coverage. This is a good place to start adding real tests.

To run:
```bash
./gradlew test                    # Unit tests
./gradlew connectedAndroidTest   # Instrumented tests
```

## Next Steps

- Complete the Compose migration by connecting AccountScreen to Activities
- Add unit tests and instrumented tests for business logic
- Add integration tests for Room database queries
- Consider extracting business logic into a ViewModel layer

---

A learning/trial project by Woorim Cho.
