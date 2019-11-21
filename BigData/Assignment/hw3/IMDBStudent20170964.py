import sys

inputfile = sys.argv[1]
outputfile = sys.argv[2]

def getGenre(inputfile, outputfile):
    dic = dict()
    with open(inputfile, "rt") as f:
        for line in f:
            movie = line.strip("\n").split("::")
            genres = movie[2].split("|")

            for genre in genres:
                if genre not in dic:
                    dic[genre] = 1
                else:
                    dic[genre] += 1

    with open(outputfile, "wt") as f:
        for key in dic.keys():
            f.write(key + " " + str(dic[key]) + "\n")

getGenre(inputfile, outputfile)
