# API Documentation for Virtual Learning System

# Project Overview - Virtual Learning System

## Overview

The Virtual Learning System is designed to facilitate education through efficient management of users, classes, challenges, sessions, and school-related operations. It provides a comprehensive API that allows administrators, teachers, and students to interact with the system, making learning and administrative tasks seamless.

### Base URL

```
http://localhost:8080
```

## Key Features

- **User Management**: Create, update, deactivate, and list users of different types: students, teachers, and administrators.
- **School Management**: Manage classes, assign teachers, and track student enrollments within the school.
- **Challenge Management**: Create and assign challenges to students, track progress, and manage challenge sessions.
- **Session Management**: Create and manage learning sessions that include exercises and content for students.
- **Authentication**: Authenticate users and manage login.
- **Student Competence Management**: Track and manage student competences for specific skills.
- **Exercise Management**: Retrieve and manage exercises along with their tools and modifiers.

## User Management

### Overview
The system includes user management features that allow administrators to create, update, and deactivate users. This ensures the flexibility needed for educational institutions to keep user information current and maintain system security.

### Endpoints
- **POST `/users/{userType}`**: Create a user (student, teacher, administrator).
- **PUT `/users/{userType}/{id}`**: Update user details.
- **GET `/users/{userType}/{id}`**: Get details of a specific user.
- **GET `/users/{userType}`**: List all users of a specific type.
- **PUT `/users/{userType}/{id}/deactivate`**: Deactivate a specific user.

## School Management

### Overview
The School Management API provides features to manage classes and teacher-student relationships. This includes creating classes, assigning teachers, and changing student enrollments.

### Endpoints
- **GET `/school/classes`**: Retrieve all classes.
- **GET `/school/teacher/{teacherId}/classes`**: Get all classes taught by a specific teacher.
- **GET `/school/class/{classId}/students`**: Get all students in a class.
- **PUT `/school/students/changeClass`**: Change a student's class.
- **PUT `/school/classes/changeTeacher`**: Assign a new teacher to a class.
- **POST `/school`**: Create a new class.
- **DELETE `/school/{id}`**: Delete a class.

## Challenge Management

### Overview
Challenges are a crucial part of the Virtual Learning System. They help in engaging students and assessing their progress. Teachers can create challenges that students can participate in, and these challenges are tracked within the system.

### Endpoints
- **POST `/challenges/create`**: Create a new challenge.
- **GET `/challenges/{challengeId}`**: Retrieve details of a specific challenge.
- **GET `/challenges/teacher/{teacherId}`**: Get all challenges created by a specific teacher.
- **GET `/challenges/student/{studentId}`**: Get all challenges assigned to a student.
- **GET `/challenges/session/{sessionId}`**: Retrieve details of a specific challenge session.

## Session Management

### Overview
Sessions are used to group exercises and content for students, providing a structured learning experience. The API allows creating, retrieving, and managing these sessions effectively.

### Endpoints
- **POST `/sessions`**: Create a new session.
- **GET `/sessions/{id}`**: Get details of a specific session.

## Authentication

### Overview
The Authentication API provides endpoints to authenticate users, allowing them to access the system with appropriate permissions.

### Endpoints
- **POST `/auth/login`**: Authenticate a user and return relevant user information based on the user type.

## Student Competence Management

### Overview
The Student Competence Management API allows tracking student competences for specific skills. This helps in understanding student strengths and areas of improvement.

### Endpoints
- **GET `/api/student-competences/student/{studentId}`**: Retrieve the competences of a specific student.

## Exercise Management

### Overview
The Exercise Management API provides endpoints to manage exercises, including their tools and modifiers, helping to create engaging learning experiences for students.

### Endpoints
- **GET `/api/exercises`**: Retrieve a list of all exercises with their tools and modifiers.
- **GET `/api/exercises/get-names`**: Retrieve the names of specific exercises based on their IDs.

## How to Use

To use the Virtual Learning System, developers can interact with the above endpoints to integrate the API functionalities into their own systems or applications. The API follows RESTful conventions, making it easy to use and integrate with modern web applications.

### Example Use Cases
- **Administrators**: Create new classes, assign teachers, and manage users.
- **Teachers**: Create challenges, view assigned classes, and track student progress.
- **Students**: Participate in challenges, view class information, and track their own progress.

## Getting Started

1. Set up the API server using the provided Base URL.
2. Authenticate as an administrator to create users and classes.
3. Use the appropriate endpoints to create and manage sessions, challenges, and class relationships.

For detailed information on each endpoint, refer to the respective documentation sections for User Management, School Management, Challenge Management, Session Management, Authentication, Student Competence Management, and Exercise Management.

## Endpoints

### Authentication Endpoints

#### POST `/auth/login`

Authenticates a user and returns relevant user information based on the user type.

##### Request Rules

- **URL**: `/auth/login`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.
    - `email` (string): Must be a valid email address.
    - `password` (string): The user's password.

**Example Request Body**:

```json
{
  "userType": "student",
  "email": "student@example.com",
  "password": "password123"
}
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a JSON object containing user information.
    - For students: includes `userId` and `classId`.
    - For teachers and administrators: includes only `userId`.

**Example Success Response for Student**:

```json
{
  "userId": 1,
  "classId": 101
}
```

**Example Success Response for Teacher or Administrator**:

```json
{
  "userId": 2
}
```

- **Error Response (400 Bad Request)**:
    - If `userType` is invalid: Returns "Invalid user type".
    - If `email` is not found: Returns "Invalid email for [userType]".
    - If `password` is incorrect: Returns "Invalid password for [userType]".


### Exercise Endpoints

#### GET `/api/exercises`

Retrieves a list of all exercises with their tools and modifiers.

##### Request Rules

- **URL**: `/api/exercises`
- **Method**: `GET`

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of exercises, including their details such as tools and modifiers.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "Push-up",
    "description": "A basic upper body exercise",
    "active": true,
    "exerciseToolId": 3,
    "difficulty": "Medium",
    "leftToolId": 5,
    "leftToolName": "Left Dumbbell",
    "rightToolId": 6,
    "rightToolName": "Right Dumbbell",
    "modifierId": 2,
    "modifierName": "Resistance Band"
  }
]
```

#### GET `/api/exercises/get-names`

Retrieves the names of specific exercises based on their IDs.

##### Request Rules

- **URL**: `/api/exercises/get-names`
- **Method**: `GET`
- **Query Parameters**:
    - `exerciseIds` (List of Integers): A list of exercise IDs to retrieve names for.

**Example Request**:

```
/api/exercises/get-names?exerciseIds=1,2,3
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of exercises, including their names and difficulty levels.

**Example Success Response**:

```json
[
  {
    "exercisesId": 1,
    "name": "Push-up",
    "difficulty": "Medium",
    "exerciseToolId": 3,
    "leftToolName": "Left Dumbbell",
    "rightToolName": "Right Dumbbell",
    "modifierName": "Resistance Band"
  }
]
```

- **Error Response (400 Bad Request)**:
    - If any `exerciseId` is not found: Returns "Exercise not found".

### Session Endpoints

#### POST `/sessions`

Creates a new session.

##### Request Rules

- **URL**: `/sessions`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `exercises` (string): The exercises to be included in the session.
    - `difficulty` (string): The difficulty level of the session.
    - `active` (boolean): Whether the session is active.

**Example Request Body**:

```json
{
  "exercises": "Exercise 1, Exercise 2",
  "difficulty": "Hard",
  "active": true
}
```

##### Response Rules

- **Success Response (201 Created)**:
    - Returns the created session information.

**Example Success Response**:

```json
{
  "id": 1,
  "exercises": "Exercise 1, Exercise 2",
  "difficulty": "Hard",
  "active": true,
  "createdAt": "2024-10-25T10:00:00",
  "updatedAt": "2024-10-25T10:00:00"
}
```

#### GET `/sessions/{id}`

Retrieves details of a specific session.

##### Request Rules

- **URL**: `/sessions/{id}`
- **Method**: `GET`
- **Path Parameters**:
    - `id` (Long): The ID of the session to retrieve.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the details of the session.

**Example Success Response**:

```json
{
  "id": 1,
  "exercises": "Exercise 1, Exercise 2",
  "difficulty": "Hard",
  "active": true,
  "createdAt": "2024-10-25T10:00:00",
  "updatedAt": "2024-10-25T10:00:00",
  "deletedAt": null
}
```

- **Error Response (404 Not Found)**:
    - If the `id` is not found: Returns "Session not found".

### Student Competence Endpoints

#### GET `/api/student-competences/student/{studentId}`

Retrieves the competences of a specific student.

##### Request Rules

- **URL**: `/api/student-competences/student/{studentId}`
- **Method**: `GET`
- **Path Parameters**:
    - `studentId` (Long): The ID of the student.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of competences for the student, including the competence name and score.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "competenceName": "Mathematics",
    "studentId": 101,
    "score": 85
  },
  {
    "id": 2,
    "competenceName": "Science",
    "studentId": 101,
    "score": 90
  }
]
```

- **Error Response (400 Bad Request)**:
    - If the `studentId` is not found: Returns "Student not found".


#### User Management Endpoints

##### Overview

This section of the API provides endpoints to manage users within the system. It allows creating, updating, reading, deactivating, and listing users of different types: students, teachers, and administrators.


### POST `/users/{userType}`

Creates a new user of the specified type.

##### Request Rules

- **URL**: `/users/{userType}`
- **Method**: `POST`
- **Path Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `name` (string): The name of the user.
    - `email` (string): Must be a valid email address.
    - `password` (string): The user's password.
    - `classId` (Long, optional for students): The ID of the class for the student.

**Example Request Body**:

```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "classId": 101
}
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the created user's details.

**Example Success Response**:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "active": true
}
```

### PUT `/users/{userType}/{id}`

Updates an existing user of the specified type.

##### Request Rules

- **URL**: `/users/{userType}/{id}`
- **Method**: `PUT`
- **Path Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.
    - `id` (int): The ID of the user to update.
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `email` (string, optional): Updated email address.
    - `password` (string, optional): Updated password.

**Example Request Body**:

```json
{
  "email": "updated.email@example.com",
  "password": "newpassword123"
}
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the updated user's details.

**Example Success Response**:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "updated.email@example.com",
  "active": true
}
```

### GET `/users/{userType}/{id}`

Retrieves details of a specific user.

##### Request Rules

- **URL**: `/users/{userType}/{id}`
- **Method**: `GET`
- **Path Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.
    - `id` (int): The ID of the user to retrieve.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the user's details.

**Example Success Response**:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "active": true
}
```

### GET `/users/{userType}`

Retrieves a list of all users of the specified type.

##### Request Rules

- **URL**: `/users/{userType}`
- **Method**: `GET`
- **Path Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of users.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "active": true
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "active": true
  }
]
```

### PUT `/users/{userType}/{id}/deactivate`

Deactivates a specific user.

##### Request Rules

- **URL**: `/users/{userType}/{id}/deactivate`
- **Method**: `PUT`
- **Path Parameters**:
    - `userType` (string): Must be either `student`, `teacher`, or `administrator`.
    - `id` (int): The ID of the user to deactivate.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the deactivated user's details.

**Example Success Response**:

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "active": false
}
```



#### School Management endpoints

##### Overview

This section of the API provides endpoints to manage school-related data within the system. It allows managing classes and students, as well as assigning teachers to classes.


### GET `/school/classes`

Retrieves a list of all school classes.

##### Request Rules

- **URL**: `/school/classes`
- **Method**: `GET`

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of all classes, including their details.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "Math 101",
    "description": "Basic Mathematics",
    "teacherId": 3,
    "teacherName": "John Doe"
  }
]
```

### GET `/school/teacher/{teacherId}/classes`

Retrieves the classes taught by a specific teacher.

##### Request Rules

- **URL**: `/school/teacher/{teacherId}/classes`
- **Method**: `GET`
- **Path Parameters**:
    - `teacherId` (Long): The ID of the teacher.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of classes taught by the teacher.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "Math 101",
    "description": "Basic Mathematics"
  }
]
```

### GET `/school/class/{classId}/students`

Retrieves a list of students in a specific class.

##### Request Rules

- **URL**: `/school/class/{classId}/students`
- **Method**: `GET`
- **Path Parameters**:
    - `classId` (Long): The ID of the class.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of students in the class.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "active": true
  }
]
```

### PUT `/school/students/changeClass`

Changes a student's class.

##### Request Rules

- **URL**: `/school/students/changeClass`
- **Method**: `PUT`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `studentId` (Long): The ID of the student.
    - `newClassId` (Long): The ID of the new class.

**Example Request Body**:

```json
{
  "studentId": 1,
  "newClassId": 2
}
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the updated student information.

**Example Success Response**:

```json
{
  "id": 1,
  "name": "Jane Smith",
  "email": "jane.smith@example.com",
  "active": true
}
```

### PUT `/school/classes/changeTeacher`

Assigns a new teacher to a specific class.

##### Request Rules

- **URL**: `/school/classes/changeTeacher`
- **Method**: `PUT`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `classId` (Long): The ID of the class.
    - `newTeacherId` (Long): The ID of the new teacher.

**Example Request Body**:

```json
{
  "classId": 1,
  "newTeacherId": 4
}
```

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a message indicating the teacher change was successful, along with the updated details.

**Example Success Response**:

```json
{
  "classId": 1,
  "newTeacherId": 4,
  "message": "Teacher changed successfully"
}
```

### POST `/school`

Creates a new class.

##### Request Rules

- **URL**: `/school`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `name` (string): The name of the class.
    - `description` (string): A brief description of the class.
    - `teacherId` (Long): The ID of the teacher assigned to the class.

**Example Request Body**:

```json
{
  "name": "Physics 101",
  "description": "Introduction to Physics",
  "teacherId": 3
}
```

##### Response Rules

- **Success Response (201 Created)**:
    - Returns the created class details.

**Example Success Response**:

```json
{
  "id": 2,
  "name": "Physics 101",
  "teacherId": 3,
  "teacherName": "John Doe"
}
```

### DELETE `/school/{id}`

Deletes a class.

##### Request Rules

- **URL**: `/school/{id}`
- **Method**: `DELETE`
- **Path Parameters**:
    - `id` (Long): The ID of the class to delete.

##### Response Rules

- **Success Response (204 No Content)**:
    - Indicates that the class was successfully deleted.


## Challenge Management Endpoints

### Overview

This section of the API provides endpoints to manage challenges within the system. It allows creating, reading, and retrieving challenges associated with teachers or students.

### POST `/challenges/create`

Creates a new challenge.

##### Request Rules

- **URL**: `/challenges/create`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Body Parameters**:
    - `challengeSessionId` (Long): The ID of the challenge session.
    - `description` (string): Description of the challenge.
    - `teacherId` (Long): ID of the teacher creating the challenge.
    - `name` (string): Name of the challenge.

**Example Request Body**:

```json
{
  "challengeSessionId": 1,
  "description": "Solve the exercises in the session",
  "teacherId": 3,
  "name": "Math Challenge"
}
```

##### Response Rules

- **Success Response (201 Created)**:
    - Returns the ID of the created challenge.

**Example Success Response**:

```json
{
  "challengeId": 5
}
```

- **Error Response (400 Bad Request)**:
    - If the `challengeSessionId` or `teacherId` is invalid: Returns "Challenge session not found" or "Teacher not found".

### GET `/challenges/{challengeId}`

Retrieves details of a specific challenge.

##### Request Rules

- **URL**: `/challenges/{challengeId}`
- **Method**: `GET`
- **Path Parameters**:
    - `challengeId` (Long): The ID of the challenge to retrieve.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the details of the challenge.

**Example Success Response**:

```json
{
  "challengeSessionId": 1,
  "description": "Solve the exercises in the session",
  "active": true,
  "teacherId": 3
}
```

- **Error Response (404 Not Found)**:
    - If the `challengeId` is not found: Returns "Challenge not found".

### GET `/challenges/teacher/{teacherId}`

Retrieves all challenges created by a specific teacher.

##### Request Rules

- **URL**: `/challenges/teacher/{teacherId}`
- **Method**: `GET`
- **Path Parameters**:
    - `teacherId` (Long): The ID of the teacher.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of challenges created by the teacher.

**Example Success Response**:

```json
[
  {
    "id": 1,
    "name": "Math Challenge",
    "description": "Solve the exercises in the session",
    "challengeSessionId": 1
  }
]
```

- **Error Response (404 Not Found)**:
    - If the `teacherId` is not found: Returns "Teacher not found".

### GET `/challenges/student/{studentId}`

Retrieves all challenges assigned to a specific student.

##### Request Rules

- **URL**: `/challenges/student/{studentId}`
- **Method**: `GET`
- **Path Parameters**:
    - `studentId` (Long): The ID of the student.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns a list of challenges assigned to the student.

**Example Success Response**:

```json
[
  {
    "challengeId": 1,
    "challengeName": "Math Challenge",
    "score": 85
  }
]
```

- **Error Response (404 Not Found)**:
    - If the `studentId` is not found: Returns "Student not found".

### GET `/challenges/session/{sessionId}`

Retrieves details of a specific challenge session.

##### Request Rules

- **URL**: `/challenges/session/{sessionId}`
- **Method**: `GET`
- **Path Parameters**:
    - `sessionId` (Long): The ID of the challenge session to retrieve.

##### Response Rules

- **Success Response (200 OK)**:
    - Returns the details of the challenge session.

**Example Success Response**:

```json
{
  "exercises": "Exercise 1, Exercise 2",
  "difficulty": "Medium",
  "active": true
}
```

- **Error Response (404 Not Found)**:
    - If the `sessionId` is not found: Returns "Challenge Session not found".

