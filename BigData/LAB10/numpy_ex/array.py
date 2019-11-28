import numpy as np

test_array = np.array([1, 4, 5, 8], float)
print(test_array)
type(test_array[3])
test_array = np.array([1, 4, 6, "8"], float) # String type의 데이터를 넣어도
print(test_array)
print(type(test_array[3])) # Float type으로 자동 형변환을 실시
print(test_array.dtype) # Array 전체의 data type을 반환함
print(test_array.shape) # Array의 shape을 반환함

