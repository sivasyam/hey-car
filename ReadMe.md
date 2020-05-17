As per the given challenge document

#####Created endpoint to upload CSV
#####Created endpoint to publish vehicle json
#####Created endpoint to search publishing based on the parameters
#### Below are the Endpoints
```
Upload CSV

POST    http://localhost:8000/api/vehicle/upload_csv/{dealerId}
upload file with param name 'file'
```
```
Publish vehicle listing using Json

POST    http://localhost:8000/api/vehicle/vehicle_listings/{dealerId}
Sample input
[
    {
        "code": "2",
        "make": "a31",
        "model": "audi",
        "kw": 111,
        "year": 2016,
        "color": "white",
        "price": 19000.0,
        "dealerId": 1
    }
]

```
```
Search Vehicle publishings

GET    http://localhost:8000/api/vehicle/search?model=auid
[
    {
        "code": "2",
        "make": "a3",
        "model": "audi",
        "kw": 111,
        "year": 2016,
        "color": "white",
        "price": 19000.0,
        "dealerId": 1
    }
]
```

####Technologies used
JAVA 8, Spring-boot, Maven, H2 database, docker

####Operating system tested:
Mac OS

####To Start the application
```
Download the project from the drive and unzip the folder
Open terminal and go to the project location ex cd ~/Downloads/hey-car
Run the script file docker-run.sh, example: sh docker-run.sh
if the docker is not available, please use following command
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8000
```
