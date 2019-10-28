# 문제 설명
월요일에 원탁에 회의를 하려고 직원들이 모여 있다.  
회의 시작 전에 서로 악수를 하려고 하는데 팔이 겹치지 않게 하면서 모두 악수를 하는 방법의 수를 구하는 프로그램을 작성하시오.

# memoization을 활용한 재귀코드
```java
for (int i = 0; i < k; i++) {
			if (memo[2*i] == 0)
				memo[2*i] = handshaking(2*i, memo);
			if (memo[2*(k-1-i)] == 0)
				memo[2*(k-1-i)] = handshaking(2*(k-1-i), memo);
			count += memo[2*i] * memo[2*(k-1-i)];
		}
		return count;
```

