# 문제 설명
### 목적
K-Nearest Neighbor 알고리즘을 이용해서 필기체 인식 시스템을 만들어보자.
### 제공하는 데이터
- trainingDigits 폴더 : N_M.txt (N: 숫자, M: 데이터 ID)
  ![image](https://user-images.githubusercontent.com/50271884/107652462-f46ae200-6cc3-11eb-971f-61b5d71b93e8.png)
- testDigits 폴더 : N_M.txt (N: 숫자, M: 데이터 ID)
  ![image](https://user-images.githubusercontent.com/50271884/107652904-693e1c00-6cc4-11eb-81a7-2c620adc6d22.png)
    - 이 폴더의 모든 파일 데이터를 읽어서 숫자를 인식해야 한다.  
      그리고 인식한 결과와 실제 라벨을 확인해서 에러율을 계산하자.  
      cf) 에러율 = 예측을 실패한 경우 / 전체 파일 개수 * 100
### 출력
- KNN 알고리즘에서 k를 변화시켜가면서 에러율을 출력하자.
- 출력 규칙
  - K가 1, 3, 5... 19일 때의 에러율 10개를 차례대로 출력한다.
  - 숫자 20개가 차례대로 한 줄에 한 숫자로 출력되며 소수점은 절사한다.
  
# 코드
```python
from numpy import *
from os import listdir
import operator
 
def classify0(inX, dataSet, labels, k):
    dataSetSize = dataSet.shape[0]
    diffMat = tile(inX, (dataSetSize, 1)) - dataSet
    sqDiffMat = diffMat ** 2
    sqDistances = sqDiffMat.sum(axis = 1)
    distances = sqDistances ** 0.5
    sortedDistIndicies = distances.argsort()
    classCount={}
    for i in range(k):
        voteIlabel = labels[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel, 0) + 1
    sortedClassCount = sorted(classCount.items(),
                              key=operator.itemgetter(1), reverse=True)
    return sortedClassCount[0][0]
  
def img2vector(filename):
    returnVect = zeros((1, 1024))
    fr = open(filename)
    for i in range(32):
        lineStr = fr.readline()
        for j in range(32):
            returnVect[0, 32*i+j] = int(lineStr[j])
    return returnVect
    
def hwClassifier():
    hwLabels = []
    trainingFileList = listdir('trainingDigits')
    m = len(trainingFileList)
    trainingMat = zeros((m, 1024))
    for i in range(m):
        fileNameStr = trainingFileList[i]
        fileStr = fileNameStr.split('.')[0]
        classNumStr = int(fileStr.split('_')[0])
        hwLabels.append(classNumStr)
        trainingMat[i, :] = img2vector('trainingDigits/%s' % fileNameStr)
    testData = img2vector('testDigits/%s' % input("Input file name: "))
    classifierResult = classify0(testData, trainingMat, hwLabels, 3)
    print("Result: %d" % classifierResult)

```
# 참고자료  
https://codeapp.tistory.com/6?category=675169
