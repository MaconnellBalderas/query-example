# query-example
1. Clone the project.
2. Make sure to have lombok installed in IDE
3. Run Application

API examples:
http://localhost:8080/experience
http://localhost:8080/experience?countries=US,GB&devices=iPhone 4
http://localhost:8080/experience?devices=iPhone 4
http://localhost:8080/experience?countries=US,GB

created a JPA Repository and loaded the data from the Bugs.csv file along with the other testers/devices data. Implemented Bugs/Tester/Device models and used jpaRepository and hibernate to join the Bug table with Tester and Device tables. Created queries to search for bugs based on countries and/or devices and used that data in a Hashmap to implement counting the amount of bugs each Tester filed.
