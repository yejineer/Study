import numpy as np

x = np.array([3, 1, 2])
print(np.argsort(x))    # x를 어떤 순서로 방문해야 정렬된 순서가 될 지
x = np.array([[0, 4], [2, 3]])
print(x)
print(np.argsort(x, axis=0)) # sorts along first axis(down)
print(np.argsort(x, axis=1)) # sorts along last axis(across)
