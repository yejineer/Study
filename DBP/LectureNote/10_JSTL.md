# JSTL 개요
## 1. Custom Tag
- JSP에서 반복적으로 사용되는 프로그램 로직을 캡슐화하여 태그 형태로 정의한 것
- JSP페이지에서 scriptlet(Java code)을 대체하기 위해 사용함

## 2. JSP Standard Tag Library
- JSP와 함께 Java EE Platform에 포함됨
- JSP 개발에서 공통적으로 사용되는 유용한 custom tag들을 정의
- MVC구조에서 collection과 같이 여러 원소들을 포함하는 데이터를 처리할 때 특히 유용

## 3. JSTL 구성
- Library, URI, prefix
  - Core기능, Function 기능

# Expression Laguage
## 1. EL
- JSP에서 Javabeans나 Map과 같은 Java component를 Java code(scriptlet)를 사용하지	 않고 쉽게 접근하게 해주는 언어
- ${expression}형식을 사용해 다음과 같은 것들을 접근 가능
  - JavaBean properties
  - Map, List, Array elements
  - Servlet 내장객체의 속성
  
## 2. 사용 예
- JavaBean에 대한 접근
  - ${person.name} 또는 ${person[“name”]}
	   = <%= ((Person)request.getAttribute(“person”)).getName()%>    - Map에 대한 접근   
	- ${musicMap[“Ambient”]} 또는 ${musicMap[Genre]}	// Zero 7
	   - java.util.Map mMap = new java.util.HashMap();
	     musicMap.put(“Ambient”, “Zero 7”);
	     request.setAttribute(“musicMap”, mMap);
- 배열 또는 List에 대한 접근   
  - ${musicList}, ${musicList[0]}

## 3. EL 내장 객체
- pageSocpe, requestScope, sessionScope, applicationScope
- param, paramValues
- header, headerValues, pageContext
- cookie
- initParam

# JSTL Core Library
## 1. Tag Summary    
  
  &nbsp;&nbsp;&nbsp;![image](https://user-images.githubusercontent.com/50271884/70375805-b480d000-1945-11ea-9fd4-1daaae29eabb.png)  
  &nbsp;&nbsp;&nbsp;![image](https://user-images.githubusercontent.com/50271884/70375806-b77bc080-1945-11ea-95f7-e2ea804b3536.png)

  


# JSTL Functions
## 1. Tag Summary     
  
  &nbsp;&nbsp;&nbsp;![image](https://user-images.githubusercontent.com/50271884/70375803-b054b280-1945-11ea-9789-d4c9979bcb67.png)

- Examples   
  
  &nbsp;&nbsp;&nbsp;![image](https://user-images.githubusercontent.com/50271884/70375786-88fde580-1945-11ea-80f8-977c9cdcfbf4.png)
