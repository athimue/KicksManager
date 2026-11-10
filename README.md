# 👟 KicksManager

![Kotlin](https://img.shields.io/badge/Kotlin-2.4-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white)
![Min SDK](https://img.shields.io/badge/minSdk-29-3DDC84?logo=android&logoColor=white)
![Target SDK](https://img.shields.io/badge/targetSdk-37-3DDC84?logo=android&logoColor=white)

KicksManager is an Android app for sneaker resellers: track the pairs you own, record every sale and see how much you actually make on each platform.

<!--
## 📸 Screenshots

| Inventory | Sales | Statistics | Trends |
| :---: | :---: | :---: | :---: |
| <img src="docs/screenshots/inventory.png" width="200"/> | <img src="docs/screenshots/sales.png" width="200"/> | <img src="docs/screenshots/statistics.png" width="200"/> | <img src="docs/screenshots/trends.png" width="200"/> |
-->

## ✨ Features

**Inventory**
- Add a pair in seconds: search the model, pick the size, purchase price, date and shop
- See your stock value at a glance
- Search by model, size or shop, sort by date, price, name or size
- Swipe right to mark a pair as sold, swipe left to delete it (with undo)

**Sales**
- Every sale with its price, platform, date, fees and net profit
- Totals for revenue and net profit

**Statistics**
- Pairs in stock and stock value
- Revenue, purchases, fees and net margin
- Breakdown by platform, built from your actual sales: tap one to see its sales

**Trends**
- Most wanted sneakers, latest drops and new arrivals

Indigo theme in light and dark mode, Poppins typography, Material Symbols icons, available in English and French.

## 🛠️ Tech stack

| | |
| --- | --- |
| Language | Kotlin 2.4 |
| UI | Jetpack Compose, Material 3, Material Symbols, Poppins |
| Architecture | MVVM, clean architecture, multi-module (one `data` / `domain` / `ui` module per feature) |
| Dependency injection | Hilt |
| Persistence | Room |
| Network | Retrofit, OkHttp |
| Images | Coil 3 |
| Async | Kotlin Coroutines & Flow |
| Navigation | Navigation Compose (type-safe routes) |

## 🚀 Getting started

Requirements:
- Android Studio with Android Gradle Plugin 9.5 support
- JDK 17+
- Android SDK Platform 37

```bash
git clone https://github.com/athimue/KicksManager.git
cd KicksManager
```

Open the project in Android Studio, let Gradle sync, then run the `app` configuration.

## 🧱 Project structure

```
app/                  Entry point, theme and bottom navigation
core-ui/              Shared design components (cards, badges, top bar, empty states)
core-data/            Shared database and network setup
feature-inventory/    Pairs in stock (ui / domain / data)
feature-sells/        Recorded sales (ui / domain / data)
feature-statistics/   Key figures and per-platform stats (ui / domain / data)
feature-trends/       Sneaker trends (ui / domain / data)
```

## 🙏 Credits

- [Poppins](https://fonts.google.com/specimen/Poppins) font, SIL Open Font License (see `licenses/Poppins-OFL.txt`)
- [Material Symbols](https://fonts.google.com/icons) icons, Apache License 2.0

## 🤝 Contributing

Issues and pull requests are welcome.
