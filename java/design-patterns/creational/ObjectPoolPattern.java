/*

mechanism to reuse objects that are expensive to create. 

Intent
-reuse and share objects that are expensive to create.

Implementation:
Reusable - Wraps the limited resource, will be shared by several clients for a limited amount of time.
Client - uses an instance of type Reusable.
ReusablePool - manage the reusable objects for use by Clients, creating and managing a pool of objects.

client asks for reusable obj..
pool checks if it has any and returns..
else returns new obj of reusable..
client should release the reusable obj..

When? 
there are several clients who needs the same stateless resource which is expensive to create.

reusable pool can restrict num of obj that can be created.. if obj creation fails, client should be notified

reusable pool should be singleton and should be accessible only to reusable class

*/