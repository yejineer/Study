[>> 문제 보러가기](https://www.hackerrank.com/challenges/compare-the-triplets/problem?h_r=internal-search)  

## 문제 설명
Alice and Bob each created one problem for HackerRank.  
A reviewer rates the two challenges, awarding points on a scale from 1 to 100 for three categories: problem clarity, originality, and difficulty.    
    
The rating for Alice's challenge is the triplet a = (a[0], a[1], a[2]), and the rating for Bob's challenge is the triplet b = (b[0], b[1], b[2]).  
   
The task is to find their comparison points by comparing a[0] with b[0], a[1] with b[1], and a[2] with b[2].   
  
>If a[i] > b[i], then Alice is awarded 1 point.  
>If a[i] < b[i], then Bob is awarded 1 point.  
>If a[i] = b[i], then neither person receives a point.  
>Comparison points is the total points a person earned.  
  
**Given a and b, determine their respective comparison points.**  
  
## 예시
**a = [1, 2, 3]**    
**b = [3, 2, 1]**    
>For elements *0*, Bob is awarded a point because a[0] .   
>For the equal elements a[1] and b[1], no points are earned.  
>Finally, for elements 2, a[2] > b[2] so Alice receives a point.  
>The return array is [1, 1] with Alice's score first and Bob's second.   
  
## 소스 코드

```java
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CompareTriplets {

    // Complete the compareTriplets function below.
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(0);
    	for (int i = 0; i < a.size(); i++) {
        	if (a.get(i) > b.get(i))
        		list.set(0, list.get(0) + 1);
        	else if (a.get(i) < b.get(i))
        		list.set(1, list.get(1) + 1);
        }
    	return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = compareTriplets(a, b);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

```
