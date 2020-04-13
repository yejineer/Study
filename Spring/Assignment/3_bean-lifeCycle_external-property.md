# 실습 3: bean life-cycle 관리, 외부 property 설정
## 1번
### [추가/수정 코드]
#### ⓐ SpringIdol.java
```java
public class SpringIdol implements TalentCompetition, InitializingBean {
	@Autowired
	private Performer[] performers;
	
	@Autowired //문제 1번
	private Piano piano;
	
	@Value("${stage.size}") //문제 1번
	private int stageSize;
	
	public SpringIdol() {
	}
	
	public Performer[] getPerformers() { return performers; }
	public void setPerformers(Performer[] performers) { this.performers = performers; }
	
	@Override ////문제 1번. InitializingBean의 메소드 구현 
	public void afterPropertiesSet() throws Exception {
		System.out.println("SpringIdol.setStageSize(): 무대 크기 = " + stageSize);
	}
	
	public void tunePiano() { //문제 1번. init-method
		System.out.println("SpringIdol.tunePiano(): 피아노 조율");
		this.piano.tune();
	}
	
	public void cleanPiano() { //문제 1번. destroy-method
		System.out.println("SpringIdol.cleanPiano(): 피아노 청소");
		this.piano.clean();
	}
	
	@Override
	public void run() {
		for (int i = 0; i < performers.length; i++) {
			System.out.print("<Performance #" + (i+1) + ">");
			Performer performer = performers[i];
			performer.perform();
			System.out.println("-----------------------");
		}
	}

}
```
#### ⓑ Piano.java
```java
@Component
public class Piano implements Instrument {
	public Piano() {
	}

	public void tune() { //문제 1번
		System.out.println("Piano.tune(): Tuning the piano.");
	}
	
	public void clean() { //문제 1번
		System.out.println("Piano.clean(): Cleaning the piano.");
	}
	
	public void play() {
		System.out.println("PLINK PLINK PLINK");
	}
}
```
#### ⓒ spring-idol.xml
```xml
<bean id="springIdol" class="com.example.springidol.SpringIdol"
	init-method="tunePiano"
	destroy-method="cleanPiano"/> <!-- 문제 1번 -->
```
### [실행 결과]  
SpringIdolMain starts.  
SpringIdol.setStageSize(): 무대 크기 = 200  
SpringIdol.tunePiano(): 피아노 조율  
Piano.tune(): Tuning the piano.  
<Performance #1>  
...(생략)...  
HARMONICA : HUM HUM HUM  
SpringIdol.cleanPiano(): 피아노 청소  
Piano.clean(): Cleaning the piano.  
  
  
## 2번
### [추가/수정 코드]
#### ⓐ Performer.java
```java
public interface Performer {
	public void perform() throws PerformanceException;
	public String getBeanName(); // 추가
}
```
#### ⓑ Instrumentalist.java, Juggler.java, OneManBand.java, Singer.java
```java
public class Instrumentalist implements Performer, BeanNameAware { //BeanNameAware추가
```
```java
public class Juggler implements Performer, BeanNameAware { //BeanNameAware추가
```
```java
public class OneManBand implements Performer, BeanNameAware { //BeanNameAware추가
```
```java
public class Singer implements Performer, BeanNameAware { //BeanNameAware추가
```
##### - 4개의 클래스 안에 아래의 코드를 추가한다
```java
private String beanName; 
	
@Override 
public void setBeanName(String name) {
	this.beanName = name;
}

@Override
public String getBeanName() {
	return this.beanName;
}
```
#### ⓒ SpringIdol.java 
```java
@Override
public void run() { //수정
	for (int i = 0; i < performers.length; i++) {
		System.out.print("<Performance #" + (i+1));
		Performer performer = performers[i];
		System.out.println(": " + performer.getBeanName() + ">"); //bean id 출력
		performer.perform();
		System.out.println("-----------------------");
	}
}
```

### [실행 결과]  
SpringIdolMain starts.  
SpringIdol.setStageSize(): 무대 크기 = 200  
SpringIdol.tunePiano(): 피아노 조율  
Piano.tune(): Tuning the piano.  
<Performance #1: hank>  
GUITAR : STRUM STRUM STRUM  
CYMBAL : CRASH CRASH CRASH  
HARMONICA : HUM HUM HUM  
\-----------------------  
<Performance #2: duke>  
...(생략)  
\-----------------------  
<Performance #3: kenny>  
Playing Jingle Bells : TOOT TOOT TOOT  
\-----------------------  
<Performance #4: chris>  
Chris Kim is singing a song "Bohemian Rhapsody" by Queen  
\-----------------------  
...(생략)...  

## 3번
### [추가/수정 코드]
#### ⓐ app.properties 파일
```java property file
guitar.sound=STRUM STRUM STRUM
cymbal.sound=CRASH CRASH CRASH
harmonica.sound=HUM HUM HUM

kenny.song=Jingle Bells

song1.title=Bohemian Rhapsody
song1.artist=Queen

song2.title=Hero
song2.artist=Mariah Carey

singer1.name=Chris Kim
singer2.name=Jain Lee

stage.size=200
```
#### ⓑ spring-idol.xml
##### 추가 코드
```xml
<context:property-placeholder location="classpath:app.properties"/>
```
##### 수정 코드
```xml
<bean id="kenny" class="com.example.springidol.Instrumentalist">
	<property name="song" value="${kenny.song}" /> <!-- 문제 3번 -->
	<property name="instrument" ref="saxophone" />
</bean>

<bean id="chris" class="com.example.springidol.Singer">
        <constructor-arg value="${singer1.name}" /> <!-- 문제 3번 -->
        <constructor-arg ref="bohemian" />
</bean>
```
```xml
<util:properties id="instruments">
	<prop key="GUITAR">${guitar.sound}</prop> <!-- 문제 3번 -->
	<prop key="CYMBAL">${cymbal.sound}</prop> <!-- 문제 3번 -->
	<prop key="HARMONICA">${harmonica.sound}</prop> <!-- 문제 3번 -->
</util:properties>
	
<bean id="bohemian" class="com.example.springidol.Song">
	<property name="title" value="${song1.title}" /> <!-- 문제 3번 -->
	<property name="artist" value="${song1.artist}" /> <!-- 문제 3번 -->
</bean>
```
### [실행결과]
2번의 결과와 똑같으나 singer가 부르는 곡부분만 property file을 이용해서 변경했다.  
   
<Performance #4: chris>
Chris Kim is singing a song "Hero" by Mariah Carey
