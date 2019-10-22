## SQLite
  			↱ 앱 자체의 라이브러리 형태로 내장되어 있음 
- 클라이언트 어플리케이션에 주로 사용하는 경량 내장형 DBMS 	(관계형 DB)  
- 모바일 기기 등에 DB를 사용해 자료를 저장하여야 할 경우 사용  
  
  
## 안드로이드 DB 사용
① DB설계:  요구사항 / UI 보고 설계	( ※ 주의: primary key의 컬럼명은 _id로 반드시 추가! )
② Helper 클래스 작성 : SQLiteOpenHelper 상속   
  - 필수 구현 3가지 메소드: 생성자, OnCreate(), OnUpgrade() 
  -  설계를 바탕으로 DB 테이블 생성 & 샘플 data 추가  
③ DB 사용  
  
- 실제 DB 사용 시점  
  - DB 접근이 필요할 때 Helper 클래스 객체 생성 ( Writable / Readable )
  - CRUD : Create(insert) Read(select) Update(update) Delete(delete)
