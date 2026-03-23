# Character CRUD API - Spring Boot Demo

REST API for managing character records using Spring Boot, Spring Data JPA, and PostgreSQL

## Table of Contents

- [Installation](#installation)
- [Run the App](#run)
- [API Endpoints](#api-endpoints)
- [Demo Video](#demo-video)

---

## Installation

### Prerequisites

- Java 25
- Maven Wrapper (`mvnw` on Mac/Linux and `mvnw.cmd` on Windows)

### Setup

1. Clone this repository
2. Open the project folder in VS Code
3. Install dependencies and build:

 **On Windows**:

   ```cmd
   mvnw.cmd clean install
   ```

   **On Mac/Linux**:

   ```bash
   ./mvnw clean install
   ```

---

## Run

### VS Code

1. Open `CurdApiApplication.java`
2. Click **Run --> Start Debugging**

---

## API Endpoints

Base path: `http://localhost:8080/api/characters`

### Character fields

- `characterId` (Long, auto-generated)
- `name` (String, required)
- `description` (String, required)
- `universe` (String, required)
- `species` (String, required)

### 1) Get all characters

`GET /api/characters`

### 2) Get character by ID

`GET /api/characters/{id}`

- `200 OK` if found
- `404 Not Found` if missing

### 3) Create character

`POST /api/characters`

Example body:

```json
{
  "name": "Batman",
  "description": "Dark Knight",
  "universe": "DC",
  "species": "Human"
}
```

### 4) Update character

`PUT /api/characters/{id}`

### 5) Delete character

`DELETE /api/characters/{id}`

- `204 No Content` if deleted
- `404 Not Found` if missing

### 6) Get by category

`GET /api/characters/category/{category}?value={value}`

- Supported categories: `universe`, `species`
- Invalid category returns `400 Bad Request`

### 7) Search by name

`GET /api/characters/search?name=substring`

## Demo Video

https://uncg-my.sharepoint.com/:v:/g/personal/t_shaker_uncg_edu/IQDplQBd5aeySq_QdrqDztu8AacKKnfHKmLQ_q2tNu1z8HM?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=3RWhly