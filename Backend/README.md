## App Tracker Backend REST API
Our RESTful service for handling user subscriptions on our tracker

## Usage
The Spring Boot based REST API allows for necessary database interactions in our app. It provides CRUD functionality for our user base, as well as for our internship postings tracking

## Technologies
- [Spring Boot](https://spring.io/projects/spring-boot)
- Java 8
- PostgreSQL
- [Google Cloud Platform](https://cloud.google.com/gcp)
  - [App Engine](https://cloud.google.com/appengine)

## Installation
1. Clone the repository: `git clone *repo url*`
2. Navigate into backend: `cd Backend`
3. Initialize dependencies with Maven (build with Maven)
4. Boot dev server: (run Backend\backend-mlh\src\main\java\com\backendmlh\BackendMlhApplication.java)

## Endpoints
### User Related
- Insert new user: `POST /addUser`
  - Takes in JSON Payload with a single property, `email_address`, and inserts into our database
  - Returns "SUCCESS" or "ERROR" accordingly
- Get all users: `GET /getUsers`
  - Returns JSON with all subscribed users
- Delete a user: `DELETE /deleteUser`
  - Deletes the user by specified email address, passed as JSON Payload
  - Returns "SUCCESS" or "ERROR" accordingly

### Intern Posting Related
- Insert Most Recent Posting: `POST /addMRP`
  - Takes in JSON Payload with properties, `company, link, role, location`, and inserts into database
  - Returns "SUCCESS" or "ERROR" accordingly
- Get Most Recent Posting: `GET /getMRP`
  - Returns JSON with all subscribed users
