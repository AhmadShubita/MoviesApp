# Movies App

Movies App is an Android application built using Kotlin and Jetpack Compose, following clean architecture principles, modularization, and various modern Android development technologies.

## Screenshots and Video

<table>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/Home%20Page.png" alt="image1"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/Home2%20Page.png" alt="image2"></td>  </tr>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/Tv%20Page.png" alt="image4"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/People%20Screen.png" alt="image5"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/Details%20Screen.png" alt="image7"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/All%20Movies.png" alt="image8"></td>
  </tr>
</table>

## Architecture

The project is structured using Clean Architecture, separating concerns into layers:

- **Data:** Contains the remote and local module data sources, as well as the repository implementations.

- **Domain:** Contains the entities and use cases module representing the business logic.

- **Presentation:** Consists of the UI and ViewModel module.

## Features

- ### Movies Screen

- #### Top Rated Movies Section

-   #### Upcoming Movies Section

-   #### Popular Movies Section

-   #### Dark/Light Mode Switcher

- ### Tv Series Screen

- ### People Screen

- ### Details

- ### View All Screen

## Technologies Used

- **MVVM Architecture:** Utilizing the Model-View-ViewModel architecture pattern for a clean separation of concerns.
- **Paging:** Implementing pagination for efficient data loading.
- **Jetpack Compose:** UI app development toolkit.
- **Retrofit:** Networking library for handling API calls.
- **Coroutines:** Asynchronous programming for managing background tasks.
- **Kotlin Flow:** Reactive programming for handling streams of data.
- **Dependency Injection:** Utilizing a dependency injection framework (Hilt) for managing object creation and injection.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Obtain an API key from [TMDB](https://www.themoviedb.org/).
4. Insert the API key in the appropriate gradle.properties file.
5. Build and run the application on an emulator or physical device.

## License

This project is open source and released under the [MIT License](LICENSE).
