a = list(range(1, 11))
b = [n*n for n in a]

dic = dict(zip(a, b))
print(dic[6])


