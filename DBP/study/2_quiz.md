# 문제 범위
Chapter 6. JDBC Programming   (+ SQL)  
  
# 민희 문제
### Q1
Statement 객체를 생성하고, 그것으로 DELETE문을 실행하는 코드를 작성하시오.  
(함수 실행 시 반환하는 값이 있다면 이를 저장하는 코드도 있어야함)  
#### ✏ 풀이 
```sql
Statement stmt = conn.createStatement();
int recordCount = stmt.executeUpdate(query);
```
  
#### 📄 답 
```sql
Statement stmt = conn.createStatement();
int recordCount = stmt.executeUpdate(query);
```
<hr>
   
### Q2
Statement 객체를 통해 쿼리 실행 결과로 반환되는 집합을 저장하기 위해 (1)____ 객체를 사용하고  
이를 (2)__가 가리키도록 한다. (2)____는 가장 처음에 (3)__를 가리키고 있고, (4)___를 통해 다음 행으로 이동시킨다.  

#### ✏ 풀이
(1) ResultSet  
(2) 커서 ??  
(3) 첫 번째 행  
(4) next()  
  
#### 📄 답
(1) ResultSet  
(2) cursor  
(3) 첫번째 행의 앞부분  
(4) rs.next()  
  
<hr>
   
### Q3~Q4    
실습#3과 동일한 sql 스크립트를 실행하여 EMPS와 DEPTS 테이블을 생성했을 때, 사원번호와 수당을 매개변수로 받아서 해당 사원번호의 수당을 변경하는 코드를 작성하시오. 
?를 사용하는 방식이 **Q3**, ?를 사용하지 않는 방식의 **Q4**이다.
### Q3
```java
public static void replaceManagerOfDept(int empNo, double comm) {
Connection conn = null;
(1)___ pstmt = null;
String query = "(2)____";
conn = getConnection();
try {
pstmt = conn.(3)__(query);
(4)___쿼리에 값을 입력하는 부분_;
(5)____쿼리를 실행시키는 부분___;
/ 생략 /
}
```
  
#### ✏ 풀이
(1) PreparedStatement  
(2) UPDATE emps SET comm = ? WHERE empno = ?  
(3) prepareStatement  
(4)  
```java
pstmt.setDouble(1, comm);
pstmt.setInt(2, empNo);
```
  
#### 📄 답  
(1) PreparedStatement  
(2) UPDATE EMPS SET comm = ? WHERE empno = ?;  
(3) prepareStatement  
(4) pstmt.setDouble(1, comm); pstmt.setInt(2, empNo);  
(5) pstmt.executeUpdate();  
  
<hr>
    
Q4.
```java
public static void replaceManagerOfDept(int empNo, double comm) {
/ 생략 /
(1)__ stmt = conn.(2)___;
String query = "(3)_"
(4)____쿼리를 실행시키는 부분_);
/ 생략 /
}
```
  
#### ✏ 풀이
(1) Statement
(2) createStatement()
(3) "UPDATE emps SET comm = " + comm + " WHERE empno = " + empNo; 
(4)  
```java
stmt.executeUpdate(query);
```
  
#### 📄 답  
(1) Statement  
(2) createStatement()  
(3) UPDATE DEPTS SET comm = " + comm + " WHERE empno = " + empNo;  
(4) stmt.executeUpdate(query)  
  
<hr>
    
 
