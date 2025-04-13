<<<<<<< HEAD
# Pet Shelter Management Backend: API Documentation

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
Only the admin can create, update or delete a pet. Users can only view the pets (filtered such that they only see those who are not adopted yet)  


### `GET /v1/pets
**Description**: This request retrieves all pets from the database. The pets can be filtered by name, species and the boolean 'isAdopted'.

![Image](https://github.com/user-attachments/assets/1349c752-5654-4727-ae80-300fbf7b3b39)  

Filtered pets by species (works similarly for filtering after name or the boolean 'isAdopted' and also, filter criteria can be combined)    

![Image](https://github.com/user-attachments/assets/c7f14747-86a7-43bd-8c10-cd979da2a1a5)  


### `POST /v1/pets  
**Description**: This endpoint creates a new pet - only the admin is authorized to post a new pet.

Post a pet as admin  

![Image](https://github.com/user-attachments/assets/3d006726-f91e-4fdc-84d1-30cf0eae0edc)

Post pet as user

![Image](https://github.com/user-attachments/assets/9f0ad970-103f-46d0-bbdb-0f58e0117d70)

If the pet name is already taken

![Image](https://github.com/user-attachments/assets/854cb433-736b-4e1b-9a59-f58cd99b5059)


### `PUT /v1/pets/{id}
**Description**: This endpoint updates a pet - only the admin is authorized to update a pet.  

![Image](https://github.com/user-attachments/assets/e2b847ac-0720-4456-9406-c81500de0739)

### `DELETE /v1/pets/{id}
**Description**: This endpoint deletes a pet - only the admin is authorized to delete a pet.  

![Image](https://github.com/user-attachments/assets/6b0513d1-a504-43ee-972b-650060452f5b)  


## Adoptions
Only users can adopt pets. Each pet can be adopted only once. The admin can view or update an adoption's status by accepting it.  

### `POST /v1/adoptions  
**Description**: This endpoint makes an adoption request (creates a new adoption with status "pending")  

Example: User (user111@gmail.com) makes an adoption request for the pet named Rex:  

![Image](https://github.com/user-attachments/assets/7bcf41e4-2dc3-490c-b3b3-bc3e12b30176)  

Then, if some user wants to adopt the same pet again:  

![Image](https://github.com/user-attachments/assets/02223157-3b9c-48f9-aac9-bce3a8bbe6b6)  


### `PUT /v1/adoptions/{id}  
**Description**: This endpoint is used by an admin to update and adoption status from the default "pending" to "accepted". This will set the adoption's timestamp to the current timestamp.  

![Image](https://github.com/user-attachments/assets/6fc9f644-b983-46ad-ac39-1e9a5db5c792)  

Next, a job checks at a certain interval all adoptions and based on their timestamp, they are deleted (if an adoption is older than a certain treshold that adoption is deleted along with the pet it references.  

So after the time treshold, the adoption is deleted and if the admin fetches all adoptions again:  

![Image](https://github.com/user-attachments/assets/cfcb9a49-4a4f-451c-a3c3-f06e8802c66a)  

Cleanup logs:  

![Image](https://github.com/user-attachments/assets/123584b6-00bf-470b-9320-79269b3fd9e2)

Also the pet is deleted, after its adoption was deleted (Rex is no longer in the database):  

![Image](https://github.com/user-attachments/assets/b132de13-d97b-4c82-a4f9-d534f29ede40)  


Database:  

![Image](https://github.com/user-attachments/assets/b87b43f0-b0fc-4dbd-bac1-9f2c5cc8c0af)  

The endpoints that were not exemplified follow the same pattern as seen in the previous examples and provide basic CRUD operations.
