다음과 같은 기능을 갖는 프로그램을 Spring MVC framework를 기반으로 구현하시오.  
  
# 1. 공연 일정 조회
- 접수된 모든 공연들의 정보를 검색하여 화면에 출력
- 결과 화면 (실행 예 2 참조)
  - 각 공연에 대한 세부정보 조회 및 삭제
  - 새로운 신청 접수를 받는 링크들을 제공

# 2. 참가 신청 접수
### validation
#### - pom.xml에 Bean Validation 의존성 추가  
- Bean Validation은 javax.validation.validation-api로 별도 모듈이 있지만, Hibernate Validator가 해당 모듈을 포함하고 있기 때문에 pom.xml에 아래와 같이 의존성을 추가
```xml
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-validator</artifactId>
  <version>6.0.7.Final</version>
</dependency>
```
- 참고 사이트
  - [Java Bean Validation 사용하기](https://do-study.tistory.com/99)
#### - Form 입력 값 검증
##### time 검증
- 500 - Internal Server Error
  - javax.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'javax.validation.constraints.NotEmpty' validating type 'java.lang.Integer'. Check configuration for 'time'
  - 해결 방법 
    - @NotEmpty, @NotBlank는 Integer에 쓸 수 없음 → @NotNull
- time은 command로 받아올 때는 input값으로 받아왔으므로 String이어야 함
  - 따라서 RegisterRequest의 time은 String형으로 해야 함. (MemberInfo의 time은 int형으로)
  - Controller에서 time 값 validation해줄 때, input에서 아무값도 없었으면 null이 아닌 ""으로 들어오므로 ""로 체크해줘야 함!
    - Integer.valueOf(regReq.getTime().trim().isEmpty()) 사용
