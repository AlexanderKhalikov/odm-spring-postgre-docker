# odm-spring-postgre-docker
Combination of several ibm odm projects with spring boot application and postgre database wrapped with docker-compose

Postman Collection with requests: https://www.postman.com/collections/86a34d5c4236bae979e1

When using containers run this:

```mvn package && docker-compose up -d --build```

Don't forget to fill your own credentials in application.yml

# Intro

## What is ODM:

IBM Operational Decision Maneger is a technology which allow different business client such as banks, insurance companies and many more to automate their decision making proceses based on rules written in natural language for a particular business user.

# Instructions:

## First checkout to branch oneService

```git checkout oneService```

IBM ODM service may be requested by client:

![Screenshot 2020-07-21 at 17 38 23](https://user-images.githubusercontent.com/17321542/88068668-2de9cd80-cb79-11ea-9b5d-0d10ec34cfae.png)

But in this project a Spring Boot application is injected between client and ODM service:

![Screenshot 2020-07-21 at 17 38 36](https://user-images.githubusercontent.com/17321542/88068691-34784500-cb79-11ea-90de-3e887c07d2da.png)

Run this:

```mvn clean package && mvn spring-boot:run```

Follow to http://localhost:8080/DecisionService and you will see this description:

![Screenshot 2020-06-30 at 16 19 01](https://user-images.githubusercontent.com/17321542/86130933-9d761b00-baed-11ea-8ac5-399600389cd7.png)

Run ```1 consume one service``` from postman collection. You just requested an ODM service but with proxy service on your localhost

## Second checkout to branch twoServices

```git checkout twoServices```

Now let's try to imagine that business user need to commit some integrational changes to ODM, but it is very expensive in terms of time or some other resources to make these changes on requesting system. In this example it is called "additional paremeter". To solve this problem second ODM service is added. Now the architecture looks like this:

![Screenshot 2020-07-21 at 17 38 54](https://user-images.githubusercontent.com/17321542/88068701-393cf900-cb79-11ea-8e55-8652b3251a2c.png)

Run this:

```mvn clean package && mvn spring-boot:run```

Run the application. Follow to http://localhost:8080/DecisionService and notice that description is a bit different:

![Screenshot 2020-06-30 at 16 22 07](https://user-images.githubusercontent.com/17321542/86131090-d9a97b80-baed-11ea-9896-0a5875099086.png)

Run ```2 consume two services``` from postman collection. Now it is a bit more smart. Basically you requested one ODM service with request from other ODM service

## At last checkout to branch addPostgres

```git checkout addPostgres```

It may be very useful to enriche every request with some information from a Database

![Screenshot 2020-07-21 at 17 39 16](https://user-images.githubusercontent.com/17321542/88068718-3e9a4380-cb79-11ea-928a-9cc96c407fe9.png)

Now to run your service with Postges you need this command:

```mvn clean package && docker-compose up -d --build```

It will deploy 2 containers: app and db. Now we are working same as in previous step, our response depends on "name" from Request and if it is in our DB.

Run ```badValues/values``` from postman collection to get all values from DB.
To stop everything run ```docker-compose down```

And of course...

![IMG_20200630_152649_172](https://user-images.githubusercontent.com/17321542/86126443-04dc9c80-bae7-11ea-9f6e-774c9789a911.jpg)
