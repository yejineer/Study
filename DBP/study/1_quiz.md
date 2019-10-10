# 선미 문제  
  
Q1. SQL 문제  
1) 왼쪽 또는 오른쪽에서 문자를 삭제하는 함수는? TRIM  
2) comm이 NULL값일 경우 0으로 변환하는 방법은? NVL(comm, 0)  
3) ename과 ‘is a’와 job 이 세 컬럼을 하나의 컬럼으로 연결되어 출력하려면
쿼리가 어떻게 나올지 쓰시오. (ename과 job은 모두 emp 테이블에 있다.)  
```sql
SELECT ename || ‘ is a ’ || job
FROM emp;
```    
  
Q2. DDL & DML
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
  
■ DML(Data Manipulation Language) 문제  
1) Subquery를 이용하여 변경할 값 생성 
부서번호가 20인 부서의 부서명과 지역을 부서번호가 40인 부서의 부서명과 지역으로 변경하는 쿼리를 작성하시오.  
```sql
UPDATE dept
SET (dname, loc) = (SELECT dname, loc
FROM dept
WHERE deptno = 40)
WHERE deptno = 20;
```
  
Q3. View Options  
View를 통해 기반 테이블의 데이터를 변경할 수 없도록 아래 빈칸을 채우시오.  
```sql
CREATE VIEW emp_chk30 AS
SELECT empno, ename, deptno
FROM emp
WHERE e.deptno = 30 WITH READ ONLY;
``` 
  
Q4.  
1) Supertype 개체로의 통합을 뭐라 하는가? **RollUp**
같은 맥락으로, Subtype 개체로의 분할은 뭐라 하는가? **RollDown**
  
2) 명명법에 대해 세 가지 쓰시오.  
① **실제 업무에서 사용하는 용어 사용 (ex. 학생번호 -> 학번)**  
② **약어보다는 구체적 명칭 사용**  
③ 단수 명사 사용 (ex. 수강생들 -> 수강생)
