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
This project was coded using Android Studio Artic Fox 2020.3.1 Canary 15 and gradle plugin 7.0.0-Alpha15.

It will also require to have the latest gradle version (>7.0 and auto installed with the gradle wrapper)

## Implementation

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

### ViewPager
The Joke List screen is composed using a Tab layout and a view pager. The Tab element already exists in the compose ui
components, however I used [Chris Banes Accompanist library](https://google.github.io/accompanist/pager/) to implement the view pager.

### Architecture
Given that I've been working with big projects based on Clean Architecture and RxJava, I decided to do something different
here in order to learn about Compose UI and Unidirectional State Flows.

The app has 2 modules just to demo how to create a multi module architecture.

The app is separated into 2 layers: Presentation and Data.

#### App module
The main module `App` contains the UI and view models.

Depending on the size of the project or the Requirements I would split this module with different modules per feature.
That way we could use dynamic features and work more efficiently in parallel and have smaller build times.

I have created an small animation for the loading state.

The data is collected by calling the repository from each view model (one per layout of the view pager) and it is modeled
using a sealed class called `CallResult` This is useful to handle the view state that can be loading, error or success.

Each change in the state forces a recomposition at ui level that paints different layouts for each of them.

#### Data module
The data module contains the repository and the remote datasource.
The calls are done with retrofit and I add different interceptors to the okhttp client depending on the build type.
Check `DebugNetworkModule`

#### What would I do different?
For a bigger project I would create a domain layer that contains the business logic, the core Joke model and and interface
that defines the Repository.

That way I could have some benefits:
- Isolate the data layer
- Reuse the Use Case to get the jokes so I can pass a param to it with the joke category.
- Setup some kind of cache policy
- Be able to choose between refreshing the jokes or load the stored ones.
- Write unit tests for the domain layer

### Testing

#### UI tests
UI tests are provided by the compose ui testing framework and must be run in a device or emulator in the debug variant.
The UI tests are located under `androidTest` folder

Check the [compose ui testing docs](https://developer.android.com/jetpack/compose/testing) for further info and take a
look to the `AndroidManifest.xml` file under `app/src/debug` that includes a little hack to let the compose element be
initialized without calling the parent activity.

#### Unit tests
I created a sample unit test in the data module.
With more time I would also write some tests for the API response and call with MockWebServer.

## Libraries
Dependencies are provided at `gradle/libs.versions.toml`
And I use:
+ 100% Kotlin
+ Hilt for dependency injection
+ Retrofit, OkHttp, Moshi for the network stack
+ Mockk for testing
