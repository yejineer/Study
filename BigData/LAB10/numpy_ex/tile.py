import numpy as np

a = np.array([0, 1, 2]) # default 데이터 타입: numpy.int64
print(type(a[2]))
print(np.tile(a, 2))
print(np.tile(a, (2,2))) # 2행과 a.length*1열로 만들기

b = np.array([[1, 2], [3, 4]])
print(np.tile(b, 2))
print(np.tile(b, (2, 1))) # 2행과 b.length*1열로 만들기
