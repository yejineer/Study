# 실습 2: Auto-wiring, Java Code 기반 설정, Annotation 기반 설정
## 1
### spring-idol-autowire.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:util="http://www.springframework.org/schema/util"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
						http://www.springframework.org/schema/beans/spring-beans.xsd
						http://www.springframework.org/schema/util 
						http://www.springframework.org/schema/util/spring-util.xsd">

	<!-- ==================================================================== -->
	<!-- Spring Idol talent competition class -->
	<!-- ==================================================================== -->
	<!-- autowire="byType"으로 하면 Performer타입 bean 전부 찾음 -->
	<bean id="springIdol" class="com.example.springidol.SpringIdol"
		autowire="byType">
		<!-- <property name="performers"> 
			<list>
				<ref bean="duke" />
				<ref bean="kenny" />
				<ref bean="hank" />
				<ref bean="chris" />
				<ref bean="jain" />
			</list>
		</property> -->
	</bean>

	<!-- ==================================================================== -->
	<!-- Performers -->
	<!-- ==================================================================== -->

	<bean id="duke" class="com.example.springidol.PoeticJuggler"
		autowire="constructor">
		<!-- <constructor-arg ref="sonnet29" /> poet타입 bean이 sonnet29하나라 가능!-->
	</bean>

	<bean id="kenny" class="com.example.springidol.Instrumentalist"
		autowire="byName">
		<property name="song" value="Jingle Bells" />
		<!-- <property name="instrument" ref="guitar" /> -->
	</bean>

	<bean id="hank" class="com.example.springidol.OneManBand"
		autowire="byName">
		<!-- <property name="instruments" ref="instruments_of_hank" /> -->
	</bean>

	<bean id="chris" class="com.example.springidol.Singer"
		autowire="constructor">
        <constructor-arg value="Chris Kim" />
        <!-- <constructor-arg ref="bohemian" /> -->
	</bean>
	
	<bean id="jain" class="com.example.springidol.Singer">
		<constructor-arg value="Jain Lee" />
		<constructor-arg value="#{chris.song}" /> <!-- 생략 불가 -->
	</bean>
	
	<!-- ==================================================================== -->
	<!-- Instruments performers can use -->
	<!-- ==================================================================== -->
	<bean id="harmonica" class="com.example.springidol.Harmonica" />
	<bean id="cymbal" class="com.example.springidol.Cymbal" />
	<bean id="instrument" class="com.example.springidol.Guitar" />
	<bean id="saxophone" class="com.example.springidol.Saxophone" />
	<bean id="piano" class="com.example.springidol.Piano" />
		
	<util:properties id="instruments"> <!-- "hank"bean의 property속성 설정 위해 id값 변경-->
		<prop key="GUITAR">STRUM STRUM STRUM</prop>
		<prop key="CYMBAL">CRASH CRASH CRASH</prop>
		<prop key="HARMONICA">HUM HUM HUM</prop>
	</util:properties>
		
	<bean id="sonnet29" class="com.example.springidol.Sonnet29" />
	
	<!-- 실습 #1의  Singer, Song, Encore bean -->
	<bean id="bohemian" class="com.example.springidol.Song"
		p:title="Bohemian Rhapsody" p:artist="Queen" />
 		
 	<!-- "chris"bean의 autowire을 위해 동일한 Song타입 객체는 하나여야 함. 
 	<bean id="hero" class="com.example.springidol.Song"
		p:title="Hero" p:artist="Mariah Carey" />  -->          	
	
	<bean id="encore" class="com.example.springidol.Encore">
		<property name="performer"
			value="#{springIdol.performers[T(java.lang.Math).random()*springIdol.performers.length]}" />
	</bean>	

</beans>
```
  
## 2
### com.example.springidol.javaconf1 패키지
#### SpringConfig 클래스
```java
package com.example.springidol.javaconf1;

import java.util.Properties;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springidol.Encore;
import com.example.springidol.Guitar;
import com.example.springidol.Instrumentalist;
import com.example.springidol.OneManBand;
import com.example.springidol.Performer;
import com.example.springidol.Poem;
import com.example.springidol.PoeticJuggler;
import com.example.springidol.Saxophone;
import com.example.springidol.Singer;
import com.example.springidol.Song;
import com.example.springidol.Sonnet29;
import com.example.springidol.SpringIdol;

@Configuration
public class SpringConfig {

	@Bean
	public Saxophone saxophone() {	  	// method 이름을 bean의 ID로 사용 ("saxophone") 
		return new Saxophone();			// Saxophone type bean 생성  
	}
	
	@Bean
	public Guitar guitar() {	    
		return new Guitar();		
	}
		
	@Bean
	public Poem sonnet29() {
		return new Sonnet29();
	}
	
	@Bean
	public PoeticJuggler duke() {
		return new PoeticJuggler(5, sonnet29());
	}
	
	@Bean(name="kenny")				// name 속성을 통해 bean ID 지정 가능 
	public Instrumentalist instr() {
		Instrumentalist instr = new Instrumentalist();
		instr.setSong("Jingle Bells");	
//		instr.setInstrument(saxophone());		// Setter-based DI
		instr.setInstrument(guitar());
		return instr;
	} 
	
	@Bean		
	public OneManBand hank() {
		Properties props = new Properties();
		props.setProperty("GUITAR", "STRUM STRUM STRUM"); //guitar 추가
		props.setProperty("CYMBAL", "CRASH CRASH CRASH");
		props.setProperty("HARMONICA", "HUM HUM HUM");
		OneManBand omb = new OneManBand();		
		omb.setInstruments(props);
		return omb;
	}		
	
	/* Song타입 bean 생성*/
	@Bean(name="bohemian")
	public Song song1() {
		Song song = new Song();
		song.setTitle("Bohemian Rhapsody");
		song.setArtist("Queen");
		return song;
	}
	@Bean(name="hero")
	public Song song2() {
		Song song = new Song();
		song.setTitle("Hero");
		song.setArtist("Queen");
		return song;
	}
	
	/* Singer타입 bean 생성*/
	@Bean
	public Singer chris() {
		return new Singer("Chris Kim", song1());
	}
	@Bean
	public Singer jain() {
		return new Singer("Jain Lee", chris().getSong());
	}
	
	@Bean
	public SpringIdol springIdol() {
		SpringIdol si = new SpringIdol();
		si.setPerformers(new Performer[]{duke(), instr(), hank(), chris(), jain()});
		return si;		
	}
	
	@Bean
	public Encore encore() {
		Encore encore = new Encore();
		Random r = new Random();
		encore.setPerformer(springIdol().getPerformers()[r.nextInt(springIdol().getPerformers().length)]);
		return encore;		
	}
}
```

### ⑴ com.example.springidol.javaconf3 패키지
#### ⓐ SpringConf1 클래스
```java
package com.example.springidol.javaconf3;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springidol.Guitar;
import com.example.springidol.Instrumentalist;
import com.example.springidol.OneManBand;
import com.example.springidol.Poem;
import com.example.springidol.PoeticJuggler;
import com.example.springidol.Saxophone;
import com.example.springidol.Singer;
import com.example.springidol.Song;
import com.example.springidol.Sonnet29;

@Configuration
public class SpringConf1 {
	
	@Bean
	public Saxophone saxophone() {	  	// method 이름을 bean의 ID로 사용 ("saxophone") 
		return new Saxophone();			// Saxophone type bean 생성  
	}
	
	@Bean
	public Guitar guitar() {	    
		return new Guitar();		
	}
		
	@Bean
	public Poem sonnet29() {
		return new Sonnet29();
	}
	
	@Bean
	public PoeticJuggler duke() {
		return new PoeticJuggler(5, sonnet29());
	}
	
	@Bean(name="kenny")				// name 속성을 통해 bean ID 지정 가능 
	public Instrumentalist instr() {
		Instrumentalist instr = new Instrumentalist();
		instr.setSong("Jingle Bells");	
//		instr.setInstrument(saxophone());		// Setter-based DI
		instr.setInstrument(guitar());
		return instr;
	} 
	
	@Bean		
	public OneManBand hank() {
		Properties props = new Properties();
		props.setProperty("GUITAR", "STRUM STRUM STRUM"); //guitar 추가
		props.setProperty("CYMBAL", "CRASH CRASH CRASH");
		props.setProperty("HARMONICA", "HUM HUM HUM");
		OneManBand omb = new OneManBand();		
		omb.setInstruments(props);
		return omb;
	}		
	
	/* Song타입 bean 생성*/
	@Bean(name="bohemian")
	public Song song1() {
		Song song = new Song();
		song.setTitle("Bohemian Rhapsody");
		song.setArtist("Queen");
		return song;
	}
	@Bean(name="hero")
	public Song song2() {
		Song song = new Song();
		song.setTitle("Hero");
		song.setArtist("Queen");
		return song;
	}
	
	/* Singer타입 bean 생성*/
	@Bean
	public Singer chris() {
		return new Singer("Chris Kim", song1());
	}
	@Bean
	public Singer jain() {
		return new Singer("Jain Lee", chris().getSong());
	}
}
```
#### SpringConf2 클래스
```java
package com.example.springidol.javaconf3;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springidol.Encore;
import com.example.springidol.Instrumentalist;
import com.example.springidol.OneManBand;
import com.example.springidol.Performer;
import com.example.springidol.PoeticJuggler;
import com.example.springidol.Singer;
import com.example.springidol.SpringIdol;

@Configuration
public class SpringConf2 {
	@Autowired
	private PoeticJuggler duke;			// PoeticJuggler 타입 객체가 자동 주입됨
	@Autowired
	private Instrumentalist instr;		// Instrumentalist 타입 객체가 자동 주입됨
	@Autowired
	private OneManBand hank;			// OneManBand 타입 객체가 자동 주입됨
	@Autowired
	private Singer chris;				// Singer 타입 객체가 자동 주입됨
	@Autowired
	private Singer jain;				// Singer 타입 객체가 자동 주입됨
	
	@Bean
	public SpringIdol springIdol() {
		SpringIdol si = new SpringIdol();
		si.setPerformers(new Performer[]{duke, instr, hank, chris, jain});	// 위 필드를 통해 객체 참조
		return si;		
	}
	
	@Bean
	public Encore encore() {
		Encore encore = new Encore();
		Random r = new Random();
		encore.setPerformer(springIdol().getPerformers()[r.nextInt(springIdol().getPerformers().length)]);
		return encore;				
	}
}
```
