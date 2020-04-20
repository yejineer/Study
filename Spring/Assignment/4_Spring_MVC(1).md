# 실습 4. Spring MVC(1)
## 1번
### (1) 수정한 파일
#### ⓐ Instrumentalist.java에 추가한 코드
```java
	@Value("${kenny.name}") // app.properties에 저장된 kenny의 name 값
	private String name; // 공연자 이름을 저장할 name 필드

	public String getName() {
		return name;
	}
```
#### ⓑ Juggler.java에 추가한 코드
```java
	private String name; // 공연자 이름을 저장할 name 필드

	public String getName() {
		return name;
	}
```
#### ⓒ OneManBand.java에 추가한 코드
```java
	@Value("${hank.name}") // app.properties에 저장된 hank의 name 값
	private String name; // 공연자 이름을 저장할 name 필드

	public String getName() {
		return name;
	}
```
#### ⓓ Performer.java에 추가한 코드
```java
	public String getName();
```
#### ⓔ PoeticJuggler.java에 추가한 코드
```java
	@Value("${duke.name}") // app.properties에 저장된 duke의 name 값
	private String name; // 공연자 이름을 저장할 name 필드

	public String getName() {
		return name;
	}
```
#### ⓕ app.properties 파일의 수정 코드
```java properties file
kenny.name=Kenny G
hank.name=Hank
duke.name=Duke
```
#### ⓖ HelloController.java 확장
- 파일로 첨부  

#### ⓗ application-config.xml의 수정 코드 (bean scan 대상에 service 패키지 추가)
```xml
<context:component-scan base-package="com.example.springidol, com.example.helloworld.service" />
```

### (2) 추가한 파일 (파일로 첨부)
- Message.java
- HelloService.java
- helloIdol.jsp

