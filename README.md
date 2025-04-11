# Project Name: API Documentation

## Introduction

For the pet shelter application there are endpoints for CRUD operations on pets, species, donations and adoptions. The most important and interesting resources are pets and adoptions. The other ones mostly implement basic operations and they all follow the same template.

---

## Auth

### `POST auth/login
**Description**: This is the endpoint for login. If successful, the user receives an access token.  

![Image](https://github.com/user-attachments/assets/f9c2de0c-2eca-4d1d-b6e5-b5214efa2005)

![Image](https://github.com/user-attachments/assets/dc42c2f3-db4a-48af-8a53-2f0f1aadf99b)

![Image](https://github.com/user-attachments/assets/bc35ed15-5406-48e3-9429-ab9e9ddaf556)


## Pets
Only the admin can create, update or delete a pet. Users can only view the pets that are not adopted yet.  


### `GET /v1/pets
**Description**: This request retrieves all pets from the database. The pets can be filtered by name, species and the boolean 'isAdopted'.

![Image](https://github.com/user-attachments/assets/1349c752-5654-4727-ae80-300fbf7b3b39)  

![Image](https://github.com/user-attachments/assets/c7f14747-86a7-43bd-8c10-cd979da2a1a5)  


### `POST /v1/pets  
Post a pet as admin  

![Image](https://github.com/user-attachments/assets/3d006726-f91e-4fdc-84d1-30cf0eae0edc)

Post pet as user

![Image](https://github.com/user-attachments/assets/9f0ad970-103f-46d0-bbdb-0f58e0117d70)




