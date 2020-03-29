# 실습 1: XML 기반 Spring DI 설정
# 1.
### [수정 코드]
```xml
<bean id="kenny" class="com.example.springidol.Instrumentalist">
	<property name="song" value="Jingle Bells" />
	<property name="instrument" ref="guitar" />
</bean>
```
### [실행 결과]
Performance #2
Playing Jingle Bells : STRUM STRUM STRUM~~

# 2.
### [수정 코드]
```xml
<bean id="duke" class="com.example.springidol.PoeticJuggler"
	c:poem-ref="sonnet29" />

<bean id="kenny" class="com.example.springidol.Instrumentalist"
	p:song="Jingle Bells"
	p:instrument-ref="guitar" />
```

# 3.
## OneManBand.java
### 원본
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
### 수정
```java
import java.util.Map;

public class OneManBand implements Performer {
  public OneManBand() {
  }

  private Map<String, Instrument> instruments;
  public void setInstruments(Map<String, Instrument> instruments) {
  	this.instruments = instruments;
  }

  @Override
  public void perform() throws PerformanceException {
  	for (String key : instruments.keySet()) {
	    Instrument instrument = instruments.get(key);
	    instrument.play();
	  }
  }	
}
```
## spring-idol.xml
### 원본
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
### 수정
```xml
<bean id="hank" class="com.example.springidol.OneManBand">
	<property name="instruments">
		<map>
			<entry key="HARMONICA" value-ref="harmonica"/>
			<entry key="CYMBAL" value-ref="cymbal"/>
			<entry key="SAXOPHONE" value-ref="saxophone"/>
		</map>
	</property>
</bean>
```
## 실행 결과
Performance #3
HUM HUM HUM~~
CRASH CRASH CRASH~~
TOOT TOOT TOOT~~

# 4
## Song.java
```java
package com.example.springidol;

public class Song {
  private String title;
  private String artist;
	
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
## Singer.java
```java
package com.example.springidol;

public class Singer implements Performer {
  private String name;
  private Song song;
	
  public Singer(String name, Song song) {
	  this.name = name;
	  this.song = song;
  }
	
  @Override
  public void perform() throws PerformanceException {
  	System.out.print(name + " is singing a song " + song.getTitle() + " by " + song.getArtist() + ".");
  }
}
```
## spring-idol.xml
```xml
<!-- performers에 두 명의 가수 singerA와 singerB를 경연에 참가시킨다. -->
<bean id="springIdol" class="com.example.springidol.SpringIdol">
	<property name="performers">
		<list>
			<ref bean="duke" />
			<ref bean="kenny" />
			<ref bean="hank" />
			<ref bean="singerA" />
			<ref bean="singerB" />
		</list>
	</property>
</bean>

<!-- 두 개의 곡 bean을 생성한다. -->
<bean id="song1" class="com.example.springidol.Song"
	p:title="Bohemian Rhapsody"
	p:artist="Queen"/>
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
## 실행 결과
\-----------------------  
Performance #4  
Chris is singing a song "Bohemian Rhapsody" by "Queen".  
\-----------------------  
Performance #5  
Jain is singing a song "Bohemian Rhapsody" by "Queen".  
\-----------------------  
