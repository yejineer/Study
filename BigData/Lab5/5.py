import json

with open('movies.json') as datafile:
    jsondata = json.load(datafile)

sales = list(jsondata['boxOfficeResult']['dailyBoxOfficeList'])

sum = 0
for s in sales:
    sum += int(s['salesAmt'])

print("오늘 매출액은 총 ", sum, "원")
