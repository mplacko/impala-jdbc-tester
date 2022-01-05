# impala-jdbc-tester
Impala JDBC Java based tester - Basic Authentication

### HOW TO CONFIGURE THE PROJECT
- `impala-jdbc-tester\src\main\resources\ClouderaImpalaJdbcTester.conf`
```sh
connection.url = jdbc:impala://impala-load-balancer:21050;AuthMech=3;UID=<REPLACE>;PWD=<REPLACE>;SSL=1;AllowSelfSignedCerts=1
```