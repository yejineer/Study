```python3
#!/usr/bin/python3

from openpyxl import *

wb = load_workbook(filename='student.xlsx')
sheet = wb['Sheet1']

i = 2
dicTotal = {}
while sheet.cell(row = i, column = 1).value is not None:
    dicTotal[i] = sheet.cell(row = i, column = 7).value
    i += 1
sortedScore = sorted(dicTotal.items(), key = lambda x : x[1], reverse=True)

# 리스트로 할 때 정렬 totalList.sort(reverse=True)
#print(sortedScore) sortedScore는 list
#print('총 학생 수 : %d' % len(sortedScore))

import math
#학점을 받을 수 있는 최대 등수를 표현하기 위한 딕셔너리
count = len(sortedScore)
limit = {'A+' : math.floor(int(count*0.3*0.5)),
        'A0' : math.floor(int(count*0.3)),
        'B+' : math.floor(int(count*0.5)),
        'B0' : math.floor(int(count*0.7)),
        'C+' : math.floor(int(count*0.85)), 'C' : int(count)}

limitIdx = 0 # limit 딕셔너리의 위치를 설정. 즉, 처음엔 학점 A+주면 됨
grade = list(limit.keys())
listLimit = list(limit.values())
#print(grade[0])

for i in range(len(sortedScore)):
    #sortedscore = sortedScore[i][0] 행들 표시
    score = float(sortedScore[i][1])
    if (score < 40):
        sheet.cell(row = sortedScore[i][0], column = 8, value = 'F')
        continue

    if i+1 == listLimit[limitIdx]: # 현재 학생 수
        limitIdx += 1

    sheet.cell(row = sortedScore[i][0], column = 8,
          value = grade[limitIdx])

wb.save('student.xlsx')
```
