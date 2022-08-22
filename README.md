# reservationApp

Simple apartment rental application

## Features
- Make and edit reservation
- Get reservations made by tenant 
- Get reservations made for specific apartment 

## Sample requests

To create reservation use:
```
curl -X POST http://localhost:8080/reservation -H "Content-Type: application/json" -d "{\"tenantId\": 1, \"apartmentId\": 1, \"startDate\":\"2022-09-12\", \"endDate\":\"2022-09-20\"}"
```

To update rent time use:
```
curl -X PUT http://localhost:8080/reservation/1 -H "Content-Type: application/json" -d "{\"startDate\":\"2022-09-28\", \"endDate\":\"2022-10-05\"}"
```

To get reservation made by specific tenant:
```
curl http://localhost:8080/reservation/tenant/{tenantId}
```
To get reservation made for specific apartment:
``` 
curl http://localhost:8080/reservation/apartment/{apartmentId}
```

## Technologies
- Java 8
- Spring Boot
- H2
- JPA
- Hibernate

