# json-bd
Customers management in a json file

To handle the issue of database storage, the decision was made to do so through the container's file system.

To execute the process, it is recommended to build the image and run the container, and thus be able to access the functionalities.

Commands used after clone: 

```sh
mvn clean install

docker build -t juanbg/json-crud-v4 .
docker run -p8080:8080 juanbg/json-crud-v4
```
