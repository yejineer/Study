CREATE OR REPLACE PROCEDURE lab4b (
  dept_name IN dept1.dname%TYPE,     
  new_mgr IN dept1.manager%TYPE,
  comm_inc IN emp1.comm%TYPE)
IS
  /* 프로그램에서 사용할 변수들을 선언 */
  dept_no dept1.deptno%TYPE;
  mgr_no  dept1.manager%TYPE;
  emp_num INTEGER;
  emp     emp1%ROWTYPE;
  
  CURSOR C1 IS	  -- 사원 정보 출력을 위한 커서 선언
    SELECT empno, ename, job, sal, comm 
    FROM emp1 JOIN dept1 ON emp1.deptno = dept1.deptno 
    WHERE dname = dept_name; 	
    -- emp_rec C1%ROWTYPE;	-- 커서를 위한 레코드 변수 선언 필요 없음
BEGIN
  /* 2. 지정된 부서의 부서번호, 부서명, 관리자사번, 소속사원 수를 구해서 출력 */
  SELECT deptno, manager, COUNT(empno) INTO dept_no, mgr_no, emp_num
  FROM emp1 JOIN dept1 USING (deptno) 
  WHERE dname = dept_name
  GROUP BY deptno, manager;
	
  /* 3. 그 부서에 속한 모든 사원들에 대해 사번, 이름, 직무, 월급, 수당을 검색하여 출력 */
  dbms_output.put_line('부서번호: ' || dept_no || ', 부서명: ' || dept_name || 
                       ', 관리자사번: ' || mgr_no || ', 소속사원 수: ' || emp_num);
  dbms_output.put_line('사번    이름     직무     월급     수당');
  dbms_output.put_line('------------------------------------');
  
  /* Cursor for loop를 사용하여 검색 결과를 하나씩 출력하는 코드 */
  -- OPEN C1;			-- 필요없음
  -- LOOP 				
  --	FETCH C1 INTO emp_rec; 	
  --  	EXIT WHEN C1%NOTFOUND;	   
  FOR emp IN C1 LOOP
    dbms_output.put_line(RPAD(emp.empno,10) || RPAD(emp.ename,10) || 
                    RPAD(emp.job,10) || LPAD(emp.sal,10) || LPAD(emp.comm,10));
  END LOOP;
  -- CLOSE C1;		-- 필요없음
  dbms_output.put_line('------------------------------------');
  
  /* 4. 부서의 관리자를 변경하고 그 사원의 수당을 주어진 값만큼 증가 */
  UPDATE dept1  
  SET manager = new_mgr
  WHERE dname = dept_name; 
      
  UPDATE emp1  
  SET comm = NVL(comm,0) + comm_inc -- comm이 null일 경우 0으로 처리하고 계산!
  WHERE empno = new_mgr; 
  
  dbms_output.put_line(dept_name || ' 부서의 관리자와 그의 수당이 변경되었음');
  
  /* 5. 새 관리자의 사번, 이름, 직무, 월급, 수당, 소속부서명을 검색하여 출력 */
  SELECT * INTO emp
  FROM emp1 
  WHERE empno = new_mgr; 
  
  dbms_output.put_line(dept_name || ' 부서의 새 관리자 정보: ' || 
                    emp.empno || ' ' || emp.ename || ' ' || emp.job || ' ' ||
                      emp.sal || ' ' || emp.comm);
END lab4b;
/

set serveroutput on
execute lab4b('ACCOUNTING', 7934 , 1010);
