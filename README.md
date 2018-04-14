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

### Caching with Hibernate & JPA
* **1st Level Cache:** Data cached for single transaction
* **2nd Level Cache:** Data cached for multiple transactions

| Transaction 1 | Transaction 2 | Transaction 3 | Transaction 4 |
| ------------- | ------------- | ------------- | ------------- |
| PersistenceContext1(FLC) | PersistenceContext2(FLC) | PersistenceContext3(FLC) | PersistenceContext4(FLC) |
| Second Level Cache | Second Level Cache | Second Level Cache | Second Level Cache |
| Database | Database | Database | Database |

### JPA Entity Life Cycle
* **PostLoad:** called after an entity is retrieved and loaded
* **PostPersist:** called after an entity is persisted into DB
* **PostRemove:** called after an entity is deleted from DB
* **PostUpdate:** called after an entity is updated
* **PrePersist:** called before an entity is persisted
* **PreRemove:** called before an entity is deleted
* **PreUpdate:** called before an entity is updated

### Performance Tuning Tips
* DONALD KNUTH
  * "We should forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil."
* INDEXES
* USE APPROPRIATE CACHING
  * 1st level Caching - Don't make this too big
  * 2nd level Caching
  * Distributed Cache
  * Be careful about the size of 1st level Cache
* EAGER VS LAZY FETCH
  * Use Lazy fetching mostly
  * Remember that all mapping ToOne (*@ManyToOne and @OneToOne*) are EAGER by default
* N+1 PROBLEM
  * Entity Graph & Named Entity Graphs & Dynamic Entity Graphs
  * Join Fetch Clause
