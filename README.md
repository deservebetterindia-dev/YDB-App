# Deserve Better - YDB Android App

This is the official Android application for Deserve Better, a wellness brand focused on providing high-quality Ayurvedic products.

## Project Structure

The project follows a standard Android architecture with a modular design based on Clean Architecture principles.

- **/app**: The main application module.
- **/app/src/main/java**: Contains all the Kotlin source code.
  - **/com/deservebetter/ydbandroid**:
    - **/data**: Data layer, including models, repositories, and data sources.
    - **/di**: Hilt dependency injection modules.
    - **/navigation**: Navigation graph, routes, and bottom navigation setup.
    - **/ui**: UI layer, containing Composables for screens, components, and the theme.
    - **/viewmodel**: ViewModels for each screen that requires business logic.

## How to Run the App

1.  Open the project in the latest stable version of Android Studio.
2.  Ensure you have a configured Android emulator or a physical device connected.
3.  Sync the Gradle files to download all the necessary dependencies.
4.  Select the `app` run configuration.
5.  Click the "Run" button (or press `Shift` + `F10`).

## Mock Data

To facilitate offline development and testing, the app currently uses a local mock data file.

- **Location**: The mock data is located at `/app/src/main/assets/mock_home.json`.
- **Content**: This JSON file contains all the data for the home screen, product details, and categories. You can edit this file to change the content displayed in the app.

## Connecting to a Real API

The project is set up with different build types (`debug`, `staging`, `release`) to make it easy to switch between different environments.

- **API URLs**: The base URL for the API is defined as a `buildConfigField` in the `app/build.gradle.kts` file.
- **Switching to a Real API**: To connect to a live backend, you will need to:
  1.  Update the `API_URL` values in `app/build.gradle.kts` to point to your production and staging endpoints.
  2.  Implement a `RemoteDataSource` in the `/data/remote` package that uses a networking library like Retrofit to fetch data from the API.
  3.  Update the `HomeRepositoryImpl` to switch between the local mock data and the remote data source based on the build type or a feature flag.

This setup ensures a smooth transition from a mock-data-driven app to a fully-featured, production-ready application.
