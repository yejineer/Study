import numpy as np

print(np.sum([0.5, 1.5])) # DTYPE을 주지 않으면 input array의 합을 반환
print(np.sum([[0, 1], [5, 0]])) # axis를 주지 않으면 모든 요소를 합산
print(np.sum([[0, 1], [0, 5]], axis = 0)) # 위에서 아래로 계산
print(np.sum([[0, 1], [0, 5]], axis = 1)) # 좌우 계산
