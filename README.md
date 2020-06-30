# odm-spring-postgre-docker
Combination of several ibm odm projects with spring boot application and postgre database wrapped with docker-compose

Postman Collection with requests: https://www.postman.com/collections/86a34d5c4236bae979e1

When using containers run this:

```mvn package && docker-compose up -d --build```

Don't forget to fill your own credentials in application.yml

# Instructions:

## First checkout to branch oneService

```git checkout oneService```

Run this:

```mvn clean package && mvn spring-boot:run```

Follow to http://localhost:8080/DecisionService and you will see this description:

![Screenshot 2020-06-30 at 16 19 01](https://user-images.githubusercontent.com/17321542/86130933-9d761b00-baed-11ea-8ac5-399600389cd7.png)

Run ```1 consume one service``` from postman collection. You just requested an ODM service but with proxy service on your localhost

## Second checkout to branch twoServices

```git checkout twoServices```

Run this:

```mvn clean package && mvn spring-boot:run```

Run the application. Follow to http://localhost:8080/DecisionService and notice that description is a bit different:

![Screenshot 2020-06-30 at 16 22 07](https://user-images.githubusercontent.com/17321542/86131090-d9a97b80-baed-11ea-9896-0a5875099086.png)

Run ```2 consume two services``` from postman collection. Now it is a bit more smart. Basically you requested one ODM service with request from other ODM service

## At last checkout to branch addPostgres

```git checkout addPostgres```

Now to run your service with Postges you need this command:

```mvn clean package && docker-compose up -d --build```

It will deploy 2 containers: app and db. Now we are working same as in previous step, our response depends on "name" from Request and if it is in our DB.

Run ```badValues/values``` from postman collection to get all values from DB.
To stop everything run ```docker-compose down```

And of course...

![IMG_20200630_152649_172](https://user-images.githubusercontent.com/17321542/86126443-04dc9c80-bae7-11ea-9f6e-774c9789a911.jpg)
