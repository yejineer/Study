# 문제 범위
Chapter 9. MVC Architecture  &nbsp;&nbsp;&nbsp; *(매우 중요)*  
  
<hr>
  
# 선미 문제
### Q1. Model1 구조와 Model2 구조의 장단점을 서술하시오.
    
#### 📄 답
Model1 구조는 대부분의 일을 JSP가 모두 처리해야 하지만, Model2(MVC) 구조는 작업들을 분리하여 개발 가능하도록 구성되어 있다. Model1 구조는 프로그램 개발자와 웹 디자이너 사이의 작업의 분리가 어려웠으나, MVC 구조는 분리하여 개발 가능하도록 했기 때문에 개발 및 유지보수의 효율성이 높다. 그러나 MVC구조는 Controller 부분에 속하는 클래스들을 별도로 정의해야 하고, request에 대한 mapping은 애플리케이션에 종속적이므로 재사용이 어렵다.
  
#### ✏ 내 풀이  
Model1은 구조가 간단하지만 jsp에서 business logic, presentation logic, 입출력 데이터 처리 등을 모두 구현하기 때문에 하나의 jsp page에서 너무 많은 일을 한다. 그래서 개발 및 유지보수, 코드 재활용에 어려움이 있다.  
Model2는 유연하고 확장하기 쉽고, 디자이너와 개발자 간 협업이 용이하다. 그리고 개발 및 유지보수, 코드 재활용이 쉬워 큰 규모의 프로젝트를 하기에 좋다. 그러나 설계를 위한 클래스들이 많아져서 시간이 오래 걸린다.  
  
### Q2. MVC architecture의 세 가지 요소를 쓰고, 각각의 기능에 대해 설명하시오.  
  
  
#### 📄 답
- Model : business logic 수행 및 data 저장 관리 수행
- View : UI 및 presentation logic 구현
- Controller : Model과 View 사이의 실행 흐름 제어 (사용자의 요청 수신, Model의 기능 선택 및 호출, View 선택 및 결과 전송 등)
   
#### ✏ 내 풀이 
- Model : business logic을 구현하고 DB와 연동해 데이터 처리 및 저장을 수행
- View : client에게 보여질 UI 및 presentation logic 구현
- Controller : Model과 View 사이의 실행 흐름 제어
  
### Q3. 아래 설명에 맞는 클래스의 이름과 MVC의 세 가지 요소 중 어느 요소에 해당되는지 쓰시오.  
> 1\) 애플리케이션의 초기 진입점으로 클라이언트의 request를 받는 클래스  
> 2\) 모델에 접근하기 위해 사용하는 인터페이스(API)를 제공하는 클래스  
> 3\) 각 요청 uri에 대응되는 controller 객체를 찾아 반환하는 클래스   
    
#### 📄 답
1\) DispatcherServlet / Controller  
2\) Manager / Model  
3\) RequestMapping / Controller  
  
#### ✏ 내 풀이 
1) DispatcherServlet Class, Controller
2) Manager Class, Model
3) RequestMapping Class, Controller
  
### Q4. url-pattern "/" 의 의미에 대해 서술하시오.
   
  
#### 📄 답
애플리케이션에 대한 모든 request를 해당 servlet이 받음을 의미한다.
  
#### ✏ 내 풀이 
어플리케이션에 들어오는 모든 request들을 servlet이 받는다는 의미이다.
  
<hr>
  
# 내 문제
### Q1. MVC pattern의 각 구성요소의 역할을 설명하고, MVC pattern을 쓰지 않은 Model 1과 같은 구조는 어떨 때 부적합한지 서술하시오.
① Model :  
② View :    
③ Controller :  
④ 단점 :  
    
#### 📄 답 
① business logic를 구현하고 database, file system등과의 연동을 하며 data 저장/관리를 수행한다.  
② UI 및 presentation logic 구현  
③ Model과 View 사이의 실행 흐름 제어   
④ Model 1은 JSP page에서 presentation logic과 business logic, 입출력 데이터 처리, 실행 흐름 제어 등을 모두 구현하는  
 &nbsp;&nbsp;&nbsp;구조이다. 이 구조는 개발 및 유지보수, 재활용에 어려움이 크므로 복잡하고 변경이 많은 application의 경우엔 부적합하다.


### Q2. Model을 구성하는 Class들의 기능을 서술하시오.
① Domain class :  
② BO class :  
③ DAO class :  
④ Manager(façade) class :  
   
#### 📄 답 
① Application에서 사용되는 데이터의 표현 및 전달을 위한 객체인 VO 및 DTO를 정의하고   
&nbsp; &nbsp; 속성에 대한 setter & getter methods를 포함한다.  
② Business logic을 구현한다.  
③ Database나 기존 legacy system과 연동하여 데이터 처리 및 관리를 수행한다.  
④ JSP 또는 Controller에서 모델에 접근하기 위해 사용하는 인터페이스(API)를 제공하는 Façade class이다.  
  
  
### Q3. DispatcherServlet class(Front Controller) 코드의 빈칸을 채우시오.
```java
public class DispatcherServlet extends ①________ {
    private RequestMapping rm;
    @Override
    public void init() throws ServletException {
        rm = new RequestMapping();
        rm.initMapping();
    }

    @Override
    protected void ②________(HttpServletRequest request, HttpServletResponse response) 
    	throws ServletException, IOException {
    	String contextPath = request.getContextPath();
    	String servletPath = request.getServletPath();
    	
    	// URL 중 servletPath에 대응되는 controller를 구함
        Controller controller = rm.findController(servletPath);
        try {
        	// controller를 통해 request 처리 후, 이동할 uri를 반환 받음
            String uri = controller.③________(request, response);
            
 			// 반환된 uri에 따라 forwarding 또는 redirection 여부를 결정하고 이동 
            if (uri.startsWith("redirect:")) {	
            	// redirection 지시
            	String targetUri = contextPath + uri.substring("redirect:".length());
            	response.④__________(targetUri);	// redirect to url            
            }
            else {
            	// forwarding 수행
            	RequestDispatcher rd = request.⑤___________(uri);
               rd.forward(request, response);		// forward to the view page
            }                   
        } catch (Exception e) {
            logger.error("Exception : {}", e);
            throw new ServletException(e.getMessage());
        }
    }
}
```
  
#### 📄 답
① HttpServlet
② service
③ execute
④ sendRedirect
⑤ getRequestDispatcher


### Q4. request.getAttribute()와 request.getParameter()의 차이는?
   
#### 📄 답
getParameter()메서드의 경우 String타입을 반환하는 반면, getAttribute()는 Object 타입을 리턴하기 때문에 주로 빈 객체나 다른 클래스를 받아올때 사용된다. 또한, getParameter()는 웹브라우저에서 전송받은 request영역의 값을 읽어오고 getAttribute()의 경우 setAttribute()속성을 통한 설정이 없으면 무조건 null값을 리턴한다.  
  
