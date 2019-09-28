str = 'X-DSPAM-Confidence:0.8475'

idx = str.find('0')

num = float(str[idx:])

print("%.4f" % num)
