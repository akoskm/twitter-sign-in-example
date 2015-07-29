# twitter-sign-in-example
"Sign in with Twitter" server side application.

### Requirements
- Java 7
- Maven 3.x
- application and generated consumer key/secret on https://apps.twitter.com/

### Build
    mvn clean package

### Pre-run
Replace the temporary `consumerKey` and `consumerSecret` values in `src/main/resources/server.yml`

### Run
    java -jar target/twitter-sign-in-example-1.0-SNAPSHOT.jar server src/main/resources/server.yml
