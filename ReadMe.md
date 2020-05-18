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

#####Problems you discovered
```In the given CSV, color is missing for the last row, added validation based on the lenght of the coloumn, if the column lenght is less than 6 then returning 400 bad request error```

```In the given CSV make and model are combined in single column but in the given json request make and model are given separte, added functionality accordingly and handling based on the input request```

#####Ideas you would like to implement if you had time
```
1. Handling more test cases
2. Will move make and model into catalog data tabel and while publishing data will FK_ID instead of make and model text so that we cn reduce repeted values
3. More accurate CSV reading process, as of now if one row is wrongly given skipping all the data, instead of that I can improve this process and publish correct rows and skipp the failed rows and will return the response with failed rows
4. Fly way configuration to inserted some dealer data(dummy data) and validate the dealer id against dealer table while publishing the list
5. FK relations
```
#####Decisions you had to take and why
```
If the column length is less the 6 then skipping all the data and returning client error
Used Open CSV to parse the CSV, we can use consider other available libraries
```
