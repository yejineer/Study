# 문제 범위
Chapter 6. JDBC Programming   (+ SQL)  
  
<hr>
  
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
(2) 커서  
(3) 첫 번째 행  
(4) next()  
  
#### 📄 답
(1) ResultSet  
(2) cursor  
(3) 첫번째 행의 앞부분  
(4) rs.next()  
  
<hr>
   
### Q3~Q4    
실습#3과 동일한 sql 스크립트를 실행하여 EMPS와 DEPTS 테이블을 생성했을 때, 사원번호와 수당을 매개변수로 받아서 해당 사원번호의 수당을 변경하는 코드를 작성하시오. ?를 사용하는 방식이 **Q3**, ?를 사용하지 않는 방식의 **Q4**이다.  
  
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
(5)  
```java
pstmt.executeUpdate();
```
 
#### 📄 답  
(1) PreparedStatement   
(2) UPDATE EMPS SET comm = ? WHERE empno = ?;   
(3) prepareStatement  
(4)
```java
pstmt.setDouble(1, comm);
pstmt.setInt(2, empNo); 
```
(5) 
```java
pstmt.executeUpdate();  
```
  
<hr>
    
### Q4.
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
(3) UPDATE emps SET comm = " + comm + " WHERE empno = " + empNo;   
(4)  
```java
stmt.executeUpdate(query);
```
  
#### 📄 답  
(1) Statement  
(2) createStatement()  
(3) UPDATE DEPTS SET comm = " + comm + " WHERE empno = " + empNo;  
(4)
```java
stmt.executeUpdate(query);
```  
  
<hr>
    
 
# 선미 문제
### Q1
아래 빈칸을 채우시오.  
```java
package jdbcTest;
import java.sql.*;  // 1. JDBC 관련 package import

public class JdbcTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 변수 선언 & null 초기화
		    Connection conn = null;
		    PreparedStatement pStmt = null; // PreparedStatement 참조 변수 생성
		    ResultSet rs = null;
		    
		    String url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl", user = "scott", passwd = "TIGER";
		    
		    try {
		      Class.forName("oracle.jdbc.driver.OracleDriver"); // 2. JDBC Driver 로딩 및 등록
		    } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
		    
		    String pattern = "%AR%";
		    
		    try {
		      conn = DriverManager.getConnection(url, user, passwd);  // 3. DBMS와의 연결 획득
		      
		      String query = "SELECT ename, job FROM emp WHERE ename like ?";
		      pStmt = conn.prepareStatement(query); // 4. SQL문을 위한 PreparedStatement 객체 생성
		      pStmt.setString(1, pattern);
		      rs = pStmt.executeQuery();  // 5. PreparedStatement 객체를 사용하여 SQL문 실행
		      
		      while (rs.next()) { // 6. DBMS 응답 사용 (커서를 통해 한 행씩 fetch)
		    	 String ename = rs.getString("ename");
		    	 String job = rs.getString("job");
		    	 System.out.println(ename + " " + job);
		      }
		    } catch (SQLException ex) { 
		    	ex.printStackTrace(); 
		    } finally { // 7. 자원 반납
				if (rs != null) 
					try { 
						rs.close(); 
					} catch (SQLException ex) { ex.printStackTrace(); }
				if (pStmt != null) 
					try { 
						pStmt.close(); 
					} catch (SQLException ex) { ex.printStackTrace(); }
				if (conn != null) 
					try { 
						conn.close(); 
					} catch (SQLException ex) { ex.printStackTrace(); }
			}	
	}
}
```
  
#### ✏ 풀이 
① java.sql.*  
② Class  
③ forName  
④ getConnection  
⑤ prepareStatement  
⑥ setString(pattern)  
⑦ executeQuery  
⑧ rs.next()  
⑨ .close()  
    
#### 📄 답 
① java.sql.*   
② Class  
③ forName   
④ getConnection   
⑤ prepareStatement   
⑥ setString(1, pattern) ⑦ executeQuery()  
⑧ rs.next()  
⑨-1 rs.close()   ⑨-2 pStmt.close()   ⑨-3 conn.close()  

<hr>
   
### Q2
Statement와 PreparedStatement의 장단점 및 사용 시 주의점을 기술하시오.  

#### ✏ 풀이 
- Statement
  - 장점  
    : 코드가 간결해진다...?  
  - 단점  
    : SQL문을 수행할 때마다 compile하고, compile한 SQL문을 실행하고, 실행결과를 반환하는 과정을 반복해서 DBMS의 부하가 증가한다.  
  - 주의할 점  
    : 반복적인 작업 수행 시 DBMS의 부하가 증가한다.  
    
- PreparedStatement  
  - 장점  
    : 동일한 형태의 SQL문이 반복적으로 compile되는 것을 피함으로써 DBMS의 부하가 감소한다.  
  - 단점  
    : ?  
  - 주의점  
    - 쿼리 실행 시 매개변수를 입력하면 runtime 오류가 난다.  
    - 매개변수 값을 지정할 때 매개변수의 개수와 값을 설정하는 set메소드 호출의 개수는 같아야 한다.  
    - 테이블 이름은 매개변수 값으로 지정할 수 없다.  
    
  
#### 📄 답 
Statement는 가독성의 문제가 있고, 동일한 형태의 SQL문이 반복적으로 컴파일되어 DBMS의 부하가 증
가한다. 그러나 PreparedStatement는 사전에 컴파일한 SQL문을 실행하여 반복적인 컴파일을 피함으로써
DBMS의 부하가 감소된다.   
  
Statement는 executeQuery() 사용 시 매개변수를 사용하지만, PreparedStatement는 매개변수를 사용하
지 않는다. PreparedStatement는 Statement의 자식클래스이므로 executeQuery() 수행 시 매개변수를
사용하게 되면 runtime error가 발생한다.  

<hr>
  
### Q3
emp 테이블에 나타나지 않는 부서 번호에 대해서도 dept 테이블의 dname을 결과에 포함시키는 SQL문을 두 가지 표현으로 작성하시오.  
(사원이 한 명도 없는 부서)  

#### ✏ 풀이 
```sql
SELECT distinct(dname)
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno;
```
```sql
모름
```
  
#### 📄 답 
```sql
SELECT e.ename, d.dname
FROM emp e, dept d
WHERE e.deptno (+) = d.deptno;
```
```sql
SELECT e.ename, d.dname
FROM emp e RIGHT OUTER JOIN dept d ON e.deptno = d.deptno;
```

<hr>
  
### Q4
아래 코드에서 잘못된 것을 모두 찾아 설명하시오. (3개)    
```java
/// (생략)
String tname = “emp”;
int salary = 2500;
String query ="SELECT empno, mgr, sal FROM ? WHERE mgr = NULL AND sal > ?";

pStmt = conn.prepareStatement(query); // 4. SQL문을 위한 PreparedStatement 객체 생성
pStmt.setString(0, “emp”);
pStmt.setInt(1, salary);
rs = pStmt.executeQuery();
///(생략) 
```
  
#### ✏ 풀이 
① String query에서 'FROM ?'   
  : 테이블 이름은 매개변수 값으로 지정할 수 없다.  
② pStmt.setString(0, "emp");  
  : PreparedStatement의 set메소드의 인덱스는 1부터 시작하므로 0이 아닌 1이다.  
③ pStmt.setInt(1, salary);  
: 위와 같은 이유로 인덱스가 2여야 한다.   
    
  
#### 📄 답 
① FROM ? WHERE  
: 질의문 상에서 값에 해당하는 부분만 대치 가능하다. 따라서 테이블 이름은 매개변수로 대치 불가하다.  
② mgr = NULL  
: 위처럼 하면 모든 mgr이 NULL로 나온다. mgr IS NULL 로 쓰는 것이 적합하다.  
③ pStmt.setString(0, “emp”)  
: 매개변수 위치는 0이 아니라 1부터 시작한다.   
  
<hr>
  
