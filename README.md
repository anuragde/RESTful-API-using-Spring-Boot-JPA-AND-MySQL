# RESTful-API-using-Spring-Boot-JPA-and-MySQL-for-Fleet-Management
Provides multiple API end points. Try it out @ http://vehicletracker.us-east-1.elasticbeanstalk.com/swagger-ui.html 


1. API endpoints to `PUT /vehicles` Vehicle details, `POST /readings`, `GET`, `UPDATE`, `DELETE` records.
2. API metrics by actuator and API documentation by Springfox swagger.
3. Generate alerts with given priority when following rules are triggered
   * Rule: engineRpm > readlineRpm, Priority: HIGH
   * Rule: fuelVolume < 10% of maxFuelVolume, Priority: MEDIUM
   * Rule: tire pressure of any tire < 32psi || > 36psi , Priority: LOW
   * Rule: engineCoolantLow = true || checkEngineLightOn = true, Priority: LOW
4. API endpoints to `GET` all alerts generated for a vehicle and `GET` recent high alerts of a vehicle
5. Text message is sent to vehicle's registered mobile number via Amazon SNS when a high alert is triggered.

Run the application using the commands in `rest.sh`.

`PUT /vehicles` JSON Input format:

````
[
 {
    "vin": "1HGCR2F3XFA027534",
    "make": "HONDA",
    "model": "ACCORD",
    "year": 2015,
    "redlineRpm": 5500,
    "maxFuelVolume": 15,
    "lastServiceDate": "2017-05-25T17:31:25.268Z"
 },
 {
    "vin": "WP1AB29P63LA60179",
    "make": "PORSCHE",
    "model": "CAYENNE",
    "year": 2015,
    "redlineRpm": 8000,
    "maxFuelVolume": 18,
    "lastServiceDate": "2017-03-25T17:31:25.268Z"
 }
]
````

`POST /readings` JSON Input format:

````
{
   "vin": "1HGCR2F3XFA027534",
   "latitude": 41.803194,
   "longitude": -88.144406,
   "timestamp": "2017-05-25T17:31:25.268Z",
   "fuelVolume": 1.5,
   "speed": 85,
   "engineHp": 240,
   "checkEngineLightOn": false,
   "engineCoolantLow": true,
   "cruiseControlOn": true,
   "engineRpm": 6300,
   "tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }
}
````
