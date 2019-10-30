# HTTTP
- HyperText Transfer Protocol(HTTP)  
  - 웹 메시지 전송을 위한 응용 계층 프로토콜  
  - Request-response protocol in the client-server computing model  
  - Stateless protocol  
  
# Request Methods
- GET : Requests data from a specified resource
- POST : Submits data to be processed to a specified resource
  
# GET vs POST
- GET
  - 가장 일반적인 request method
  - Message body나 header가 필요없음
  - Parameters passing
    - URL 내에 포함되거나 HTML form으로부터 입력된 값
    - URL 내에 query string(name/value pairs)으로 포함되어 전달됨
    - ASCII 문자열만 가능하고 길이의 제한이 있으며 URL 상에 노출됨
- POST
  - Message body를 가짐
  - Parameters(data) passing
    - HTML form으로부터 입력된 값
    - Message body에 포함되어 전달됨
    - Binary data도 가능하고 길이의 제한이 없으며 URL에 노출되지 않음
  - 대량의 중요 데이터 전송이나 file upload 등에 적합
