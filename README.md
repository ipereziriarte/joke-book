# JokeBook

![Screenshot](artwork/ic_launcher-web.png)

## Requirements
This app creates a Joke Book based on the official joke api.

It contains three screens:

+ A front page with a cover and a button to navigate to the second screen.
+ A screen with 3 categories and a list of 'setup' fields for each category.
+ A detail screen that contains the punchline for the selected joke

### Assumptions
+ I assume that the list of jokes has only 10 results and doesn't have pagination.
+ I assume that the app doesn't work offline. Otherwise I would create a local cache.
+ I assume that the list of jokes can't be refreshed by the user, otherwise I would add a pull to refresh or
similar widget.

### Prerequisites
This apps is built using jetpack compose. In order to run it you will need to download the latest version of
[Android Studio Canary](https://developer.android.com/studio/preview)

It will also require to have the latest gradle version (>7.0 and auto installed with the gradle wrapper)

### Code Style
Code style is managed by [ktlint](https://github.com/pinterest/ktlint) through the [spotless gradle plugin](https://github.com/diffplug/spotless/tree/main/plugin-gradle)

In order to run the checks type in the terminal `gradlew build` or `gradlew.bat build` on windows.

In a bigger project with several people working on it I would add this checks to a git pre-commit hook.
If a CI is available, I would add the checks using [fastlane](https://fastlane.tools/).

### Dependency management
This uses Gradle dependency catalog as explained [here](https://docs.gradle.org/current/userguide/platforms.html)
The experimental feature is enabled at `settings.gradle` file and the dependencies are declared at `gradle/libs.versions.toml`

### Navigation
The navigation is provided by the [Android Jetpack navigation component](https://developer.android.com/guide/navigation).
