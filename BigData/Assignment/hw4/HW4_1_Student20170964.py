import requests
import urllib
import configuration
import sqlite3
import json
from datetime import datetime
import time
import sys

def get_connection(configProps):
    try:
        conn = sqlite3.connect( configProps['DB.db'] )
        return conn
    finally:
        pass

def initdb(con):
    cursor = con.cursor() 
    cursor.execute("DROP TABLE IF EXISTS PRICE_TBL")
    cursor.execute("CREATE TABLE PRICE_TBL (CurrentTime VARCHAR(20) NOT NULL, CryptoCurrencyName VARCHAR(10) NOT NULL, TargetCurrencyName VARCHAR(10) NOT NULL, Price decimal(20, 5) NOT NULL)" )
    cursor.close()

def insertPrice(configProps, currentTime, cryptoCurrency, targetCurrency, price, conn):
    cursor = conn.cursor()
    cursor.execute("INSERT INTO PRICE_TBL VALUES (\'%s\', \'%s\', \'%s\', \'%s\')" % (currentTime, cryptoCurrency, targetCurrency, price))
    conn.commit()
    return True

def parserequest(configProps, data, crypto, conn): #JSON Parsing
    prices = json.loads(data)
    currentTime = "{:%Y-%m-%d %H:%M:%S}".format(datetime.now())
    insertPrice(configProps, currentTime, crypto, "USD", float(prices["USD"]), conn)
    insertPrice(configProps, currentTime, crypto, "JPY", float(prices["JPY"]), conn)
    insertPrice(configProps, currentTime, crypto, "EUR", float(prices["EUR"]), conn)
    insertPrice(configProps, currentTime, crypto, "KRW", float(prices["KRW"]), conn)

def viewDBdata(table, conn) :
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM " + table)
    
    #데이터 fetch
    rows = cursor.fetchall() # 모든 데이터를 한번에 가져옴
    for row in rows:
        print(row)

    cursor.close()
    return True

if __name__ == "__main__":
    inputConfigFile = sys.argv[1]
    c = configuration.get_web_configuration(inputConfigFile)
    db_c = configuration.get_db_configuration(inputConfigFile)

    db_conn = get_connection(db_c)
    initdb(db_conn)
    db_conn.commit()

    bit_url = 'https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=USD,JPY,EUR,KRW' 
    eth_url = 'https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD,JPY,EUR,KRW' 
    
    while 1:
        bit_data = urllib.request.urlopen(bit_url).read().decode('utf-8')
        eth_data = urllib.request.urlopen(eth_url).read().decode('utf-8')
        
        parserequest(db_c, bit_data, 'BTC', db_conn)
        parserequest(db_c, eth_data, 'ETH', db_conn)
        
        viewDBdata('price_tbl', db_conn) # 'PRICE_TBL'처럼 대문자로 해도 상관X

        time.sleep(600)
