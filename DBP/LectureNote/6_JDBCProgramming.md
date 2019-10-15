# JDBC

## JDBC (Java Database Connectivity)
- java 애플리케이션에서 DBMS를 연동하기 위한 표준 API  
  - 애플리케이션은 JDBC API를 이용함으로써 DBMS의 종류에 상관없이 동일한 방법으로 DB 이용 가능
- DBMS 접속 및 이용을 위한 interface와 class들을 포함
  - DBMS vendor에서 제공하는 JDBC Driver를 통해 구현됨
<hr> 

## JDBC Driver
- Type 4: Native-Protocol Driver(Thin Driver)  
  - 100% Java로 구현된 JDBC Driver 사용
  - DBMS vendor에서 표준 JDBC API에 따라 구현한 JDBC Driver를 제공
<hr>

## JDBC API
- 다음과 같은 두 개의 package로 구성됨
  - java.sql package
    - Data source (일반적으로 relational DB)에 저장된 데이터를 접근하고 처리하기 위한 API를 제공
      - Class: DriverManager
      - Interface: Connection, Statement, PreparedStatement, CallableStatement, ResultSet 등
  - javax.sql package
    - 서버에 존재하는 data source를 접근하고 이용하기 위한 API를 제공
      - Interface: XADataSource 등
<hr>
  
## JDBC API 사용 절차
- 순서에 맞춰 코드 작성!
![20191013_110643657_iOS](https://user-images.githubusercontent.com/50271884/66714734-7196f600-edf5-11e9-8b1a-95fad4b6ff63.png)
<hr>

  
## 1. JDBC Package Import
- JDBC Driver 사용 준비 (넷 중에 하나 설치)
  - JDK에 설치
    - <JDK 설치폴더>/jre/lib/ext에 복사
  - Tomcat 서버에 설치
    - <Tomcat 설치폴더>/lib에 복사
  - 웹 애플리케이션에 포함
    - Java EE Web Application의 경우 WEB-INF/lib 폴더에 포함
  - 일반 Java Application은 JDBC driver 파일을 classpath(build path)에 포함시켜야 실행 가능
    - Eclipse project의 Properties - Java Build Path의 Libraries tab - Add JARs 
      or
      Add External JARs - JDBC Driver 파일 선택
- Package Import
  - Java 프로그램 내에서 java.sql. package import
<hr>

## 2. JDBC Driver Loading 및 등록
- **Class.forName(CLASS_NAME)**
  - CLASS_NAME의 이름을 갖는 클래스를 메모리에 로딩
  - CLASS_NAME은 프로그램에서 변경 가능 ∴ 동적으로 특정 클래스 타입의 객체를 생성할 수 있음
    > Class.forName("oracle.jdbc.driver.OracleDriver"); // CLASS_NAME이 PATH
<hr>
  
## 3. DBMS와의 연결 획득  
- ① DriverManager.getConnection() 이용  // 로그인 성공 후 Connection 객체 return  
  > Connection conn = DriverManager.getConnection(url, user, passwd);
  - url
    - JDBC를 사용하여 접속할 DBMS 서버의 주소 표현
    - 형식: jdbc:[DBMS서버주소:포트번호]:[데이터베이스식별자]
    - ex
      - jdbc:oracle:thin:@dbserver.dongduk.ac.kr:1521:orcl (Oracle)
      - jdbc:mysql://dbserver.dongduk.ac.kr:3306/sampleDB (MySQL)
  - user, passwd
    - DBMS에 접근할 때 사용할 사용자 계정의 이름과 password

- ② **javax.sql.DataSource** Interface 이용  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //*실제 우리가 쓸 방법*
  - 하나의 물리적인 data source를 나타내며, 그것에 대한 연결(connection)을 생성하는 **factory**기능 수행
  - ① 방식의 대안: connection을 얻기 위한 더 좋은 방법
  - 일반적으로 Application Server(ex. Tomcat)에서 클래스 구현 및 객체 생성
    - Database connection pooling 기능 지원
    - Java Naming and Directory Interface(JNDI) 서비스를 통해 DataSource 객체를 제공
  - 애플리케이션에서는 JNDI 서비스를 검색(lookup)하여 DataSource 객체를 획득 및 사용  
<hr>
  
## 4. Statement 객체 생성
- **Connection.createStatement()**
  > Statement stmt = conn.createStatement();
  - 정적인 SQL문을 실행하고 그 결과를 반환하기 위해 사용되는 객체  
    // 정적인 SQL문: 바로 실행가능한 완전한 SQL
<hr>

## 5. Statement 객체를 이용하여 SQL문 실행
- **SELECT**문 실행
  > ResultSet rs = stmt.executeQuery(query)
  - **query**: 실행할 SQL 질의문
  - **ResultSet** 타입 객체를 반환
    
- **INSERT, UPDATE, DELETE**문 실행
  > int recordCount = stmt.executeUpdate(query)
  - **query**: INSERT문, UPDATE문, DELETE문
  - **삽입/변경/삭제된 행의 개수**를 반환
<hr>
  
## 6. DBMS 응답 사용
- **ResultSet**
  - Statement.executeQuery()의 실행 결과로 반환되는 행들의 집합을 저장
  - *내부적으로 커서를 사용하여* 결과 행들을 순차적으로 접근
  
- **ResultSet.next()**  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;// while이나 for문 사용!
  - ResultSet에 저장된 행들을 커서가 순서대로 가리키도록 함
    - 최초 호출 시, 첫 번째 행을 가리키고 이후 호출될 때마다 커서를 다음 행으로 이동!
  - *더 이상 가리킬 행이 없으면 false를 반환*
  
- ResultSet에서 현재 커서 위치의 컬럼 값 일기
  - ResultSet객체를 rs가 참조한다고 가정
  ![20191013_115521485_iOS](https://user-images.githubusercontent.com/50271884/66715276-78753700-edfc-11e9-8b7a-2628a309edd8.png)  
  - rs.getString(n)과 같이 숫자를 쓸 경우, 질의의 SELECT절에 지정된 n번째 컬럼 값을 반환  
<hr>
  
## 7. 자원 반납
- DB 작업이 끝나면 각 자원을 반드시 반환해야 함!
  ```java
  Connection conn;
  Statement stmt;
  ResultSet rs;
  
  ...
  
  if (rs != null) {   // ResultSet 객체 존재
    try { rs.close(); }   // rs == null이면 rs.close()할 때, NullPointException 발생
    catch(SQLException ex) { ... }
  }
  if (stmt != null) {   // Statement 객체 존재
    try { stmt.close(); }
    catch(SQLException ex) { ... }
  }
  if (conn != null) {   // Connection 객체 존재
    try { conn.close(); }
    catch(SQLException ex) { ... }
  }
  ```
  - stmt와 conn이 생성 안 될 수도 있음!!
  - 메모리에서 삭제될 수 있게. ← Java는 Garbage Collector들이 있음
<hr>
  
## JDBC Program Example
- JdbcTest.java  
![20191013_122827106_iOS](https://user-images.githubusercontent.com/50271884/66715639-8af16f80-ee00-11e9-9e2d-34064da60b8d.png)
       
  ![20191013_122915659_iOS](https://user-images.githubusercontent.com/50271884/66715833-ffc5a900-ee02-11e9-86b7-c2beceda0589.png)
<hr>
  
## JDBC에서의 SQL문 사용
- Statement
  - 일반적인 SQL문 사용 시
  - SQL문을 수행할 때마다 아래의 과정을 반복
    - SQL 문 compile → compile된 SQL 문 실행 (DBMS 실행) → 실행결과 반환
  - 반복적인 작업 수행 시 DBMS의 부하 증가  
    
- **PreparedStatement**
  - 사전에 compile한 SQL 문 실행
    - 반복적인 compile을 피함으로써 DBMS 부하 감소 효과
  - SQL문 내에 매개변수 사용 가능
  - 매개변수를 제외하고 SQL 문장의 구조가 동일한 경우 사용
  
- CallableStatement
  - SQL Stored Procedure 호출 시 이용
<hr>
  
## Statement 사용 시의 문제점
- ① 동일한 형태의 SQL문이 반복적으로 컴파일 됨
  ```sql
  INSERT INTO EMP (EMPNO, ENAME, JOB, DEPTNO)
  VALUES (7950, 'TOM', 'ANALYST', 20);
  ...
  INSERT INTO EMP (EMPNO, ENAME, JOB, DEPTNO)
  VALUES (7960, 'BRED', 'CLERT', 10);
  ```
  - SQL문을 실행할 때마다 DBMS는 SQL문을 컴파일하고 SQL문의 실행을 준비함
- ② 코드 작성 및 가독성 문제
  - 문자열 데이터의 경우 SQL문 내에서 반드시 따옴표(')를 붙여야 함
  ```sql
  String query = "INSERT INTO EMP (EMPNO, ENAME, JOB, DEPTNO)" +
                  "VALUES (" + empNo + ", '" + empName + "', '" + empJob + "', " + deptNo + " )";
  ```
<hr>
  
## PreparedStatement
- PreparedStatement
  - SQL문을 미리 컴파일 한 후 실제 데이터 값은 질의를 실행할 때 지정함
  ![20191013_140150256_iOS](https://user-images.githubusercontent.com/50271884/66716842-8089a280-ee0d-11e9-9b53-c14c42045a39.png)
    
<hr>

## PreparedStatement의 사용
- PreparedStatement 생성
  - Connection 클래스의 prepareStatement() 메소드 사용
  ![20191013_140445371_iOS](https://user-images.githubusercontent.com/50271884/66716969-cc891700-ee0e-11e9-98cb-31bb3ea5468a.png)  
  
- 매개변수 값 지정 및 실행
  - 질의문의 ? 자리에 들어갈 값을 지정 후 실행
  ![20191013_140919158_iOS](https://user-images.githubusercontent.com/50271884/66716971-d0b53480-ee0e-11e9-9a96-43a7bfa26118.png)

<hr>
  
## PreparedStatement의 set methods
- 개요
  - 질의에 포함된 매개변수 값을 입력
  - **?의 순서에 따라 1부터 시작하는 인덱스**를 가짐 (1씩 증가)
  - 질의문 상에서 값에 해당하는 부분만 대치 가능
    - SELECT * FROM ?   // 테이블 이름은 매개변수로 대치 불가
  - 매개변수의 개수와 값을 설정하는 set method 호출의 개수가 일치해야 함
    - 틀릴 경우 SQLException 발생
- 형식
  > PreparedStatement.**setXXX** (*INDEX_NO, VALUE*);
  - XXX: Data Type
  - INDEX_NO: 매개변수 위치 (1부터 시작)
  - VALUE: 매개변수의 값
<hr>
  
## PreparedStatement의 set methods
![20191013_140919158_iOS](https://user-images.githubusercontent.com/50271884/66716971-d0b53480-ee0e-11e9-9a96-43a7bfa26118.png)

<hr>
  
## PreparedStatement 사용 예
![20191013_142055497_iOS](https://user-images.githubusercontent.com/50271884/66717092-30600f80-ee10-11e9-84cf-1b5dae4ab4c7.png)

<hr>
  
