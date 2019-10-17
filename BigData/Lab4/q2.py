sum = 0
count = 0

with open("mbox-short.txt", "rt") as f:
    for line in f:
        if ('X-DSPAM-Confidence' in line):
            s = line.split(" ")
            sum += float(s[1])
            count += 1

print(sum/count)
