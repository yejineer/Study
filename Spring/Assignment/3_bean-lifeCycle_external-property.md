# 실습 3: bean life-cycle 관리, 외부 property 설정
## 1번
### [수정 코드]
#### SpringIdol.java
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
			System.out.print("<Performance #" + (i+1));
			Performer performer = performers[i];
			System.out.println(": " + performer.getBeanName() + ">"); //이름 출력
			performer.perform();
			System.out.println("-----------------------");
		}
	}

}
```
#### Piano.java
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
#### spring-idol.xml
```java
<bean id="springIdol" class="com.example.springidol.SpringIdol"
		init-method="tunePiano"
		destroy-method="cleanPiano"/> <!-- 문제 1번 -->
```
#### [실행 결과]
SpringIdolMain starts.  
SpringIdol.setStageSize(): 무대 크기 = 200  
SpringIdol.tunePiano(): 피아노 조율  
Piano.tune(): Tuning the piano.  
<Performance #1>  
...(생략)...  
HARMONICA : HUM HUM HUM  
SpringIdol.cleanPiano(): 피아노 청소  
Piano.clean(): Cleaning the piano.  
  

  

  
