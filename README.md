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
