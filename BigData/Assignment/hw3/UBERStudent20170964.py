import sys
import calendar

inputfile = sys.argv[1]
outputfile = sys.argv[2]

dayofweek = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']

def getUberInfo(inputfile):
    dic = dict()
    with open(inputfile, "rt") as f:
        for line in f:
            uber = line.strip("\n").split(",")
            date = uber[1].split("/")
            dayIdx = calendar.weekday(int(date[2]), int(date[0]), int(date[1]))
            day = dayofweek[dayIdx]

            key = uber[0] + "," + day
            
            if key not in dic:
                dic[key] = uber[2] + "," + uber[3]
            else:
                origin_value = dic[key].split(",")
                val = str(int(origin_value[0]) + int(uber[2]))
                val += "," + str(int(origin_value[1]) + int(uber[3])) + "\n"
                dic[key] = val

    with open(outputfile, "wt") as f:
       for key in dic.keys():
           f.write(key + " " + dic[key])

getUberInfo(inputfile)

