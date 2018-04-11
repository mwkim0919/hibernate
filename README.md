# Hibernate Tutorial
### Transaction Management:
* ACID
  * **A: Atomicity** - All transactions either succeed or fail
  * **C: Consistency** - Leaving the system in a consistent state in respect of whether a transaction succeeds or fails
  * **I: Isolation** - Multiple levels of isoltation present 
    (e.g while a transaction is updating a row, should other transactions running in parallel be able to see the row being updated?)
  * **D: Durability** - After a transaction is completed execution then even if the system crashes or something wrong happens, the data should be persisted
  
* Read Errors
  * **Dirty Read**: A transaction reading a value before the value gets updated by another transaction
  * **Non Repeatable Read**: A transaction reading the same value multiple time but the value being read is different each time
  * **Phanthom Read**: A transaction having a different number of results at different times.

* 4 Isolation Levels ("possible" mean it is possible to happen)
  * **Read Uncommitted**: locking the value being updated
  * **Read Committed**: locking the values being updated or read (if a value is being read, no other transaction cannot update the value  until the read is done)
  * **Serializable**: locking any results from contraints (WHERE clause) - TABLE LOCK

|               | **Dirty Read** | **Non-repeatable Read** | **Phanthom Read** | **Performance** |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **Read Uncommitted** | Possible | Possible | Possible | Super |
| **Read Committed** | Solved  | Possible | Possible | Good |
| **Repeatable Read** | Solved  | Solved | Possible | Okay |
| **Serializable** | Solved  | Solved | Solved | Bad |
