# PMZI app
This is web app that provides basic operations with users:
* authentication (based on JWT token)
* authorization (based on roles)
* registration
* input data validation
* review information from db via protected http endpoints

## How to run it
1) clone repository
2) go to the project root dir
3) run follow command:
```shell
docker-compose up --build
```
4) use health check endpoint to check that project is started

## How to shut down project
Use follow command in another console:
```shell
docker-compose down
```

## How to test it 
Use follow [pmzi-api Postman collection](https://www.postman.com/noct2000/workspace/public-workspace/collection/20171165-9cc3de2e-ff30-4788-8f83-2b0c84bcafbd)