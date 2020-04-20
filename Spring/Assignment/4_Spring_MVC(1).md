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
  
### (2) 추가한 파일 
- Message.java (파일로 첨부)  
- HelloService.java (파일로 첨부)  
- helloIdol.jsp  
```jsp
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Hello</title>
</head>
<body>
	<h2>인사말: <b>${message.greeting} I'm ${message.name}.</b></h2>
</body>
</html>
```
  
### (3) 실행 결과
- /hello.do/kenny 요청 시   
  ![image](https://user-images.githubusercontent.com/50271884/79748580-a78ad900-8348-11ea-8133-cd6f9a0cca8f.png)  
    
- /hello.do/chris 요청 시  
  ![image](https://user-images.githubusercontent.com/50271884/79748650-c12c2080-8348-11ea-999c-e9a50d5da757.png)  
   
     
## 2번
### (1) 수정한 파일
#### ⓐ Instrumentalist.java
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
public String perform() throws PerformanceException {
	return "Playing " + song + " : " + instrument.play();
}
```
#### ⓑ Juggler.java
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
public String perform() throws PerformanceException {
	return "JUGGLING " + beanBags + " BEANBAGS";
}
```
#### ⓒ OneManBand.java
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
public String perform() throws PerformanceException {
	String s = "";
	for (Instrument instrument : instruments) {
		s += instrument.play();
		s += "<br>";
	}
	return s;
}
```
#### ⓓ Performer.java (인터페이스)
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
public String perform() throws PerformanceException;
```
#### ⓔ PoeticJuggler.java
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
public String perform() throws PerformanceException {
	super.perform();
	return "While reciting..." + poem.recite();
}
```
#### ⓕ Singer.java
```java
// 공연자 클래스의 perform() 메소드에서 공연 결과를 문자열로 반환하도록 수정
@Override
public String perform() throws PerformanceException {
	return name + " is singing a song \"" + song.getTitle() + "\""+ " by " + song.getArtist();
}
```
#### ⓖ Cymbal.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play() {
	return "CRASH CRASH CRASH";
}
```
#### ⓗ Guitar.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play() {
	return "STRUM STRUM STRUM";
}
```
#### ⓘ Harmonica.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play() {
	return "HUM HUM HUM";
}
```
#### ⓙ Instrument.java (인터페이스)
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play();
```
#### ⓚ Piano.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play() {
	return "PLINK PLINK PLINK";
}
```
#### ⓛ Poem.java (인터페이스)
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String recite();
```
#### ⓜ Saxophone.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String play() {
	return sound;
}
```
#### ⓝ Sonnet29.java
```java
//console에 출력하는 대신 문자열로 반환하도록 수정
public String recite() {
	String s = "<br>";
	for (int i = 0; i < LINES.length; i++) {
		s += LINES[i];
		s += "<br>";
	}
	return s;
}
```
#### ⓞ HelloController.java 확장
- 파일로 첨부

### (2) 추가한 파일 
- perform.jsp  
```jsp
<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>Perform</title>
</head>
<body>
	<h2><b>${performer.perform()}</b></h2>
</body>
</html>
```
  
### (3) 실행 결과
- /perform/kenny 요청 시   
  ![image](https://user-images.githubusercontent.com/50271884/79748746-e325a300-8348-11ea-8a9a-7820c7fe517d.png)    
    
- /perform/chris 요청 시  
  ![image](https://user-images.githubusercontent.com/50271884/79748780-f46eaf80-8348-11ea-97c6-2052146fa108.png)  
   
