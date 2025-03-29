# Loan Management App – Technical Refactor Report

## 🚀 Project Setup & Compatibility Adjustments

The project initially referenced **Java 11**, but due to compatibility with **Android Gradle Plugin 8.0+** and **Kotlin 1.9+**, I upgraded the environment to **JDK 17**, which is required for modern Android development.

Additionally, the project was using `kapt`, a legacy annotation processor. I migrated it to **KSP (Kotlin Symbol Processing)**, which is more efficient, future-proof, and better integrated with the Kotlin ecosystem, especially with Java 16+ restrictions on reflection and encapsulation.

## 🧹 .gitignore Cleanup

Several IDE and build artifacts such as `.idea/`, `.gradle/`, `.DS_Store`, and build outputs were mistakenly tracked.  
I updated the `.gitignore` file accordingly and removed these entries from version control.

## 🧠 Strategy Pattern Implementation

The original code handled loan status updates using nested `if-else` blocks. These were hard to read and harder to extend.

I introduced the **Strategy Design Pattern** to encapsulate each loan rule in a separate class:
- `DueDateStrategy`
- `PaidLoanStrategy`
- `DefaultLoanStrategy`

Each strategy is injected via **Hilt’s multibinding** and selected dynamically via a `LoanStrategySelector`.

## 🧩 Context Injection via Hilt

`Context` was unnecessarily passed across layers like `MockLoanService`.  
I created an `AssetLoader` class to load JSON data from assets, and injected it using `@ApplicationContext` via Hilt.

## 🏗️ Compose Architecture – App Root

I introduced a composable root called `LoanApp()` to manage navigation between Login and Home screens.  
This acts as the UI entry point and replaces direct usage inside `MainActivity`.

## 📐 MVVM Adoption and Component-Based UI

To make the UI scalable and testable, I introduced ViewModels:
- `LoginViewModel` handles login input, validation, and triggers login events.
- `HomeViewModel` handles loan loading, interest/payment calculations, and state management.

UI components were split into smaller composables:
- `LoanCard`
- `LoanHeader`
- `LoanDetailsSection`

Each resides under `ui.components.loan`.

## 💰 Domain Logic Improvements

Removed unclear or unnecessary functions (e.g. repeating math operations pointlessly).  
Added real-world financial calculations:
- **Total interest**: `principal × rate`
- **Monthly payment**: amortization formula  
Results are formatted to **two decimal places**.

## 🖼️ Enhanced UX/UI

Loans are displayed as color-coded cards:
- Red → `overdue`
- Yellow → `default`
- Green → `paid`
- Light yellow → `active`

This provides visual clarity and fast recognition.

Additionally, the **username** from login is preserved using a shared `UserInfoSharedViewModel` and displayed at the top of the home screen.

## ✅ Summary

This refactor improved:

- **Architecture**: Clean MVVM + Strategy Pattern
- **Maintainability**: Easier to test, extend, debug
- **Readability**: Clear and consistent structure
- **User Experience**: Informative, intuitive, visual

The app is now ready for **real-world scale** with modern Android standards:  
**Jetpack Compose**, **Hilt**, and **KSP**.

