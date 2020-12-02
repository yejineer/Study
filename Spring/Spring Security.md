# Spring Security
- 보안 솔루션을 제공해주는 Spring 기반의 스프링 하위 프레임워크이다.
- Spring Security에서 제공해주는 보안 솔루션을 사용하면 개발자가 보안 관련 로직을 짤 필요가 없어 굉장히 간편하다.

# 인증과 인가
- 인증(Authentication): 해당 사용자가 본인이 맞는지를 확인하는 절차
- 인가(Authorization): 인증된 사용자가 요청된 자원에 접근가능한지를 결정하는 절차

# 전제조건
- Spring Security에는 Java 8 이상의 런타임 환경이 필요하다.
- Spring Security는 자체 포함 된 방식으로 작동하는 것을 목표로 하므로 Java Runtime Environment에 특별한 구성 파일을 배치 할 필요가 없다. 
  특히, 특별한 JAAS (Java Authentication and Authorization Service) 정책 파일을 구성하거나 Spring Security를 공통 클래스 경로 위치에 배치 할 필요가 없다.
- 마찬가지로 EJB 컨테이너 또는 서블릿 컨테이너를 사용하는 경우 특별한 구성 파일을 어디에도 두거나 서버 클래스 로더에 Spring Security를 포함 할 필요가 없다. 
  모든 필수 파일은 애플리케이션 내에 포함되어 있습니다.
- 이 디자인은 대상 아티팩트 (JAR, WAR 또는 EAR)를 한 시스템에서 다른 시스템으로 복사할 수 있고 즉시 작동하므로 최대 배포 시간 유연성을 제공한다.