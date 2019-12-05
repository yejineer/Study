import requests
import urllib
import configuration
import sqlite3
import json
from datetime import datetime
import time
from socket import *
import sys

def get_connection(configProps):
    try:
        conn = sqlite3.connect( configProps['DB.db'] )
        return conn
    finally:
        pass

def findPrice(today, cryptoCurrency, targetCurrency, conn):
    return __findPrice(today, cryptoCurrency, targetCurrency, conn.cursor())

def __findPrice(today, cryptoCurrency, targetCurrency, cursor):
    cursor.execute("SELECT CurrentTime, Price FROM PRICE_TBL WHERE CurrentTime like \'%s\' and CryptoCurrencyName = \'%s\' and TargetCurrencyName = \'%s\'" % (today+"%", cryptoCurrency, targetCurrency))
    pricelist = cursor.fetchall()
    cursor.close()
    rslt = ""
    for item in pricelist:
        rslt += item[0] + " " + str(item[1]) + "<br>"
    
    return rslt

def viewDBdata(table, conn) :
    cursor = conn.cursor()
    cursor.execute("SELECT * FROM " + table)
    
    #데이터 fetch
    rows = cursor.fetchall() # 모든 데이터를 한번에 가져옴
    for row in rows:
        print(row)


def parserequest(msg,conn):
    msg = str.strip(msg)
    if msg == '': return None;
    msg = msg.split("\n")[0]  # ex) GET /BTC/KRW HTTP/1.1
    url = msg.split()[1]      # ex) /BTC/KRW
    print(url)
    val = url.split('/')      # ex) [BTC, KRW]
    
    if len(val) < 3:          # localhost:8088/cryptoCurrencyName/targetCurrencyName 아닐 때
        return None;

    today = "{:%Y-%m-%d}".format(datetime.now())
    
    return findPrice(today, val[1], val[2], conn)

if __name__ == "__main__":
    inputConfigFile = sys.argv[1]
    c = configuration.get_web_configuration(inputConfigFile)
    db_c = configuration.get_db_configuration(inputConfigFile)
    PORT = int(c['web.port'])
    BUFSIZE = int(c['web.bufsize'])

    db_conn = get_connection(db_c)

    listen_sock = socket(AF_INET, SOCK_STREAM)
    listen_sock.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
    listen_sock.bind(('', PORT))
    listen_sock.listen(1)
    viewDBdata('PRICE_TBL', db_conn)

    while 1:
        conn, addr = listen_sock.accept()
        data = conn.recv(BUFSIZE).decode("UTF-8")
        rslt = parserequest(data,db_conn)
        if (rslt == None):
            rslt = "Please Input Proper url <br> ex) http://localhost:8088/BTC/KRW"

        msg = """HTTP/1.1 200 OK


            <html><body>Price Table:<br>%s</body></html>""" % rslt
        conn.sendall(msg.encode("UTF-8"))
        conn.close()
