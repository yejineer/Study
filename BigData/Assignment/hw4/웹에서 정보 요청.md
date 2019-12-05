# 웹 클라이언트로서 정보 요청하기
## 요청
- 일정한 약속에 따라 클라이언트(서비스 이용자)가 서버(서비스 제공자)에게 특정 주소(URL)에 해당하는 정보를 달라고 메시지를 보내는 것
- 요청을 해서 웹 환경에 공개된 자원에 접근 가능
  - 자원: 웹 문서 뿐 아니라 이미지/음악/영상 등 여러 가지 형태의 정보를 통틀어 말하는 것
- 파이썬으로 웹 요청을 수행하는 것 = 평소 웹 브라우저로 웹 사이트에 접속하는 것
  - 차이점: 주소를 입력하는 곳이 주소창이 아니라 함수의 매개변수라는 것

## 웹에 정보 요청하기
- 파이썬은 URL과 웹 요청에 관련된 모듈들을 **urllib** 이라는 패키지로 묶어 제공함
- 두 가지 모듈만 알면 HTTP 요청을 할 수 있음
  - urllib.parse : URL 해석/조작 기능을 담은 모듈
  - urllib.request : HTTP 요청 기능을 담은 모듈

## urllib.request 모듈의 HTTP 요청 기능
- urllib.request 모듈을 import한 후, urllib.request.urlopen(요청할URL).read().decode('utf-8') 실행하면 웹요청 보내짐!
  - urllib.request.urlopen() 함수: 웹 서버에 정보를 요청한 후, 돌려받은 응답을 저장하여 HTTPResponse를 반환함
  - 반환된 응답 객체의 read()를 실행하여 웹 서버가 응답한 데이터를 바이트 배열로 읽어들인다.
  - 읽어들인 바이트 배열은 이진수로 이루어진 수열이어서 그대로 사용하기 어렵다. 웹 서버가 응답한 내용이 텍스트 형식의 데이터라면, 바이트 배열의 decode('utf-8')를 실행하여 문자열로 변환할 수 있다. 이 때, 'utf-8'은 유니코드 부호화 형식의 한 종류인데 decode()함수의 기본 인자이므로 생략해도 된다.
    
## 웹 문서 요청 함수 정의해 두기
```python
import urllib.request
def request(url):
 """지정한 url의 웹 문서를 요청하여, 본문을 반환한다."""
    response = urllib.request.urlopen(url)
    byte_data = response.read()
    text_data = byte_data.decode('utf-8')
    return text_data
 ```
- 웹 요청이 필요할 때마다 사용하면 됨. 요청을 실행했을 때, 인터넷 연결이 원활하지 않거나 URL이 잘못되었다면 예외 발생can!

## 웹 문서 요청하기
```python
 import urllib.request
 url = 'https://python.bakyeono.net'  # 요청할 URL
 webpage = urllib.request.urlopen(url).read().decode('utf-8')
 print(webpage)  # 응답 받은 텍스트 확인: HTML 문서가 출력된다
<!DOCTYPE html>
<html>
... (뭔가 복잡한 내용이 출력된다) ...
</html>
```
- 웹의 정보를 파이썬에서 읽어들인다면, 그 목적은 읽어들인 정보를 프로그램으로 처리하기 위한 것
- 웹에는 프로그램에서 다루기 편리한 형식으로 제공되는 정보가 많이 있다. ex) JSON

