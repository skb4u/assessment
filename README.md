# assessment

Run 

java -jar -Dspring.profiles.active=load target/assesment-0.0.1-SNAPSHOT.jar

To Test

GET http://localhost:8080/find

Body:

{
    "latitude": "37.79612338025215",
    "longitude": "-122.39727320433803",
    "withInDistance": 8
}

The response would display the restaurants with in the 8 miles distance
