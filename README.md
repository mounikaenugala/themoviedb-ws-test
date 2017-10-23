# The Movie Database API - Web Service Testing

The Movie Database supports API for querying on movies, tv shows, actor and image API. We are testing out few endpoints of the api using Java. 

This is a Java project which tests the following endpoints
  - Get lists - https://developers.themoviedb.org/3/lists/get-list-details
  - Get Movie Details - https://developers.themoviedb.org/3/movies/get-movie-details
  - Get Person Details - https://developers.themoviedb.org/3/people
  - Search Movies - https://developers.themoviedb.org/3/search/search-movies
  - Manage Session - https://developers.themoviedb.org/3/authentication/create-session
  - Manage Guest Session - https://developers.themoviedb.org/3/authentication/create-guest-session
  - Web Service media type
  - Web Service HTTP response

# Prerequisites

  - Apache Maven - Download and setup Maven (https://maven.apache.org/). Check the maven settings by typing in the following command in terminal which returns maven version.
  ```sh
  mvn --version
  ```
  - Git clone the repository
  ```sh 
  git clone https://github.com/mounikaenugala/themoviedb-ws-test.git
  ```
  - Replace the key in test.properties. Key can be obtained by signing up at https://www.themoviedb.org/account/signup
   ```sh 
  MOVIE_DB_API_KEY=ENTER_VALID_API_KEY_HERE
  to
  MOVIE_DB_API_KEY=abcdefghik......
  ```
# Run
  - Execute the following command in the project folder. This will download all the required libraries and execute the test cases. Please check if maven can download the libraries since it might fail if its behind firewall.
   ```sh 
  mvn test
  ```
   - Once the tests ran successfully you can see the results like the following.
   ```sh
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running themoviedb.lists.ListTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 6.142 sec
Running themoviedb.movie.MovieTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.221 sec
Running themoviedb.person.PersonTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.27 sec
Running themoviedb.search.movies.MovieSearchTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.18 sec
Running themoviedb.session.SessionTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.702 sec
Running themoviedb.tests.TestHTTPResponse
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.802 sec

Results :

Tests run: 14, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 9.382 s
[INFO] Finished at: 2017-10-22T17:54:46-07:00
[INFO] Final Memory: 11M/309M
[INFO] ------------------------------------------------------------------------
   ```

