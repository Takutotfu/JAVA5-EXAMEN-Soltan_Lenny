# Technical Documentation

## Table of Contents
1. [Introduction](#1-introduction)
2. [Endpoint Definitions](#2-endpoint-definitions)
   - BiblioManager
   - Endpoint Details

---

## 1. Introduction
This documentation describes the technical workings of a project consisting of one API BiblioManager. This project has a simple flux that allow to create/read/update/delete (CRUD) a Livre and an Auteur.

---

## 2. Endpoint Definitions

### <u>BiblioManager</u>
#### LivreController endpoints:
| URL                       | Request Type  | Functionality                             |
|---------------------------|---------------|-------------------------------------------|
| [`/api/livres`](#playercontroller)        | POST          | Create a Livre                          |
| [`/api/livres`](#playercontroller)        | GET           | Retrieve all Livres                     |
| [`/api/livres/{id}`](#playercontroller)   | GET           | Retrieve a Livre by ID                  |
| [`/api/livres/{id}`](#playercontroller)   | PUT           | Update a Livre                          |
| [`/api/players/{id}`](#playercontroller)   | DELETE        | Delete one Livre                          |

#### AuteurController endpoints:
| URL                       | Request Type  | Functionality                             |
|---------------------------|---------------|-------------------------------------------|
| [`/api/auteurs`](#friendcontroller)        | POST          | Create an Auteur                            |
| [`/api/auteurs/{auteurId}`](#friendcontroller)        | GET           | Retrieve an Auteur profile                       |
| [`/api/auteurs`](#friendcontroller)        | GET        | Retrieve a list of all Auteurs                      |

## Endpoint Details

### LivreController

#### 2.1 `/api/livres` (POST)
- **Description**: Adds a new Livre.
- **Request Type**: POST
- **Parameters**:
  - Request Body:
```json
{
  "titre": "Test",
  "genre": "test",
  "auteurId": 1
}
```
- **Response**:
```json
{
  "id": 1,
  "titre": "Test",
  "genre": "test",
  "auteurId": 1
}
```

#### 2.2 `/api/livres` (GET)
- **Description**: Retrieves the list of all livres.
- **Request Type**: GET
- **Parameters**: None
- **Response**:
```json
[
  {
  "id": 1,
  "titre": "Test",
  "genre": "test",
  "auteurId": 1
  },
  {
  "id": 2,
  "titre": "Test",
  "genre": "test",
  "auteurId": 1
  }
]
```

#### 2.3 `/api/livres/{id}` (GET)
- **Description**: Retrieves details of a specific livre.
- **Request Type**: GET
- **Parameters**:
  - `id` (path): Unique identifier of the livre.
- **Response**:
```json
{
  "id": 1,
  "titre": "Test",
  "genre": "test",
  "auteurId": 1
}
```

#### 2.4 `/api/livres/{id}` (PUT)
- **Description**: Updates a livre by their unique identifier.
- **Request Type**: PUT
- **Parameters**:
  - `id` (path): Unique identifier of the player.
  - Request Body:
```json
{
  "titre": "Test_updated",
  "genre": "test_updated"
}
```
- **Response**:
```json
{
  "id": 1,
  "titre": "Test_updated",
  "genre": "test_updated",
  "auteurId": 1
}
```

#### 2.5 `/api/livres/{id}` (DELETE)
- **Description**: Deletes a livre by their unique identifier.
- **Request Type**: DELETE
- **Parameters**:
  - `id` (path): Unique identifier of the player.
- **Response**: No content (204).

#### AuteurController

#### 2.6 `/api/auteurs` (POST)
- **Description**: Creates an Auteur.
- **Request Type**: POST
- **Parameters**:
  - Request Body:
```json
{
  "nom": "Doe",
  "prenom": "John"
}
```
- **Response**:
```json
{
  "id": 1,
  "nom": "Doe",
  "prenom": "John"
}
```

#### 2.7 `/api/auteurs` (GET)
- **Description**: Get a list of all Auteurs.
- **Request Type**: GET
- **Parameters**: None
- **Response**:
```json
[
  {
  "id": 1,
  "nom": "Doe",
  "prenom": "John"
  },
  {
  "id": 2,
  "nom": "Doe",
  "prenom": "Jane"
  }
]
```

#### 2.8 `/api/auteurs/{auteurId}` (GET)
- **Description**: Get an Auteur profile.
- **Request Type**: GET
- **Parameters**:
  - `auteurId` (path): Unique identifier of the player.
- **Response**:
```json
{
  "id": 2,
  "nom": "Doe",
  "prenom": "Jane"
  "livres": [
    {
    "id": 1,
    "titre": "Test",
    "genre": "test",
    "auteurId": 1
    },
    {
    "id": 2,
    "titre": "Test",
    "genre": "test",
    "auteurId": 1
    }
  ]
}
```

This documentation is designed to guide developers in understanding and extending the application.
