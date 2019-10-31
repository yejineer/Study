# 문제 범위
Chapter 8. HTTP, Servlet & JSP Review  
  
<hr>
  
# 내 문제
### Q1. Status Codes에 대해 설명하시오.
  
#### 📄 답 
- 웹 서버가 request를 수신하여 처리한 결과의 종류를 나타냄.
- 웹 클라이언트가 해야 할 action을 명시  
  
<hr>

### Q2. JSP page는 최초 요청 처리 시 (A), 이후 요청은 (B)다.  

#### 📄 답
(A) servlet으로 변환되고 메모리에 load되고
(B) 변환 없이 메모리 상의 servlet에서 바로 처리된
  
<hr>  

### Q3. JSP Request 처리 과정을 서술하시오.
#### ① 웹 브라우저가 인터넷을 통해 웹 서버에게 ( 1 )  
#### ② 웹 서버가 웹 컨테이너에게 ( 2 )  
#### ③ 웹 컨테이너가  
  - #### ( 3 )  
  - #### ( 4 )  
  - #### ( 5 )  
  - #### ( 6 )  
#### ④ 웹 컨테이너가 웹 서버에게 ( 7 )  
#### ⑤ 웹 서버가 인터넷을 통해 웹 클라이언트에게 ( 8 )  
#### ⑥ 웹 클라이언트가 화면을 출력한다.  
  
#### 📄 답
(1) JSP 파일 요청(HTTP request message)  
(2) 사용자 요구 처리 요청  
(3) JSP 파일 parsing  
(4) Servlet으로 변환  
(5) class 파일 생성  
(6) 메모리에 적재 후 실행  
(7) HTML형태의 처리 결과 전송  
(8) 처리 결과 전송(HTTP response message)  
  
<hr>
  
### Q4. 다음 JSP 내장 객체의 데이터 저장 영역에 대해 설명하시오.
(1) request :   
(2) response :   
(3) application :   
  
#### 📄 답
(1) 다른 page를 include하거나 다른 page로 forward할 때 전달됨  
(2) 각 client가 여러 request들에 대해 유지 및 공유되는 영역  
(3) Web container에서 관리하며 모든 client가 공유 가능  
  
<hr>
  
