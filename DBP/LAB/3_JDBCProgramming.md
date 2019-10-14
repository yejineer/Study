# Lab3.java  
## 코드  
  
```java
package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Lab3 {

	public Lab3() { // 생성자
		// JDBC 드라이버 로딩 및 등록
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	private static Connection getConnection() {
		String url = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
		// "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String passwd = "TIGER";

		// DBMS와의 연결 획득
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
  
  // 3번 위한 printEmployeesInDept
	public static void printEmployeesInDept(String deptName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT empno, ename, job, sal, comm "
                + "FROM depts0964 d JOIN emps0964 e ON d.deptno = e.deptno "
               + "WHERE dname = ?";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deptName);
			rs = pstmt.executeQuery();

			System.out.println("사번\t  이름\t   직무\t\t월급\t      수당");
			System.out.println("=================================================");
			while (rs.next()) {
				int empNo = rs.getInt("empno"); // column 이름 정확하게 입력
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				double salary = rs.getDouble("sal");
				double comm = rs.getDouble("comm");

				System.out.printf("%d %10s %10s %10.2f %10.2f\n", empNo, ename, job, salary, comm);
			}
			System.out.println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally { // 자원 반납
			if (rs != null)
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
  
  // 2번 위한 printDeptInfo
	public static void printDeptInfo(String deptName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT d.deptno, manager, count(ename) countOfEmps "
				+ "FROM depts0964 d JOIN emps0964 e ON d.deptno = e.deptno " + "WHERE d.dname = ? "
				+ "GROUP BY d.deptno, manager";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deptName);
			rs = pstmt.executeQuery();

			System.out.println("부서번호        부서관리자 사번          소속 사원수 ");
			System.out.println("===============================");
			if (rs.next()) {
				int deptNo = rs.getInt("deptno");
				int managerNo = rs.getInt("manager");
				int countOfEmps = rs.getInt("countOfEmps");
				System.out.printf("%d %10d %10d\n", deptNo, managerNo, countOfEmps);
			}
			System.out.println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally { // 자원 반납
			if (rs != null)
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}

	}
  
  // 5번 위한 replaceManagerOfDept
	public static void replaceManagerOfDept(String deptName, int managerNo, double comm) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String query1 = "UPDATE depts0964 "
                + "SET manager = ? "
               + "WHERE dname = ?";
		String query2 = "UPDATE emps0964 "
				+ "SET comm = NVL(comm, 0) + ? "
				+ " WHERE empno = ?"; 
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(query1);
			pstmt.setInt(1, managerNo);
			pstmt.setString(2, deptName);
			
			pstmt.executeUpdate();
			pstmt.close();
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setDouble(1, comm);
			pstmt.setInt(2,  managerNo);
			
			pstmt.executeUpdate();
			
			System.out.println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally { // 자원 반납
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
	}
  
  // 6번 위한 printEmpInfo
	public static void printEmpInfo(int empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "SELECT empno, ename, job, sal, comm, dname "
					+ "FROM depts0964 d JOIN emps0964 e ON d.deptno = e.deptno "
					+ "WHERE empno = ?";
		conn = getConnection();

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();

			System.out.println("사번\t\t이름\t  직무\t         월급\t\t  수당\t\t소속부서명 ");
			System.out.println("==========================================================================");
			if (rs.next()) {
				String eName = rs.getString("ename");
				String eJob = rs.getString("job");
				double salary = rs.getDouble("sal");
				double comm = rs.getDouble("comm");
				String dName = rs.getString("dname");
				System.out.printf("%10d %10s %10s %10.2f %10.2f %15s\n", empNo, eName, eJob, salary, comm, dName);
			}
			System.out.println();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally { // 자원 반납
			if (rs != null)
				try { rs.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (pstmt != null)
				try { pstmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
			if (conn != null)
				try { conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//1
		System.out.print("부서명을 입력하시오: ");
		String deptName = scanner.next();
		
		//2
		printDeptInfo(deptName);
		
		//3
		printEmployeesInDept(deptName);
		
		//4
		System.out.print("새 관리자 사번과 관리자 수당을 입력하시오: ");
		int managerNo = scanner.nextInt();
		double commission = scanner.nextDouble();

		//5
		replaceManagerOfDept(deptName, managerNo, commission);
		
		//6
		System.out.println("새 관리자 정보: ");
		printEmpInfo(managerNo);

		scanner.close();
	}
}
```
  
## 실행 결과  
![결과](https://user-images.githubusercontent.com/50271884/66759666-a1b8c480-eedb-11e9-8fdb-b66867bfd073.PNG)  
