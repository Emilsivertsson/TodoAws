# Todo App

### Description
This is the backend for a school-assignment. The frontend is a simple todo-app that can be found [here]. \
The backend is a Springboot REST API that uses a AWS RDS database to store the todos.\


### What was your motivation?
The assigment was to create a Springboot application with a Ci/CD pipeline onto AWS. The application should be a REST API that uses a AWS RDS database to store data.
I chose to make the application a todo-app because it is a simple application that can be used to demonstrate the basic CRUD operations.


### Installation
To install the project, you need to have the following installed:
- Java 17

Clone the repository and open it in your favorite IDE.\
Run the application, and it will start on port 8080.


### Usage
when the application is running, you can use the following end-points to interact with the application:
- GET /todos
- GET /todos/{id}
- POST /todos
- PUT /todos/{id}
- DELETE /todos/{id}
- PUT /todos/completed/{id}

you may test the end-points using Postman using the Collection in the Postman-folder.
The collection contains examples of how to use the end-points, but you may also read the documentation in the link.


To add a screenshot, create an assets/images folder in your repository and upload your screenshot to it. Then, using the relative filepath, add it to your README using the following syntax:

```md
![alt text](assets/images/screenshot.png)
```
### Credits
List your collaborators, if any, with links to their GitHub profiles.

member 1
member 2
If you used any third-party assets that require attribution, list the creators with links to their primary web presence in this section.

junit jupiter 5
If you followed tutorials, include links to those here as well.

### License
The last section of a high-quality README file is the license. This lets other developers know what they can and cannot do with your project. If you need help choosing a license, refer to MIT License.


### Features
the end-points allow for the following:
- get all todos
- get a single todo
- create a todo
- update a todo
- delete a todo
- toggle a todo as done or not done


### Tests
Go the extra mile and write tests for your application. Then provide examples on how to run them here.
