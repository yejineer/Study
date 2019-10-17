n1, n2 = input("숫자 두 개를 입력하세요 : ").split()

try:
    print(int(n1) / int(n2))
except ZeroDivisionError:
    print("division by zero")

