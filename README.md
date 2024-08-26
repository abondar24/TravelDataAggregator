# TravelDataAggregator a.k.a TravelProcessor

A demo app showing extended usage of spring batch:

- Multijob app
- Reading incoming files of different types
- Integration with SFTP server
- Integration with SMTP server
- Running job in the cloud using cron

The app accepts different travel related data,stores it in db and makes reports sent via email about recently added data

## Jobs
1) hotelBookingsJob - JSON based job related to hotel bookings
2) flightBookingsJob - CSV based job with flight related data
3) cruiseBookingsJob - XML based job with some date related to cruise

Example job files are in data directory. 

Please note that as an assumption the data comes from the same origin meaning each entry no matter which job is it has a trip id, which can combine data from different jobs together. This is needed for reporting step.



## Build test and run

### Build

- Build the app using gradle wrapper
```
./gradlew clean build
```
- Build docker image
```
./gradlew jibDockerBuild
```
- Build jib image and push to registry

```
./gradlew jib
```
Note: requires credentials for registry set via env variables + tag requires registry id

### Test
- Run unit tests
```
./gradlew clean test
```
- Run integration tests
```
./gradlew clean integrationTest
```
### Run
- Run the app using gradlew

```
./gradlew bootRun -PprofileName  --args='["yourJobName"]'
```

- Run the app via jar

```
java -jar -Dspring.profiles.active=profileName -Dspring.batch.job.name=jobName build/libs/TravelDataAggregator-1.0.0.jar
```

- Run a docker image

```
`docker run --env-file ./dev.env -d --name travelData  abondar/travel-processor:1.0.0
```

- Run in kubernetes
```
  cd kube
  kubectl apply -f namespace.yml
  kubectl apply -f <resource-folder> -n travelspace
```
