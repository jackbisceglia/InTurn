# InTurn: [*Live*](https://theinturn.tech/)
**Looking for places to intern with? Our project tracks the widely used Pitt CSC Summer Internship Repository and notifies our subscribers when a new posting is added. You'll always be ready to apply!**


Web App              |  Sample Email
:-------------------:|:-------------------------:
![](https://i.imgur.com/cNiqNOp.png)|![](https://i.imgur.com/GVAYh6I.png)


## Description
InTurn is a Full Stack application that was designed to monitor and notify its users of new additions to the list of internships found on the Pitt CSC Summer Internships GitHub repository. The app periodically checks the repository for updates to the list of internships, and it will send an email to a list of subscribers if any changes to the list are made. To store information about the subscriber list and the list of internships, the app utilizes a PostgreSQL database hosted on SupaBase, and all monitoring is done through a custom python script. 

## Tech Stack
- Frontend Web app in React with create-react-app
- Backend REST API in Java and Spring Boot
- Database using PostgreSQL hosted on SupaBase
- Webhook/monitoring with Python and Flask

## MVP
- A frontend client that will allow users to sign up for and receive emails about changes to the Pitt CSC Summer Internship repository
- A basic flask web app that invokes a python script responsible for parsing the information from the repository for changes and notifying users of potential changes

## Plans for the future
- Create a dashboard for past internships applied to 
- More robust python script for checking for changes to the list (removals, position changes, etc.)
- Company preferences: Only receive notifications if a certain company posts offerings

## Team Members
- [Jack Bisceglia](https://github.com/jackbisceglia)
- [Sid Raju](https://github.com/sid2033)
- [Joe Petrillo](https://github.com/joepetrillo)
- [Eric Wu](https://github.com/ewu2023)
