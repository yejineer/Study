1.
SELECT ename, deptno, job
FROM emp
WHERE deptno IN (20, 30)
AND ename LIKE '%AR%';
.
▶ 결과
ENAME          DEPTNO      JOB      
---------- --------------- -----------
WARD                    30  SALESMAN  
MARTIN                 30   SALESMAN 
.
.
2-(a)
SELECT count(*) "사원 수"
FROM dept d, emp e
WHERE e.deptno = d.deptno
AND d.dname = 'RESEARCH'
AND e.sal <= 2000;
.
2-(b)
SELECT count(*) "사원 수"
FROM emp
WHERE deptno IN (SELECT deptno FROM dept WHERE dname='RESEARCH')
AND sal <= 2000;
.
▶ 결과
   사원 수
----------
         1
.
3.
SELECT ename, sal, NVL(comm, 0), sal*12+NVL(comm, 0)
FROM emp e, salgrade s
WHERE e.sal >= s.losal AND e.sal <= s.hisal
AND s.grade=5
AND NVL(comm, 0) < 500;
.
▶ 결과
ENAME             SAL  NVL(COMM,0)    SAL*12+NVL(COMM,0)
---------- ------------ -----------------  --------------------------
KING             5000           0              60000
.
.
4.
SELECT ename, TO_CHAR(hiredate, 'YYYY"년" MM"월" DD"일"') hiredate, TRUNC(MONTHS_BETWEEN(SYSDATE, hiredate)/12, 0) as "SERVICE OF YEARS", TO_CHAR(ADD_MONTHS(hiredate, 360), 'YYYY"년" MM"월" DD"일"') retiredate
FROM emp
order by hiredate;
.
▶ 결과
ENAME      HIREDATE         SERVICE OF YEARS RETIREDATE      
---------- ---------------- ---------------- ----------------
SMITH      1980년 12월 17일               38 2010년 12월 17일
ALLEN      1981년 02월 20일               38 2011년 02월 20일
WARD       1981년 02월 22일               38 2011년 02월 22일
JONES      1981년 04월 02일               38 2011년 04월 02일
BLAKE      1981년 05월 01일               38 2011년 05월 01일
CLARK      1981년 06월 09일               38 2011년 06월 09일
TURNER     1981년 09월 08일               38 2011년 09월 08일
MARTIN     1981년 09월 28일               37 2011년 09월 28일
KING       1981년 11월 17일               37 2011년 11월 17일
JAMES      1981년 12월 03일               37 2011년 12월 03일
FORD       1981년 12월 03일               37 2011년 12월 03일
.
ENAME      HIREDATE         SERVICE OF YEARS RETIREDATE      
---------- ---------------- ---------------- ----------------
MILLER     1982년 01월 23일               37 2012년 01월 23일
.
12개 행이 선택되었습니다. 
.
5.
SELECT deptno, job, count(*) "사원 수", avg(sal)
FROM emp
group by deptno, job
order by deptno;
.
▶ 결과
 DEPTNO JOB         사원 수   AVG(SAL)
---------- --------- ---------- ----------
        10 CLERK              1       1300
        10 MANAGER         1       2450
        10 PRESIDENT       1       5000
        20 ANALYST           1       3000
        20 CLERK              1        800
        20 MANAGER         1       2975
        30 CLERK              1        950
        30 MANAGER         1       2850
        30 SALESMAN        4       1400
.
9개 행이 선택되었습니다. 
..
6-(a)
SELECT d.dname, avg(sal)
FROM dept d, emp e
WHERE d.deptno = e.deptno
GROUP BY d.dname
HAVING count(e.ename) >= 2;
.
6-(b)
SELECT d.dname, avg(sal)
FROM dept d, emp e
WHERE d.deptno = e.deptno
AND (SELECT count(*)
        FROM emp)>=2
        GROUP BY d.dname;
.
▶ 결과
DNAME            AVG(SAL)
--------------    -----------------
ACCOUNTING     2916.66667
RESEARCH         2258.33333
SALES               1566.66667
.
.
7.
SELECT d.dname, count(e.ename), max(sal)
FROM emp e, dept d
WHERE d.deptno = e.deptno(+)
GROUP BY d.dname;
.
▶ 결과
DNAME            COUNT(E.ENAME)   MAX(SAL)
--------------   --------------            ----------
ACCOUNTING                  3                  5000
OPERATIONS                   0           
RESEARCH                      3                  3000
SALES                             6                  2850
.
.
8.
SELECT e1.ename name, e2.ename coworker
FROM emp e1, emp e2
WHERE e1.deptno = e2.deptno AND e1.ename != e2.ename
ORDER BY e1.ename;
.
(LISTAGG 사용시)
▶ 결과
NAME       COWORKER  
---------- ----------
ALLEN      JAMES     
ALLEN      WARD      
ALLEN      TURNER    
ALLEN      BLAKE     
ALLEN      MARTIN    
BLAKE      WARD      
BLAKE      TURNER    
BLAKE      ALLEN     
BLAKE      MARTIN    
BLAKE      JAMES     
.
(중략)
.
WARD       ALLEN     
WARD       BLAKE     
WARD       TURNER    
WARD       JAMES     
WARD       MARTIN    
.
42개 행이 선택되었습니다. 
.
9.
SELECT ename, sal, (CASE WHEN sal<1000 THEN sal*0.01 WHEN sal < 2000 THEN sal*0.015 ELSE sal*0.02 END) "DEDUCTION"
FROM emp
ORDER BY sal DESC;
.
▶ 결과
ENAME             SAL  DEDUCTION
---------- ---------- ----------
KING             5000        100
FORD            3000         60
JONES           2975       59.5
BLAKE           2850         57
CLARK           2450         49
ALLEN            1600         24
TURNER         1500       22.5
MILLER           1300       19.5
MARTIN          1250      18.75
WARD             1250      18.75
JAMES             950        9.5
.
ENAME             SAL  DEDUCTION
---------- ---------- ----------
SMITH             800          8
.
12개 행이 선택되었습니다.
