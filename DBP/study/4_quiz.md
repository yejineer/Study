# 문제 범위
Chapter 9. MVC Architecture  &nbsp;&nbsp;&nbsp; *(매우 중요)*  
  
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
④ Model 1은 JSP page에서 presentation logic과 business logic, 입출력 데이터 처리, 실행 흐름 제어 등을 모두 구현하는 구조이다. 
이 구조는 개발 및 유지보수, 재활용에 어려움이 크므로 복잡하고 변경이 많은 application의 경우엔 부적합하다.
  
<hr>


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
  
<hr>
  
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

<hr>


### Q4. request.getAttribute()와 request.getParameter()의 차이는?
 
#### 📄 답
getParameter()메서드의 경우 String타입을 반환하는 반면, getAttribute()는 Object 타입을 리턴하기 때문에 주로 빈 객체나 다른 클래스를 받아올때 사용된다. 또한, getParameter()는 웹브라우저에서 전송받은 request영역의 값을 읽어오고 getAttribute()의 경우 setAttribute()속성을 통한 설정이 없으면 무조건 null값을 리턴한다.  
  
<hr>
