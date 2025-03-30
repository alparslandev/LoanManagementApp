<table>
<tr>
<td>

### Loan Management App – Technical Refactor Report

This project is a complete architecture and design refactor of an Android loan tracking app.  
From outdated architecture to Strategy Pattern, Compose best practices and modern DI with Hilt & KSP — everything was modernized for scalability and maintainability.

## 🚀 Project Setup & Compatibility Adjustments

The project initially referenced **Java 11**, but due to compatibility with **Android Gradle Plugin 8.0+** and **Kotlin 1.9+**, I upgraded the environment to **JDK 17**, which is required for modern Android development.

Additionally, the project was using `kapt`, a legacy annotation processor. I migrated it to **KSP (Kotlin Symbol Processing)**, which is more efficient, future-proof, and better integrated with the Kotlin ecosystem, especially with Java 16+ restrictions on reflection and encapsulation.

</td>
<td>
<img src="https://github.com/user-attachments/assets/73036786-7359-4dac-b670-94c5a6b56845" alt="Credit App" width="61.8%"/>
</td>
</tr>
</table>

---

## 🧹 .gitignore Cleanup

Several IDE and build artifacts such as `.idea/`, `.gradle/`, `.DS_Store`, and build outputs were mistakenly tracked.  
I updated the `.gitignore` file accordingly and removed these entries from version control.

---

## 🧠 Strategy Pattern Implementation

The original code handled loan status updates using nested `if-else` blocks. These were hard to read and harder to extend.

I introduced the **Strategy Design Pattern** to encapsulate each loan rule in a separate class:
- `DueDateStrategy`
- `PaidLoanStrategy`
- `DefaultLoanStrategy`

Each strategy is injected via **Hilt’s multibinding** and selected dynamically via a `LoanStrategySelector`.

---

## 🧩 Context Injection via Hilt

`Context` was unnecessarily passed across layers like `MockLoanService`.  
I created an `AssetLoader` class to load JSON data from assets, and injected it using `@ApplicationContext` via Hilt.

---

## 🏗️ Compose Architecture – App Root

I introduced a composable root called `LoanApp()` to manage navigation between Login and Home screens.  
This acts as the UI entry point and replaces direct usage inside `MainActivity`.

---

## 📐 MVVM + Clean Architecture

To make the UI scalable and testable, I introduced ViewModels:
- `LoginViewModel` handles login input, validation, and login events.
- `HomeViewModel` handles loan loading, interest/payment calculations, and UI state.
- `UserInfoSharedViewModel` acts as a shared view model to preserve login state and pass the username between screens.

---

## 🧱 Component-Based UI

UI components were modularized and extracted:
- `LoanCard`, `LoanHeader`, and `LoanDetailsSection` under `ui.components.loan`
- **Newly added:**
  - `CustomEditText` – reusable text input with optional password toggle
  - `PrimaryButton` – reusable button for consistent styling

This promotes **code reuse** and aligns with **Component-Based Design** requested in the task.

---

## 💰 Domain Logic Improvements

Removed unclear or unnecessary functions (e.g. repeating math operations pointlessly).  
Added real-world financial calculations:
- **Total interest**: `principal × rate`
- **Monthly payment**: amortization formula  
Results are formatted to **two decimal places**.

---

## 🔐 User Session Persistence

Login state and username are now persisted using **SharedPreferences**, injected using Hilt.  
- Username is restored from storage on app restart
- `Logout` button clears session and returns to login
- Session sharing is handled with `UserInfoSharedViewModel`

---

## 🖼️ Enhanced UX/UI

Loans are displayed as **color-coded cards**:
- Red → `overdue`
- Yellow → `default`
- Green → `paid`
- Light yellow → `active`

The design reflects loan status at a glance, improving usability.

---

## ✅ Summary

This refactor improved:

- **Architecture**: Clean MVVM + Strategy Pattern
- **Maintainability**: Easier to test, extend, debug
- **Reusability**: Shared ViewModels, components, strategy-based logic
- **User Experience**: Informative, intuitive, visually responsive
- **Persistence**: Login is remembered across launches

The app is now ready for **real-world scalability** with modern Android architecture:  
**Jetpack Compose**, **Hilt**, **KSP**, and **SharedPreferences**.
