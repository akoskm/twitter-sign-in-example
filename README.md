# twitter-sign-in-example
"Sign in with Twitter" in a web application.

### Requirements
- Java 7
- Maven 3.x
- application and generated consumer key/secret on https://apps.twitter.com/

### Build
    mvn clean package

### Pre-run
Replace `consumerKey` and `consumerSecret` in `src/main/resources/server.yml` with your own values.

### Run
    java -jar target/twitter-sign-in-example-1.0-SNAPSHOT.jar server src/main/resources/server.yml

Open https://localhost:9090/twitter
