fname = input("Enter file name: ")

import json

fromDict = dict()
f = open(fname, "rt")
for line in f:
    if line.startswith("From: "):
        dictKey = line.split()[1]
        if dictKey in fromDict:
            fromDict[dictKey] = fromDict[dictKey] + 1
        else:
            fromDict[dictKey] = 1

print(fromDict)

f.close()
