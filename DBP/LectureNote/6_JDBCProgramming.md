# JDBC
## JDBC (Java Database Connectivity)
- java 애플리케이션에서 DBMS를 연동하기 위한 표준 API  
  - 애플리케이션은 JDBC API를 이용함으로써 DBMS의 종류에 상관없이 동일한 방법으로 DB 이용 가능
- DBMS 접속 및 이용을 위한 interface와 class들을 포함
  - DBMS vendor에서 제공하는 JDBC Driver를 통해 구현됨
  
## JDBC Driver
- Type 4: Native-Protocol Driver(Thin Driver)  
  - 100% Java로 구현된 JDBC Driver 사용
  - DBMS vendor에서 표준 JDBC API에 따라 구현한 JDBC Driver를 제공

