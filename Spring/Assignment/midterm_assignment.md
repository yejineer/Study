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
