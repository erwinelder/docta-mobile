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

[Project website](https://paper-muscle-49d.notion.site/ce2c4bb2a10f418da98b32d9a649d997?v=12508208da0480d28501000c5507b753)

## Project Description

Docta is an application designed to help students at CTU in Prague practice various school subjects.

Students can complete exercises of different types and difficulty levels to improve their skills in specific subjects. In Docta, a subject is referred to as a “course.”

Each course is divided into sections, sections into lessons, and lessons into questions. This structure allows students to practice at their own pace, aligning with the progression of their lectures. It also assists teachers by providing a structure similar to the lecture sequence, making course creation easier.

Exercises in Docta are accompanied by relevant study materials, enabling students to access necessary theoretical content without leaving the question screen.

While some school subjects, especially in math, already offer practice exercises, they are often available only as PDF scripts on web pages. Furthermore, only a few subjects have such exercises, and many have none at all.

Docta also benefits teachers by providing insights through student data within Docta, such as identifying the most challenging aspects of a subject.

The goal of Docta is to make practicing more convenient by consolidating exercises from scripts and web pages into one platform, or by creating new ones. This approach aims to make the practicing process more user-friendly and improve the overall quality of studying.


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
