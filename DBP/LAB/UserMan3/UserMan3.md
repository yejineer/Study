# UserMan3 추가 기능
- Community 기능을 위한 DomainDAO, Controller 클래스 추가 및 병
- DB 접속 설정 외부화
   
# UserMan2에서 변경된 패키지&클래스
- controller.RequestMapping (request mapping 정보 수정 및 추가)
- controller.comm.* (추가: 커뮤니티 기능 관련 컨트롤러들)
- controller.user.RegisterUserController (커뮤니티 id 처리 추가)
- controller.user.UpdateUserFormController (삭제: UpdateUserController에 병합됨)
- controller.user.UpdateUserController (UpdateUserFormController 기능 포함)
- model.User (커뮤니티 id 필드 추가)
- model.Community (추가)
- model.dao.UserDao (커뮤니티 소속 사용자 검색 메소드들 추가)
- model.dao.CommunityDao (추가)
- model.dao.ConnectionManager (DB 접속 설정 외부화: context.properties 파일 이용)
- model.dao.JDBCUtil (Sequence를 이용한 PK 값 생성 시 생성된 값 확인을 위한 메소드 추가)
- model.service.UserManager (커뮤니티 관련 기능 추가)
- resources/context.properties (추가: DB 접속 설정 값 정의)
- resources/schema.sql (커뮤니티 관련 DB 테이블 & 레코드 정의 추가)
- WebContent/community/*.jsp (추가: 커뮤니티 관련 view pages)
- WebContent/css/community.css (추가: 커뮤니티 관련 style 정의)
- WebContent/user/list.jsp (list2.jsp와 동일: JSTL + EL 활용 구현)
- WebContent/user/registerForm.jsp (커뮤니티 이름 선택 메뉴 추가: 메뉴 항목 검색 및 출력 미구현)
- WebContent/user/updateForm.jsp (커뮤니티 이름 선택 메뉴 추가)
- WebContent/user/view.jsp (커뮤니티 이름 및 링크  추가)
