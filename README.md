<!---
# Docta
Multiplatform application for practicing school subjects at CTU in Prague.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
-->

---

# Docta

**Docta** is a multiplatform application developed for students at CTU (Czech Technical University) in Prague. Its primary goal is to help students practice school subjects.

## Project Structure

The project is divided into several main components to organize shared code and platform-specific implementations.

- `/composeApp`
This directory contains the core logic that is shared across platforms using Kotlin Multiplatform and Compose. It contains several subfolders:

  - **`commonMain/`**  
    This folder is for code that is common to all targets.

  - **Platform-specific folders**  
    Each platform has its own folder for specific implementations. This is where the platform-specific code is located:
    - **`androidMain/`** – Kotlin code specific to Android.
    - **`iosMain/`** – Kotlin code specific to iOS.
    - **`desktopMain/`** – Code intended for desktop platforms, such as macOS, Windows, or Linux.

- `/iosApp`
This folder contains the iOS application. Even when the UI is shared through Compose Multiplatform, a native entry point is still required for the iOS app. This directory also allows for the integration of SwiftUI or UIKit components, as necessary for iOS-specific features.

## Learn More

- **Kotlin Multiplatform**: [Learn more](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)

## Contribution

Contributions are encouraged. It is recommended to submit a pull request or review the [issues](https://github.com/erwinelder/docta/issues) for tasks requiring attention. Contributions such as bug fixes or the addition of new features are valued.

## License

This project is licensed under the **GNU General Public License v3.0**.  
For more details, see the [LICENSE](LICENSE) file.
