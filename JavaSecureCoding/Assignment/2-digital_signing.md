# Practice03
## 설명
- 수업시간에 설명된 순서에 따라 암호화와 복호화를 수행하는 프로그램을 작성하여라.  
  - **Practice03A**  
    - 암호화 : public key  
    - 복호화 : private key  
  - **Practice03B**  
    - 암호화 : private key  
    - 복호화 : public key 
- 실행 결과  
  ![image](https://user-images.githubusercontent.com/50271884/80591755-2f639800-8a59-11ea-93ce-fdeb4b0c20da.png)  
    
- 문제 해결
  - byte[]을 16진수로 출력 : String.format(...) 메소드 활용  
  - byte[]을 문자열로 출력 : 생성자 String(byte[]) 활용  
  
## 작성한 프로그램 코드
- **Practice03A**  
```java
package cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Practice03A {

	public static void main(String[] args) throws Exception {

		// Creating KeyPair generator object
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		// Getting the public key from the key pair
		PublicKey publicKey = pair.getPublic();

		// Creating a Cipher object
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		// Initializing a Cipher object
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);

		// Adding data to the cipher
		String plainTxt = "Software Security of Dongduk";
		byte[] input = plainTxt.getBytes();
		cipher.update(input);	//String이든 int든 byte형 배열로 바꿔준 다음에 update() 호출
		// Encrypting the data
		byte[] cipherTxt = cipher.doFinal();

		// Get the private key
		PrivateKey privateKey = pair.getPrivate();
		//Initializing the same cipher for decryption
		cipher.init(Cipher.DECRYPT_MODE, privateKey);

		//Decrypting the text
		byte[] decipheredTxt = cipher.doFinal(cipherTxt);

		System.out.println("Length of Input: " + input.length);
		System.out.println("Length of Encrypted Data: " + cipherTxt.length);
		System.out.println("[Encrypted Data]");
		for (int i = 0; i < cipherTxt.length; i++) {
			System.out.print(String.format("%02x", cipherTxt[i]) + "\t");
			if (i % 15 == 14)
				System.out.println();
		}
		System.out.println("\n[Decrypted Data]");
		System.out.println(new String(decipheredTxt));

	}

}
```

- **Practice03B** 
```java
package cryptography;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class Practice03B {

	public static void main(String[] args) throws Exception {

		// Creating KeyPair generator object
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// Initializing the KeyPairGenerator
		keyPairGen.initialize(2048);
		//Generate the pair of keys
		KeyPair pair = keyPairGen.generateKeyPair();
		// Getting the private key from the key pair
		PrivateKey privateKey = pair.getPrivate();

		// Creating a Cipher object
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		// Initializing a Cipher object
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		// Adding data to the cipher
		String plainTxt = "Software Security of Dongduk";
		byte[] input = plainTxt.getBytes();
		cipher.update(input);	//String이든 int든 byte형 배열로 바꿔준 다음에 update() 호출
		// Encrypting the data
		byte[] cipherTxt = cipher.doFinal();

		// Get the public key
		PublicKey publicKey = pair.getPublic();
		//Initializing the same cipher for decryption
		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		//Decrypting the text
		byte[] decipheredTxt = cipher.doFinal(cipherTxt);

		System.out.println("Length of Input: " + input.length);
		System.out.println("Length of Encrypted Data: " + cipherTxt.length);
		System.out.println("[Encrypted Data]");
		for (int i = 0; i < cipherTxt.length; i++) {
			System.out.print(String.format("%02x", cipherTxt[i]) + "\t");
			if (i % 15 == 14)
				System.out.println();
		}
		System.out.println("\n[Decrypted Data]");
		System.out.println(new String(decipheredTxt));
	}

}
```

# Practice04
## 설명
- Java API를 활용하여 암호화에 사용되는 클래스/메소드 매개변수를 정리하여 문서 파일로 제출하세요.  
- 각 단계별로 사용되는 매개변수 정리
  - KeyPairGenerator.getInstnace(…) 메소드
  - KeyPairGenerator.initialize(…) 메소드
  - Cipher.getInstance(…) 메소드 
  
## 정리
- 암호화에 사용되는 클래스
  - java.security.KeyPairGenerator
  - javax.crypto.Cipher

### 참고 사이트
- [클래스 KeyPairGenerator API 설명](http://cris.joongbu.ac.kr/course/java/api/java/security/KeyPairGenerator.html)
# Practice05
## 설명
- 사용자가 입력한 메시지를 암호화하고 이를 다시 복호화하여 보여주는 프로그램을 작성하여라.
- 문제 조건
  - AES 알고리즘을 사용
  - 예제 프로그램에서 주어진 keyValue 사용
- 실행 예시
  - 메시지를 입력하세요: 오늘도 화창한 날씨입니다.
  - 암호화 결과 : 93-E1-B6-32-BB-10-FD-06-CE-83-94-8F-B7-E2-C1-0C-7FB1-D2-28-6A-E1-ED-5F-F3-9D-95-F5-FA-34-39-58-
  - 복호화 결과 : 오늘도 화창한 날씨입니다.
- 문제 힌트
  - byte[] 출력을 위해서 String.format(…) 메소드 활용

# Practice06
## 설명
- [API 정리] 다음 자바 보안관련 클래스의 사용 가능한 매개변수 옵션과 기능을 정리하여 문서로 제출하세요.
  - class Signature
    - Signature.getInstance(…)
  - interface Key
    - Known sub-interfaces : PublicKey, PrivateKey, SecretKey
    - String getAlgorithm()
    - byte[] getEncoded()
    - String getFormat()
  - class KeyPair
    - Constructor & methods

# Practice07
## 설명
- 예제 프로그램을 응용하여 다음을 수행하는 프로그램을 작성하여라.
  - 길이가 1024인 public/private key pair를 생성하고,
  - 생성된 key pair를 이용하여 이클래스에 주어진 파일에 대한 전자 서명을 생성하고, 
  - 생성된 전자서명을 검증하여라.
- 문제해결
  - 생성된 KeyPair를 파일로 저장하는 방법은 다음 시간에 다룸
  - Key algorithm: “RSA”
  - Sign algorithm: “SHA1withRSA”
- 참고
  - [Signature(서명) 은 무엇이며 용도는?](https://xunaoo.tistory.com/9)
  - [RSA를 이용한 암호화와 서명](https://www.holaxprogramming.com/2017/06/12/encryption-with-rsa/)
