def avg(a, b, c):
    sum = 0

    for n in [a, b, c]:
        sum += n
    
    return sum / 3

print(avg(1, 3, 5))
print(avg(2, 3, 4))
print(avg(1, 6, 9))
