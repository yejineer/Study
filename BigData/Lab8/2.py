# 현재 시간을 찍어주는 웹 서버

from socket import *
import time

def main():
    listen_sock = socket(AF_INET, SOCK_STREAM)
    listen_sock.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
    listen_sock.bind(('', 9090))
    listen_sock.listen(5)

    while 1:
        conn, addr = listen_sock.accept()
        data = conn.recv(1024)
        print(len(data.decode("UTF-8")))
        print(data.decode("UTF-8"))
        msg = """HTTP/1.1 200 OK

        <html><body>This time is %s</body></html>""" % time.ctime()
        conn.sendall(msg.encode("UTF-8"))
        conn.close()

main()
