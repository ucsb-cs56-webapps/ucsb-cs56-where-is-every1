# Fall 2018

This project is a simple webapp which scrapes course catalog of UCSB to take data of every class room being used. The data of classes in use will be outputted based on the room number or building. The class in use will be outputted in a graphical manner based on time-slots from first class to last.

How to run the project:
1) Clone the repo.
2) run: 'mvn spring-boot:run'

If you have issues, try these commands:
'mvn validate'
'mvn compile'

To push to heroku, use:
'mvn package heroku:deploy'

Heroku link:
https://ucsb-cs56-where-is-every1.herokuapp.com/

Note: 
To regenerate the json, simply run generateJson.sh. If you're on windows, don't be on windows.

Andrew: agprice

Diane: dianephan

Juan: jdavalos

Regina: reginafw

Kyle: KyleG43

Yagnya: yagnyaPatel
