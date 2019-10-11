# 선미 문제  
  
### Q1. SQL 문제  
1) 왼쪽 또는 오른쪽에서 문자를 삭제하는 함수는? **TRIM**  
2) comm이 NULL값일 경우 0으로 변환하는 방법은? **NVL(comm, 0)**  
3) ename과 ‘is a’와 job 이 세 컬럼을 하나의 컬럼으로 연결되어 출력하려면
쿼리가 어떻게 나올지 쓰시오. (ename과 job은 모두 emp 테이블에 있다.)  
```sql
SELECT ename || ‘ is a ’ || job
FROM emp;
```    
  
### Q2. DDL & DML  
■ DDL(Data Definition Language) 문제  
1) Column 이름 변경   
emp01 table에 있는 ename을 emp_name으로 변경하는 쿼리를 작성하시오.  
```sql
ALTER TABLE emp01
RENAME COLUMN ename TO emp_name;
```
  
2) Subquery를 이용한 table 생성  
emp와 동일한 구조의 table(emp02) 생성 및 데이터 복사하는 쿼리를 작성하시오.  
```sql
CRAETE TABLE emp02
AS SELECT * FROM emp;
```
  
### ■ DML(Data Manipulation Language) 문제  
1) Subquery를 이용하여 변경할 값 생성  
부서번호가 20인 부서의 부서명과 지역을 부서번호가 40인 부서의 부서명과 지역으로 변경하는 쿼리를 작성하시오.  
```sql
UPDATE dept
SET (dname, loc) = (SELECT dname, loc
FROM dept
WHERE deptno = 40)
WHERE deptno = 20;
```
  
### Q3. View Options  
View를 통해 기반 테이블의 데이터를 변경할 수 없도록 아래 빈칸을 채우시오.  
```sql
CREATE VIEW emp_chk30 AS
SELECT empno, ename, deptno
FROM emp
WHERE e.deptno = 30 WITH READ ONLY;
``` 
  
### Q4.  
1) Supertype 개체로의 통합을 뭐라 하는가? **RollUp**  
같은 맥락으로, Subtype 개체로의 분할은 뭐라 하는가? **RollDown**  
    
2) 명명법에 대해 세 가지 쓰시오.    
① **실제 업무에서 사용하는 용어 사용 (ex. 학생번호 -> 학번)**  
② **약어보다는 구체적 명칭 사용**  
③ **단수 명사 사용 (ex. 수강생들 -> 수강생)**
  
  
# 선미 문제 풀이  
### A1.  
1) **TRIM**  
2) **NVL(comm, 0)**  
3)  
```sql
SELECT ename || 'is a' || job
FROM emp
```
  
### A2.   
■ DDL  
1)
```sql
ALTER TABLE emp01
RENAME COLUMN ename TO emp_name;
```
  
2)
```sql
CREATE TABLE emp02 AS SELECT * FROM emp;
```
   
■ DML    
1)   
```sql
UPDATE dept
SET (dname, location) = (SELECT dname, location
                       FROM dept
                       WHERE deptno = 40)
WHERE deptno = 20
```
  
### A3.  
**WITH CHECK OPTION**  
  
### A4.     
1) **정규화, 역정규화**  
2)   
    1.  **단어를 줄이지 말고 쓰기**   
    2.  **실무에서 사용하는 단어로 쓰기**  
    3.  **단수로 쓰기 (?)**   
    
      
# 내 문제
### Q1.  
SYS 사용자가 소유하는 테이블로, 모든 사용자들이 접근 가능하며 특정 테이블에 관계없는 연산이나 함수의 실행 결과를 한 번만 출력하고자 할 때 사용하는 테이블은 무엇인가?

   
### Q2. SQL Functions  
    
⑴.  _를 포함하는 문자열(ename)을 검색하는 SQL Query의 WHERE절을 완성하시오.  

```sql
SELECT empno, ename
FROM emp
WHERE ename ___________________________ ;
```

  
⑵. 다음 결과를 위한 SQL Query를 작성하시오. (DUAL 테이블 사용)  
실행결과: #####ABCDE  
      
    
⑶. 두 날짜 사이의 개월 수를 계산하기 위해 사용하는 함수는?  
    
    


### Q3.  
dept라는  테이블을 삭제하되, 테이블 자체는 삭제하지 않고 데이터만 삭제하는 SQL Query를 작성하시오.  
(주의: 즉시 commit되므로 rollback되지 않음)   
    
    

### Q4.
식별 관계과 비식별 관계를 설명하시오. (선의 모양까지 포함)
  
  
# 내 문제 답안
### A1.  
DUAL
  
### A2-1.  
‘%＼_%’ ESCAPE ‘＼’   
  
### A2-2.  
```sql
SELECT LPAD(‘ABCDE’, 10, ‘#’)
FROM DUAL;
```
  
### A2-3.  
MONTHS_BETWEEN()  
  
### A3.  
TRUNCATE TABLE dept;  
  
### A4.  
식별:  
- 특정 개체 타입에 속한 각 개체가 다른 개체 타입에 속한 하나의 개체와 반드시 관계를 갖고, 자식 개체가 부모 개체에 의해 식별되는 경우.  
- 실선으로 표현.  
  
비식별:  
- 특정 개체 타입에 속한 개체가 다른 개체 타입에 속한 개체와 선택적으로 관계를 갖고, 상대 개체에 의해 식별되지 않는 경우.  
- 점선으로 표현.  
