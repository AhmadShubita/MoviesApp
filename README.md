# Movies App

Movies App is an Android application built using Kotlin and Jetpack Compose, following clean architecture principles, modularization, and various modern Android development technologies.

## Architecture

The project is structured using Clean Architecture, separating concerns into layers:

- **Data:** Contains the remote and local module data sources, as well as the repository implementations.

- **Domain:** Contains the entities and use cases module representing the business logic.

- **Presentation:** Consists of the UI and ViewModel module.

## Features

### Movies Screen

- #### Top Rated Movies Section

-   #### Upcoming Movies Section

-   #### Popular Movies Section

-   #### Dark/Light Mode Switcher

### Tv Series Screen

### People Screen

### Details

### View All Screen

## Screenshots and Video

<table>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/All%20Movies.png" alt="image1"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/blob/main/Details%20Screen.png" alt="image2"></td>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/3a05e3e8-0523-4fed-9375-0fdf59bd6a21" alt="image3"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/07018609-90d8-4f1f-802b-82268e2e23cf" alt="image4"></td>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/037541ef-c13f-458b-b6f9-a7928b0b8bd1" alt="image5"></td>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/feb5c0fe-73bc-45b7-9731-9e7904b3aa57" alt="image6"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/101d3789-6736-465c-8e94-db469cf06e3d" alt="image7"></td>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/1a24dd6f-90f0-4386-842a-df9fa144a710" alt="image8"></td>
    <td><img src="https://github.com/Abdallahx3x/NewsHive/assets/83548062/d2e70ef5-4bd1-45f6-8f50-dc4a963601b7" alt="image9"></td>
  </tr>
</table>

## Technologies Used

- **MVVM Architecture:** Utilizing the Model-View-ViewModel architecture pattern for a clean separation of concerns.
- **Paging:** Implementing pagination for efficient data loading.
- **Retrofit:** Networking library for handling API calls.
- **Room Database:** Local storage solution for caching and offline capabilities.
- **Coroutines:** Asynchronous programming for managing background tasks.
- **Kotlin Flow:** Reactive programming for handling streams of data.
- **Dependency Injection:** Utilizing a dependency injection framework (e.g., Dagger, Koin) for managing object creation and injection.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Obtain an API key from [MediaStack](https://mediastack.com/).
4. Insert the API key in the appropriate configuration file.
5. Build and run the application on an emulator or physical device.

## Download Latest Release

Download the latest version of the News Hive App from the [Releases Page](https://github.com/Abdallahx3x/NewsHive/releases/tag/v1.0.0).

## License

This project is open source and released under the [MIT License](LICENSE).
