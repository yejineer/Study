오류정리
==  
java.io.IOException: Cleartext HTTP traffic to www.kobis.or.kr not permitted
-- 
: EditText로 받은 내용으로 url의 쿼리 부분을 채운 후 서버 응답을 받으려는데 생긴 오류 (Upload Http)  

### 해결방법  
▶ url의 http를 https 바꿔준다.   
▶ AndroidManifest.xml 파일의 <application> 부분에 android:usesCleartextTraffic="true" 로 설정
```xml
<application
             …
             android:usesCleartextTraffic="true"
>
  
```
### [참고]  
#### if)  
&nbsp;&nbsp;&nbsp;&nbsp;Internet Permission도 줬는데 WebView에서 서버 응답을 못 받는다면,  
#### Then)  
  1. 접속 url을 https://로 시작하는 주소로 지정하거나,   
  1. application보안설정을 변경(android:usesCleartextTraffic="true" 추가)하면 됨.
