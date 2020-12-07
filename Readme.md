# Wildlife Tracker
#### Java Application for tracking animals in parking, December 7 2020
#### By **Kamana Izere Emile**
## Description
The App wild lfe tracker is for tracking animal in a park and ensuring their safety while considering the new construction program around the park. It is mainly for protecting animals endangered by the construction.
## Setup/Installation Requirements
* Internet connection
* access to a browser
* fork from https://github.com/emile067/animal-tracker
## In PSQL:
* CREATE DATABASE wildlife_tracker;
* CREATE TABLE animals (id serial PRIMARY KEY, name varchar,endangered boolean,health varchar,age varchar);
* CREATE TABEL sightings (id serial PRIMARY KEY, animalid int,location varchar,rangername varchar);
* CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;
## Known Bugs
Deployment
## Technologies Used
* IntelliJ IDEA
* Java
* Spark
## Support and contact details
If you have any questions reach out to me on [emileizere@gmail.com]
### License
Licensed by MIT
Copyright (c) 2020 **Kamana Izere Emile**
