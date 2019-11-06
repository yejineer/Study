# Vladmir Yroslavskiy's algorithm
- Dual pivot quicksort + Insertion sort algorithm
- For small subarrays, use the insertion sort algorithm
  - Why? 어느 정도 수 이하는 insertion sort를 쓰는 것이 더 빠름
    
## 수행 시간 측정
- System.currentTimeMillis() 이용
```java
long startTime = System.currentTimeMillis();

// 수행 시간 측정하려는 작업 구현

long endTime = System.currentTimeMillis();

long elapsedTime = startTime - endTime;
System.out.println("수행 시간: " + elapsedTime + " ms");
```

# References
- [geeksforgeeks/dual-pivot-quicksort](https://www.geeksforgeeks.org/dual-pivot-quicksort/)  
- [gwpark.tistory.com/QuickSort-DualPivotPartitioning](https://gwpark.tistory.com/1743)
- [paper-Vladimir Yaroslavskiy](https://codeblab.com/wp-content/uploads/2009/09/DualPivotQuicksort.pdf)
- [Average Case Analysis of Java 7’s Dual Pivot Quicksort](https://www.slideshare.net/sebawild/average-case-analysis-of-java-7s-dual-pivot-quicksort)
