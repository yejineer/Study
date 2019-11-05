# 문제 범위
Chapter 9. MVC Architecture  &nbsp;&nbsp;&nbsp; *(매우 중요)*  
  
<hr>
  
# 내 문제
### Q1. MVC pattern의 각 구성요소의 역할을 설명하고, MVC pattern을 쓰지 않은 Model 1과 같은 구조는 어떨 때 부적합한지 서술하시오.
 
#### 📄 답 
Model : business logic를 구현하고 database, file system등과의 연동을 하며 data 저장/관리를 수행한다.  
View : UI 및 presentation logic 구현  
Controller : Model과 View 사이의 실행 흐름 제어  
  
단점: 
Model 1은 JSP page에서 presentation logic과 business logic, 입출력 데이터 처리, 실행 흐름 제어 등을 모두 구현하는 구조이다. 
이 구조는 개발 및 유지보수, 재활용에 어려움이 크므로 복잡하고 변경이 많은 application의 경우엔 부적합하다.
  
<hr>


### Q2. Model을 구성하는 Class들에 대해 설명하시오.
① Domain class :  
② BO class :  
③ DAO class :  
④ Manager(façade) class :  
 
#### 📄 답 
① Application에서 사용되는 데이터의 표현 및 전달을 위한 객체인 VO 및 DTO를 정의한다.  
  속성에 대한 setter & getter methods를 포함한다.  
② Business logic을 구현하는 클래스  
③ Database나 기존 legacy system과 연동하여 데이터 처리 및 관리를 수행한다. JDBC API 등을 사용하여 구현한다.  
④ JSP 또는 Controller에서 모델에 접근하기 위해 사용하는 인터페이스(API)를 제공하는 Façade class이다.  
  
<hr>
  
### Q3. 

#### 📄 답

<hr>

### Q
 
#### 📄 답

<hr>
