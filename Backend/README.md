# apptracker
small description (& maybe logo)

## Description
Backend REST API created using SpringBoot.

## Endpoints
- /addUser: A POST request which takes in an email address as a string and stores it in the database. Returns "SUCCESS" or "ERROR" based on status of request.
- /getUsers: A GET request which returns the users and all their relevant data. Returns data as a JSON.
- /deleteUser: A DELETE request which deletes the user with the specified email address passed as a string. Returns "SUCCESS" or "ERROR" based on status of request.
- /addMRP: A POST request which takes in a company, link, role, and location and stores them in the database. Returns "SUCCESS" or "ERROR" based on status of request.
- /getMRP: A GET request which returns the MRP and all its relevant data. Returns data as a JSON.
- /deleteMRP: A DELETE request which deletes the most recent posting. Returns "SUCCESS" or "ERROR" based on status of request.
