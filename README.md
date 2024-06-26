# Movies App

Movies App is an Android application built using Kotlin and Jetpack Compose, following clean architecture principles, modularization, and various modern Android development technologies.

## Screenshots and Video

<table>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/d3e48dee-ed63-491a-915e-0a1b4ebd2a62" alt="image1"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/9630bc64-a454-4f22-b403-b0703a1d83e4" alt="image2"></td>  </tr>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/2a2051c7-a236-42de-8642-1bfcf2cc5c4c" alt="image4"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/dc763584-254a-439c-8358-7e1ca5c73e1d" alt="image5"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/c541b5cf-46f5-40dc-bb72-6cb2246eea71" alt="image7"></td>
    <td><img src="https://github.com/AhmadShubita/MoviesApp/assets/25340380/989f8c17-9855-4675-bcea-3ec13f6048a4" alt="image8"></td>
  </tr>
</table>

## Architecture

The project is structured using Clean Architecture, separating concerns into layers:

- **Data:** Contains the remote and local module data sources, as well as the repository implementations.

- **Domain:** Contains the entities and use cases module representing the business logic.

- **Presentation:** Consists of the UI and ViewModel module.

## Features

### 1- Movies Screen

- #### Top Rated Movies Section

- #### Upcoming Movies Section

- #### Popular Movies Section

- #### Dark/Light Mode Switcher

### 2- Tv Series Screen
### 3- People Screen

### 4- Details

### 5- View All Screen

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
