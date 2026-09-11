# Android Finance Manager App

A personal finance management app for Android. Manage multiple accounts, track transactions, and maintain a stock portfolio.

## What This Is

A View-system app built with XML layouts and Activities. An unfinished Jetpack Compose migration exists in the codebase (AccountScreen.kt, Compose dialogs, and theme definitions in ui/theme/) but is not integrated into the running app and can be ignored for now.

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
│   │   ├── screen/                      # State management and Compose dialogs
│   │   │   ├── account/                 # AccountViewModel, AccountState, AccountEvent
│   │   │   ├── stock/                   # StockViewModel, StockState, StockEvent
│   │   │   └── types/                   # SortType, StockSortType
│   │   ├── stocks/                      # Stock business logic
│   │   └── ui/
│   │       └── theme/                   # Compose theme definitions (Color, Theme, Type)
│   ├── res/                             # XML layouts, strings, themes
│   └── AndroidManifest.xml
├── app/build.gradle.kts
├── settings.gradle.kts
└── gradle/
```

## Architecture

The codebase contains a proper state-and-event architecture:

**screen/account/**
- `AccountViewModel` – manages account list and operations
- `AccountState` – UI state for accounts
- `AccountEvent` – user actions (create, delete, sort, etc.)
- `AddAccountDialog` – Compose dialog for adding accounts (not wired to Activities)

**screen/stock/** (mirrors account structure)
- `StockViewModel` – manages stock holdings
- `StockState` – UI state for stocks
- `StockEvent` – user actions on stocks
- `StockDialog` – Compose dialog for stock operations (not wired to Activities)

**Current Gap:** The Activities call Room DAOs directly, bypassing these ViewModels. The next step is to wire Activities to use the ViewModels instead.

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

Room with Kotlin Symbol Processing (KSP). Entities and DAOs are in `room/` package. Database operations use Kotlin coroutines.

## Compose Migration (In Progress)

AccountScreen.kt, Compose dialogs (AddAccountDialog, StockDialog), and theme definitions in `ui/theme/` are part of an unfinished Compose migration. None of these are currently referenced by Activities.

If you plan to finish the migration, wire Activities to use the ViewModels in `screen/` instead of calling DAOs directly, then replace setContentView with setContent and integrate the Compose dialogs.

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

- Wire Activities to use ViewModels instead of calling DAOs directly
- Integrate Compose dialogs (AddAccountDialog, StockDialog) into Activities or convert to custom views
- Complete the Compose migration by replacing setContentView with setContent
- Add unit and instrumented tests for business logic and Room queries

---

A learning/trial project by Woorim Cho.
