Stream
==
## Stream
- 입력과 출력을 byte들의 흐름으로 생각하는 것  ex) File, Device, Network  
- 추상적 인터페이스 (저장장치와  Network는 하나 더 추상적으로!)
- 외부 장치와의 모든 통신은 stream으로  
- stream을 쓰거나 읽거나
- 저장장치에 쓸 때는 파일을 하나 만들어서 거기에 작성하고 보냄  
  
## Standard Stream
- 기본적인 스트림들은 프로그래머가 생성하지 않아도 자동으로 생성됨  
이름   | 스트림           | 연결 장치  
------ | ---------------  | -----------  
stdin  | 표준 입력 스트림 | 키보드  
stdout | 표준 출력 스트림 | 모니터 화면
stderr | 표준 오류 스트림 | 모니터 화면  
* 이는 Linux의 기본 stream이다.  
  
- 각 언어에서의 표준 스트림  
언어   | 표준 입력 스트림 | 표준 출력 스트림 | 표준 오류 스트림  
------ | ---------------- | ---------------- | ----------------------------
C      | scanf()          | printf()         | fprintf(stderr, ..)
Java   | System.in        | System.out       | System.err        
Python | input()          | print()          | print("..", file=sys.stderr)
 
   
# 파일 (Linux)
## 파일 접근 권한 보호 : ACL
- 리눅스는 파일에 무단으로 접근하는 것을 방지하고 보호하는 기능을 제공 
- 사용자는 자신의 파일/디렉터리 중에서 다른 사용자가 접근O/X인 것을 구분해 접근 권한을 제한  
  
## 파일의 속성  
> user1@myubuntu:~$ ls -l /etc/hosts
> ①-②rw-r--r-- ③1 ④root ⑤root ⑥223 ⑦11월 8 23:13 ⑧/etc/hosts
> user1@myubuntu:~$

번호 | 의미
---- | ------------------------------------------
①    | 파일의 종류(-: 일반 파일, d: 디렉터리)
②    | 파일을 읽고 쓰고 실행할 수 있는 접근 권한 표시
③    | 하드 링크의 개수
④    | 파일 소유자의 로그인 ID
⑤    | 파일 소유자의 그룹 이름
⑥    | 파일의 크기(byte단위)
⑦    | 파일이 마지막으로 수정된 날짜의 시간
⑧    | 파일명
  
### 1. 파일의 종류
- **file** 명령: 지정한 파일의 종류를 알려줌  

### 2. 파일의 접근 권한
![file](https://user-images.githubusercontent.com/50271884/66132991-e79aa080-e630-11e9-9950-9350ec2f0c62.PNG)  
  
- 파일의 접근 권한 변경: chmod  

## Stream Redirection
- 입출력 재지정
![20191003_135812538_iOS](https://user-images.githubusercontent.com/50271884/66133288-5b3cad80-e631-11e9-81ec-3cb878d90bd7.png)  

# 파일 입출력
## 파일 쓰기
```python
open('파일경로', '모드')
```
- 프로그램이 생성한 정보를 영구적으로 저장하기 위해 파일에 기록
- 유니스와 C와 거의 비슷함
- **파일 입출력을 위해 위치를 확인하고 버퍼를 준비하는 과정** : open
- 파일 경로 : 입출력 대상의 이름. 절대·상대경로 모두 가능
- 모드: 읽기, 쓰기, 추가 등 파일로 무엇을 할 것인가를 지정