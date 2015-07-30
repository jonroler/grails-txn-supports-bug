# grails-txn-supports-bug
Sample grails application that demonstrations an apparent grails bug with transactions that use supports propagation with multiple data sources.  The problem occurs when a call is made to a service method called with the "supports" propagation annotation on it followed by a call to a service method with the default ("required") transaction propagation.  The problem only appears to occur when multiple data sources are defined.

The problem can be replicated on grails 3.0.4, 2.5.1, and 2.5.0.  I cannot replicate the problem on grails 2.1.0.  I haven't tried any other grails versions.

To replicate, close this project, cd into the grails-txn-supports-bug directory, and run the app with 'grails run-app'. Then use a browser and navigate to http://localhost:8080/test/index.  You should get an error page with this error:

URI: /test/index

Class java.lang.IllegalStateException

Message: null

Caused by: Already value [org.springframework.jdbc.datasource.ConnectionHolder@4d356d09] for key [org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy@6f8682d8] bound to thread [http-nio-8080-exec-1]

and this stack trace:

    Line | Method
->> 1142 | runWorker in java.util.concurrent.ThreadPoolExecutor
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
|    617 | run       in java.util.concurrent.ThreadPoolExecutor$Worker
^    745 | run . . . in java.lang.Thread

Caused by CannotCreateTransactionException: Could not open Hibernate Session for transaction; nested exception is java.lang.IllegalStateException: Already value [org.springframework.jdbc.datasource.ConnectionHolder@4d356d09] for key [org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy@6f8682d8] bound to thread [http-nio-8080-exec-1]; nested exception is org.springframework.transaction.CannotCreateTransactionException: Could not open Hibernate Session for transaction; nested exception is java.lang.IllegalStateException: Already value [org.springframework.jdbc.datasource.ConnectionHolder@4d356d09] for key [org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy@6f8682d8] bound to thread [http-nio-8080-exec-1]
->>    8 | index     in TestController.groovy
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
|   1142 | runWorker in java.util.concurrent.ThreadPoolExecutor
|    617 | run . . . in java.util.concurrent.ThreadPoolExecutor$Worker
^    745 | run       in java.lang.Thread

Caused by CannotCreateTransactionException: Could not open Hibernate Session for transaction; nested exception is java.lang.IllegalStateException: Already value [org.springframework.jdbc.datasource.ConnectionHolder@4d356d09] for key [org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy@6f8682d8] bound to thread [http-nio-8080-exec-1]
->>    8 | index     in TestController.groovy
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
|   1142 | runWorker in java.util.concurrent.ThreadPoolExecutor
|    617 | run . . . in java.util.concurrent.ThreadPoolExecutor$Worker
^    745 | run       in java.lang.Thread

Caused by IllegalStateException: Already value [org.springframework.jdbc.datasource.ConnectionHolder@4d356d09] for key [org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy@6f8682d8] bound to thread [http-nio-8080-exec-1]
->>    8 | index     in TestController.groovy
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
|   1142 | runWorker in java.util.concurrent.ThreadPoolExecutor
|    617 | run . . . in java.util.concurrent.ThreadPoolExecutor$Worker
^    745 | run       in java.lang.Thread

