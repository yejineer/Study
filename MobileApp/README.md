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
