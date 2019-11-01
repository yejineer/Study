# 고쳐본 것
```python3
#!/usr/bin/python3

from openpyxl import *

wb = load_workbook(filename='student2.xlsx')
sheet = wb['Sheet1']

i = 2
dicTotal = {}
while sheet.cell(row = i, column = 1).value is not None:
    dicTotal[i] = sheet.cell(row = i, column = 7).value
    i += 1

sortedScore = sorted(dicTotal.items(), key = lambda x : x[1], reverse=True)

# 리스트로 할 때 정렬 totalList.sort(reverse=True)
#print(sortedScore) #sortedScore는 list
#print('총 학생 수 : %d' % len(sortedScore))

import math
#학점을 받을 수 있는 최대 등수를 표현하기 위한 딕셔너리
count = len(sortedScore)
limit = {'A+' : math.floor(int(count*0.3*0.5)),
        'A0' : math.floor(int(count*0.3)),
        'B+' : math.floor(int(count*0.5)),
        'B0' : math.floor(int(count*0.7)),
        'C+' : math.floor(int(count*0.85)),
        'C0' : int(count),
        'F' : int(count)
        }

limitIdx = 0 # limit 딕셔너리의 위치를 설정. 즉, 처음엔 학점 A+주면 됨
grade = list(limit.keys())
listLimit = list(limit.values()) # [11, 22, 37, 51, 62, 74]
print(listLimit)
snumOfGrade = [0, 0, 0, 0, 0, 0] #A+, A0, B+, B0, C+, C를 받는 학생 수

i = 0
length = len(sortedScore)
while i < length:
    #sortedscore = sortedScore[i][0] 행들 표시
    score = float(sortedScore[i][1])

    if score < 40:
        listLimit[5] = i
        listLimit[4] = listLimit[3]+int((listLimit[5]-listLimit[3])*0.5)
        break
    else:
        if i+1 > listLimit[limitIdx]:
            limitIdx += 1

        cntSame = 0 # 동점자 수를 나타내는 변수
        j = i+1
    
        while float(sortedScore[j][1]) == score:
            cntSame += 1
            if j+1 > listLimit[limitIdx]: #동점자 등수가 학점 바운더리일 때
                for k in range(j-i):
                    listLimit[limitIdx] -= 1
                limitIdx += 1

                if limitIdx == 2: #B+일 때 다시 설정
                    listLimit[limitIdx] = listLimit[limitIdx-1] + math.floor((listLimit[limitIdx+1]-listLimit[limitIdx-1])*0.5)
            
            j += 1
   
        if cntSame == 0:
            i += 1
        else:
            i = i + 1 + cntSame

# 엑셀에 값 넣기
start = 0
for i in range(len(listLimit)):
    for j in range(start, listLimit[i]):
        sheet.cell(row = sortedScore[j][0], column = 8, value = grade[i])
    start = listLimit[i]

    
print(listLimit)
wb.save('student2.xlsx')
```

# 제출
