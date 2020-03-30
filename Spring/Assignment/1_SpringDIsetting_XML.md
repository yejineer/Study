# 실습 1: XML 기반 Spring DI 설정
## 1번
#### [수정 코드]
```xml
<bean id="kenny" class="com.example.springidol.Instrumentalist">
	<property name="song" value="Jingle Bells" />
	<property name="instrument" ref="guitar" /> <!-- saxophone에서 guitar로 수정 -->
</bean>
```
#### [실행 결과]
<Performance #2>  
Playing Jingle Bells : STRUM STRUM STRUM~~  
  
## 2번
#### [수정 코드]
```xml
<bean id="duke" class="com.example.springidol.PoeticJuggler"
	c:poem-ref="sonnet29" /> <!-- "c" namespace 이용 -->

<bean id="kenny" class="com.example.springidol.Instrumentalist"
	p:song="Jingle Bells"
	p:instrument-ref="guitar" /> <!-- "p" namespace 이용 -->
```
#### [실행 결과]
변화 없음   
  
## 3번
### ⑴ OneManBand.java
#### [원본 코드]
```java
import java.util.Properties;

public class OneManBand implements Performer {
  private Properties instruments;
  
  public OneManBand() {
  }
  
  public void setInstruments(Properties instruments) {
    this.instruments = instruments;
  }
  
  @Override
  public void perform() throws PerformanceException {
    for (Object element : instruments.keySet()) {
		  String key = (String) element;
		  System.out.println(key + " : " + instruments.getProperty(key));
    }
  }
}
```
#### [수정 코드]
```java
import java.util.Map; // Map util을 import

public class OneManBand implements Performer {
  public OneManBand() {
  }

  private Map<String, Instrument> instruments; // Properties타입에서 수정
  public void setInstruments(Map<String, Instrument> instruments) {
  	this.instruments = instruments;
  }

  @Override
  public void perform() throws PerformanceException { //perform() method도 알맞게 수정
  	for (String key : instruments.keySet()) {
	    Instrument instrument = instruments.get(key);
	    instrument.play();
	  }
  }	
}
```
### ⑵ spring-idol.xml
#### [원본 코드]
```xml
<bean id="hank" class="com.example.springidol.OneManBand">
	<property name="instruments">
		<props>
			<prop key="GUITAR">STRUM STRUM STRUM</prop>
			<prop key="CYMBAL">CRASH CRASH CRASH</prop>
			<prop key="HARMONICA">HUM HUM HUM</prop>
		</props>
	</property>
</bean>
```
#### [수정 코드]
```xml
<bean id="hank" class="com.example.springidol.OneManBand">
	<property name="instruments">
		<map> <!-- <props>에서 <map>으로 수정 -->
			<entry key="HARMONICA" value-ref="harmonica"/> <!-- <prop>에서 <entry>로 수정 -->
			<entry key="CYMBAL" value-ref="cymbal"/>
			<entry key="SAXOPHONE" value-ref="saxophone"/>
		</map>
	</property>
</bean>
```
#### [실행 결과]
<Performance #3>  
HUM HUM HUM~~  
CRASH CRASH CRASH~~  
TOOT TOOT TOOT~~  
  
## 4번
### ⑴ Song.java (추가된 클래스)
```java
package com.example.springidol;

public class Song {
  private String title;  // 제목
  private String artist; // 원곡자
	
  public Song() {
  }

  public String getTitle() {
  	return title;
  }
  public void setTitle(String title) {
  	this.title = title;
  }

  public String getArtist() {
  	return artist;
  }
  public void setArtist(String artist) {
  	this.artist = artist;
  }
}

```
### ⑵ Singer.java (추가된 클래스)
```java
package com.example.springidol;

public class Singer implements Performer {
  private String name;	// 이름
  private Song song;	// 곡 정보
	
  public Singer(String name, Song song) { //생성자를 이용한 DI 설정 위해
	  this.name = name;
	  this.song = song;
  }
	
  @Override
  public void perform() throws PerformanceException {
  	System.out.print(name + " is singing a song " + song.getTitle() + " by " + song.getArtist() + ".");
  }
}
```
### ⑶ spring-idol.xml
#### [수정 코드]
```xml
<!-- performers에 두 명의 가수 singerA와 singerB를 경연에 참가시킨다. -->
<bean id="springIdol" class="com.example.springidol.SpringIdol">
	<property name="performers">
		<list>
			<ref bean="duke" />
			<ref bean="kenny" />
			<ref bean="hank" />
			<ref bean="singerA" /> <!-- 추가 -->
			<ref bean="singerB" /> <!-- 추가 -->
		</list>
	</property>
</bean>
```
#### [추가 코드]
```xml
<!-- 두 개의 곡 bean을 생성한다. -->
<bean id="song1" class="com.example.springidol.Song"
	p:title="Bohemian Rhapsody"
	p:artist="Queen"/> <!-- "p"namespace를 이용한 property설정 -->
<bean id="song2" class="com.example.springidol.Song">
	<property name="title" value="Square"/>
	<property name="artist" value="Yerin Baek"/>
</bean> 
	
<!-- 두 명의 가수 bean을 생성해 경연에 참가시킨다. -->
<bean id="singerA" class="com.example.springidol.Singer"
	c:name="Chris"
	c:song-ref="song1"/>
<bean id="singerB" class="com.example.springidol.Singer"
	c:name="Jain"
	c:song="#{singerA.song}"/> <!-- singerB는 항상 singerA가 선택한 곡을 따라 부른다 -->
```
#### [실행 결과]
\-----------------------  
<Performance #4>  
Chris is singing a song "Bohemian Rhapsody" by "Queen".  
\-----------------------  
<Performance #5>  
Jain is singing a song "Bohemian Rhapsody" by "Queen".  
\-----------------------   
  
## 5번
### ⑴ Encore.java (추가된 클래스) 
```java
package com.example.springidol;

public class Encore {
	private Performer encorePerformer; // 공연 참가자들 중 한 명이 앵콜 공연
	
	public Encore() {
	}
	
	// setter method를 이용한 bean 생성
	public void setEncorePerformer(Performer encorePerformer) {
		this.encorePerformer = encorePerformer;
	}
	
	public void execute() {
		System.out.println("<Encore Performance>");
		encorePerformer.perform();
	}
}
```
### ⑵ spring-idol.xml
#### [추가 코드]
```xml
<!-- 앵콜 공연을 위한 encore bean을 설정한다. 앵콜 공연자는 공연 참가자들 중 임의로 한 명이 선택되도록 한다. -->	
<bean id="encore" class="com.example.springidol.Encore"
	p:encorePerformer="#{springIdol.performers[T(java.lang.Math).random()*springIdol.performers.length]}"/>
```
   
## 6번
### SpringIdolMain.java 
#### [수정 코드]
```java
public class SpringIdolMain {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-idol.xml");

		TalentCompetition competition = ctx.getBean("springIdol", TalentCompetition.class);
		Encore encore = ctx.getBean("encore", Encore.class); // 추가 코드
		competition.run();
		encore.execute(); // 추가 코드: 참가자들의 공연이 모두 끝난 후 앵콜 공연 실시하기 위해
		ctx.close();
	}
}
```
#### [실행 결과] 
#### ex1) kenny가 앵콜 공연 할 때
<Encore Performance>  
Playing Jingle Bells : STRUM STRUM STRUM~~  
 
#### ex2) Jain이 앵콜 공연 할 때
<Encore Performance>  
Jain is singing a song "Bohemian Rhapsody" by "Queen".  
