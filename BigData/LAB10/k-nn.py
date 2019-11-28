import numpy as np
import operator
import sys

inputfile = sys.argv[1]
#inputs = input("input\n")
#listOfInputs = inputs.strip().split()
#airMile = int(listOfInputs[0])
#videoGame = float(listOfInputs[1])
#iceCream = float(listOfInputs[2])

def createDataSet():
    filename = inputfile
    with open(filename) as f:
        numberOfLines = len(f.readlines())
    group = np.zeros((numberOfLines, 3))
    labels = [] # 답
    index = 0
    with open(filename) as f:
        for line in f.readlines():
            line = line.strip() # 각 줄당 마지막에 \n있는거 없애주려고
            listFromLine = line.split()
            group[index][0] = float(listFromLine[0])
            group[index][1] = float(listFromLine[1])
            group[index][2] = float(listFromLine[2])
            labels.append(listFromLine[-1])
            index += 1
    return group, labels

def autoNorm(dataSet):
    minVals = dataSet.min(0) #집합에서의 최솟값. 0: column의 최솟값을 얻게 함
    maxVals = dataSet.max(0) #집합에서의 최댓값
    ranges = maxVals - minVals # 범위
    norDataSet = np.zeros(np.shape(dataSet))
    m = dataSet.shape[0]
    normDataSet = dataSet - np.tile(minVals, (m,1)) #행렬 크기 맞춰주기
    normDataSet = normDataSet / np.tile(ranges, (m, 1)) #구성요소 나누기
    return normDataSet, ranges, minVals

def classify0(inX, dataSet, labels, k): # k는 홀수로
    dataSetSize = dataSet.shape[0]
    diffMat = np.tile(inX, (dataSetSize, 1)) - dataSet
    sqDiffMat = diffMat ** 2
    sqDistances = sqDiffMat.sum(axis = 1)
    distances = sqDistances ** 0.5
    sortedDistIndicies = distances.argsort()
    classCount = {}
    for i in range(k):
        voteIlabel = labels[sortedDistIndicies[i]]
        classCount[voteIlabel] = classCount.get(voteIlabel, 0) + 1
    sortedClassCount = sorted(classCount.items(),
                key = operator.itemgetter(1), reverse = True)
    return sortedClassCount[0][0]

group, labels = createDataSet()
normDataSet, ranges, minVals = autoNorm(group)
print(classify0(([40900, 8.3, 0.9]-minVals)/ranges, 
                    normDataSet, labels, 9))
