num = []

for n in range(1, 10):
    if n % 3 == 0:
        num.append(n*n)

for i in num:
    print(i, end=', ')
print()
