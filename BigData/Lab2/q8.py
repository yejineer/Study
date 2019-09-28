
def fib_opt(n, memo):
     
    if n == 1:
        return 1
    elif n == 2:
        return 1
    else:
        if memo[n] == 0:
            memo[n] = fib_opt(n-1, memo) + fib_opt(n-2, memo)
        return memo[n]

    
n=5
memo = [0 for n in range(n+1)]
print(fib_opt(n, memo))

n=10
memo = [0 for n in range(n+1)]
print(fib_opt(n, memo))

n=35
memo = [0 for n in range(n+1)]
print(fib_opt(n, memo))
