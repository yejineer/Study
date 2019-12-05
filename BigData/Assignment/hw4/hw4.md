# HW4-1
## 설명
- Write the python code that retrieves price data and stores the data into the database every 10 minutes.
- CryptoCurrency
  - BTC, ETH
  - Target Currency : USD, JPY, EUR, KRW
- You can use the sql code in the next slide for creating the price table.

## Table Schema
```sql
drop table if exists 
price_tbl;
create table 
price_tbl(
CurrentTime
varchar(20) not null,
CryptoCurrencyName
varchar(10) not null,
TargetCurrencyName
varchar(10) not null,
Price decimal(20,5) not null
);

## 주의
- Your source code file MUST be HW_4_1Student<Your ID>.py
  - Example) If your student id is 20151047, your file for source code MUST BE HW4_1_Student20151047.py
- If you don’t follow the rule, your submission may be failed.
- Configuration.
  -Your program should use the configuration file for DB url, DB id & DB pass.
  - The prof. will run your code by using the following command.
  - python3 HW4_1_Student20151047.py.py config.props

## config.props
