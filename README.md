# RESTful-API-using-Spring-Boot-JPA-AND-MySQL
Provides multiple API end points

1. PUT /vehicles JSON Input format:

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

2. POST /readings

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