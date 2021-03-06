# Chapter 02. 변수와 자료형
## Q9. a = “3.5”, b = “1.5”일 때, print(a+b)의 실행 결과는?
① 5		② 3.51.5	③ a+b		④ ab		⑤ 2  
  
**답: ②**    
> a와 b 모두 문자열이므로 +는 두 문자열을 연결하는 역할을 한다.따라서print(a+b)의 실행 결과는 3.51.5이다.  
  
## Q10. 다음과 같이 코드를 작성했을 때, 실행 결과로 알맞은 것은?
```python
a = ‘3’
b = float(a)
print(b ** int(a))
```
① TypeError &nbsp;&nbsp;&nbsp; ② '27.0'	&nbsp;&nbsp;&nbsp; ③ 27.0	&nbsp;&nbsp;&nbsp;	④ 27	&nbsp;&nbsp;&nbsp;	⑤ '27  

**답: ③**  
> 연산자 \*\*는 거듭제곱을 의미한다.
> b는 3.0이고 int(a)는 3이므로 b ** int(a)는 이다. 따라서 실행결과는 27.0이다.  

# Chapter 03. 화면 입출력과 리스트 
## Q3. 다음 코드의 실행 결과를 쓰시오.
```python
first=["egg", "salad", "bread", "soup", "canafe"]
second=["fish", "lamb", "pork", "beef", "chicken"]
third=["apple", "banana", "orange", "grape", "mango"]

order = [first, second, third]
john = [order[0][:-2], second[1::3], third[0]]
del john[2]
john.extend([order[2][0:1]])
print(john)
```
  
**실행 결과: [['egg', 'salad', 'bread'], ['lamb', 'chicken'], ['apple']]**
> 풀이)
> 처음에 john = [["egg", "salad", "bread"], ["lamb","chicken"], [“apple”]]이다.  
> del john[2]를 하면 john = [["egg", "salad", "bread"], ["lamb","chicken"]]이다.  
> john.extend([order[2][0:1]])를 하면 john = [["egg", "salad", "bread"], ["lamb","chicken"], [“apple”]]이다.  
> extend()는 매개변수 안의 리스트를 직접 덧붙이기 때문이다.  
  
## Q4. 다음 코드의 실행 결과를 쓰시오.
```python
list_a = [3, 2, 1, 4]
list_b = list_a.sort()
print(list_a, list_b)
```
  
**실행 결과: [1, 2, 3, 4] None**  
> sort()는 오름차순으로 리스트의 요소를 정렬한다. 하지만 반환 값이 없다.  
> 정렬된 새 리스트를 반환하려면 list_b = sorted(list_a)라고 해야 한다.  
  
## Q8. 다음 코드의 실행 결과를 쓰시오.  
```python
num = [1, 2, 3, 4]
print(num * 2)
```
  
**실행 결과: [1, 2, 3, 4, 1, 2, 3, 4]**   
> 리스트 * 2 는 요소를 2 번 반복하는 것이다.  
> listmulti = num * 2 라고 작성해 반환된 리스트를 저장할 수 있다.  
  
## Q10. 다음과 같이 코드를 작성했을 때, 실행 결과로 알맞은 것은?
```python
list_a = ['Hankook', 'University', 'is', 'an', 'academic', 'institute',
       'located', 'in', 'South Korea']
list_b = []
for i in range(len(list_a)):
    if i % 2 != 1:
        list_b.append(list_a[i])
print(list_b)
```  
① None  
② Error  
③ ['Hankook', 'is', 'academic', 'located', 'South Korea']  
④ ['University', 'an', 'institute', 'in']  
⑤ ['Hankook', 'University', 'is', 'an', 'academic', 'institute', 'located', 'in', 'South Korea']  
  
**답: ③**  
> len(list_a)는 9이므로 ｉ는 0~8까지의 값을 갖는다.   
> ｉ가 0, 2, 4, ..., 8일 때 list_a[i]를 list_b의 끝에 덧붙여 추가한다.   
  
  
# Chapter 04. 조건문과 반복문   
## Q6. 다음 코드의 실행 결과를 쓰시오.
```sql
result = 0
for i in range(5, -5, -2):
    if i < -3:
        result += 1
    else:
        result -= 1
    
print(result)
```
**실행 결과: -5**  
> range(5, -5) = -4 ~ 5  
> 1) ｉ= 5	→ result -= 1		∴ result = –1  
> 2) ｉ= 3	→ result -= 1		∴ result = –2  
> 3) ｉ= 1	→ result -= 1		∴ result = –3  
> 4) ｉ= -1	→ result -= 1		∴ result = –4  
> 5) ｉ= -3	→ result -= 1		∴ result = –5  
