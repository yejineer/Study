# HTTP
- HyperText Transfer Protocol(HTTP)  
  - 웹 메시지 전송을 위한 응용 계층 프로토콜  
  - Request-response protocol in the client-server computing model  
  - Stateless protocol  

# HTTP Messages
- Web Client(Browser)는 기능 하나하나를 Web Server에 request(요청)한다.  
  그럼 Web Server안에 있는 프로그램이 처리하고 return해준다.  
  ![image](https://user-images.githubusercontent.com/50271884/70384594-2c8ddb00-19c4-11ea-85a8-48f755fd1739.png)
  
# Request Methods
- **GET** : 지정된 리소스에서 데이터를 요청한다.
- **POST** : 처리 할 데이터를 지정된 자원에 제출한다.

- HEAD : GET과 비슷하나, 실제 문서를 요청하는 것이 아니라, 문서 정보를 요청 (Rest API 쓸 때)
- PUT : 내용 갱신 위주 (파일 전송 가능)  (Rest API 쓸 때)  
- DELETE : 웹 리소스를 제거 (Rest API 쓸 때)
- OPTIONS : 웹서버측 제공 메소드에 대한 질의

# GET vs POST
- GET
  - 가장 일반적인 request method
  - **Message body나 header가 필요없음**
  - **Parameters passing**
    - **URL 내에 포함되거나 HTML form으로부터 입력된 값**
    - **URL 내에 query string(name/value pairs)으로 포함되어 전달됨**
    - ASCII 문자열만 가능하고 길이의 제한이 있으며 URL 상에 노출됨
- POST
  - **Message body를 가짐**
  - **Parameters(data) passing**
    - **HTML form으로부터 입력된 값**
    - **Message body에 포함되어 전달됨**
    - Binary data도 가능하고 길이의 제한이 없으며 URL에 노출되지 않음
  - 대량의 중요 데이터 전송이나 file upload 등에 적합
