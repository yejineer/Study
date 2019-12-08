# MVC Architecture
## Web Application의 구조
- Model 1
  - JSP page에서 presentation logic과 business logic, 입출력 데이터 처리, 실행 흐름 제어 등을 모두 구현
  - 장점
    - 구조가 간단해 작은 규모의 프로젝트 수행 시 사용 가능
  - 단점
    - 하나의 JSP page에서 너무 많은 일을 한다.
    - 개발 및 유지보수, 재활용에 어려움이 크다. → 복잡하고 변경이 많은 application의 경우에 부적합
- Model 2 (**MVC pattern** 또는 **MVC architecture**)
  - 구성 요소
    - Model : 비즈니스 로직 구현 및 DB와 상호작용하며 데이터 처리 수행
    - View : UI 및 presentation logic 구현
    - Controller : Model과 View 사이의 실행 흐름 제어
  - 장점
    - JSP가 처리해야 했던 많은 일들을 분리하여 개발 가능하도록 구성
    - 개발 및 유지보수의 효율성 향상  
      → 웹 디자이너와 프로그램 개발자가 각각 UI 화면과 business logic을 개발 및 수정하는 것이 용이
  - 단점
    - Controller 부분에 속하는 클래스들을 별도로 정의해야 한다.
    - Request에 대한 mapping은 애플리케이션에 종속적이므로 재사용이 어렵다.

## Model
- 모델의 역할
  - business logic 구현
  - 데이터 처리
    - Database, file system, legacy system 등과의 연동 수행
    ![image](https://user-images.githubusercontent.com/50271884/68205786-79217780-000e-11ea-88aa-3550cc094018.png)  
- Model 관련 Class 4가지
  - Domain class
    - Application에서 사용되는 데이터의 표현 및 전달을 위한 객체인 VO, DTO를 정의
      - Database에 저장되는 객체는 database의 table과 유사한 구조를 가짐
    - 속성에 대한 setter & getter 메소드들 포함
  - Business Object(BO) class
    - Business logic을 구현
      - Business logic이 간단할 경우 BO를 생략하고 Manager class등에서 구현 가능
  - Data Access Object(DAO) class
    - Database나 기존 legacy system과 연동하여 데이터 처리 및 관리 수행
    - JDBC API 등을 사용하여 구현
  - Manager(Façade) class
    - JSP(Model 1) 또는 Controller(Model 2)에서 Model에 접근하기 위해 사용하는 인터페이스(API)를 제공하는 Façade class
      - 외부에서 Model에 접근할 때 반드시 manager object를 이용하도록 함
    - Manager object의 역할
      - domain object를 통해 business object와 DAO에 데이터를 전달
      - business logic 및 데이터 처리 결과를 JSP 또는 Controller에 전달하는 역할을 수행
    - Manager class에서 간단한 business logic을 직접 구현 가능

## Model : Example
- Database Schema
```sql
CREATE TABLE USERINFO (
    userID  varchar2(12) PRIMARY KEY,
    userID  varchar2(12) NOT NULL,
    userID  varchar2(20) NOT NULL,
    userID  varchar2(50),
    userID  varchar2(20)
);
```
- Class Diagram
![image](https://user-images.githubusercontent.com/50271884/68206467-192bd080-0010-11ea-82a9-2f884568d280.png)  
  
- User class
```java
package model;
/**
 * 사용자 관리를 위해 필요한 도메인 클래스. USERINFO 테이블과 대응됨
 */
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phone;

	public User() { }		// 기본 생성자
	
	public User(String userId, String password, String name, String email, String phone) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
        this.phone = updateUser.phone;
    }
	
	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }

	/* 비밀번호 검사 */
	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}
	
	public boolean isSameUser(String userid) {
        return this.userId.equals(userid);
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + ", phone="
				+ phone + "]";
	}	
}
```
