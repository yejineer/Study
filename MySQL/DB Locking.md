# Table-Level Locking
- 테이블이 MyIASM인 경우는 테이블 수준의 잠금 기능을 사용할 수 있다. 
(이 방법은 테이블 전체를 잠그기 때문에 다중 사용자 환경에서는 사용하지 않는 것이 좋다.)
- 테이블 잠금에는 **READ lock** 과 **WRITE lock** 이 있다.

- **READ lock 을 걸면** UNLOCK 하기 전까지 **다른 세션에서 읽을 수는 있지만** 입력, 수정, 삭제는 할 수 없고 대기하게 된다.
```mysql
LOCK TABLES tb_stock READ;
(수행하려는 작업)
UNLOCK TABLES;
```

- **WRITE lock 을 걸면** UNLOCK 하기 전까지 **다른 세션에서는 읽을 수 없고 대기**하게 된다.
```mysql
LOCK TABLES tb_stock WRITE;
(수행하려는 작업)
UNLOCK TABLES;
```

- 테이블 잠금이 유지되는 동안 잠긴 테이블에 대해서만 액세스 할 수 있다. 
여러 테이블을 읽을 경우 모든 테이블의 잠금을 하나의 LOCK TABLES 문에 모두 나열해야 한다.
```mysql
LOCK TABLES tb_stock WRITE, tb_order READ;
```

- 하나의 테이블의 lock 을 얻고, 다른 테이블을 조회하려고 하면 에러가 발생한다.

### 참고
- [MySQL locking - Table-Level Locking, Row-Level Locking, Optimistic Locking](https://offbyone.tistory.com/225)
