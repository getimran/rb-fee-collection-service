# rb-fee-collection-service


Fee Collection Service is used to collect fee based on studentId. \
Retrieve Fee details based on studentId or txnReferenceId (receipt no)

### API:
* POST - [/rakbank/api/fee/collect](/rakbank/api/fee/collect) (COLLECT FEE - check swagger for more details)
* GET - [/rakbank/api/fee/student/{studentId}](/rakbank/api/fee/student/{studentId}) (GET FEE DETAIL BY STUDENT_ID - check swagger for more details)
* GET - [/rakbank/api/fee/{referenceId}](/rakbank/api/fee/{referenceId}) (GET FEE DETAIL BY REFERENCE_ID - check swagger for more details)

#### Useful Links
* Local Swagger URL: [http://localhost:8082/rakbank/api/fee-collect/swagger-ui.html](http://localhost:8082/rakbank/api/fee-collect/swagger-ui.html)
* Swagger File: [https://github.com/getimran/rb-student-fee-collection/blob/master/swagger/rb-fee-collection-service-swagger.json](https://github.com/getimran/rb-student-fee-collection/blob/master/swagger/rb-fee-collection-service-swagger.json)
* Postman Collection: [https://github.com/getimran/rb-student-fee-collection/blob/master/postman/2-rb-fee-collection-service.postman_collection.json](https://github.com/getimran/rb-student-fee-collection/blob/master/postman/2-rb-fee-collection-service.postman_collection.json)

### Tech Stack
* Java 8
* Spring Boot
* Spring Data JPA
* H2 in-memory database

### Installation
**Run locally Using Intellij:**
```
git clone https://github.com/getimran/rb-fee-collection-service.git
git checkout develop
gradle clean build
gradle bootRun
```