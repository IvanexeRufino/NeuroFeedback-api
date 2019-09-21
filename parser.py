import json
import sys

EEG_File = str(sys.argv[1])
w, h = 0, 24
EEG_data =[[0 for x in range(w)] for y in range(h)]

with open(EEG_File) as file:
    data = json.load(file)
    for block in data:
        dataIndex = 0
        for signals in data[dataIndex]["Signals"]:
            channelCnt = 0
            for signal in signals:
                EEG_data[channelCnt].append(signal)
                channelCnt+=1
            dataIndex+=1

for x in range(0,24):
    del EEG_data[x][0]

EEG_Matlab_File =  EEG_File.split('.DAT')[0] + ".m"

f= open(EEG_Matlab_File,"w+")
for i in range(0,24):
    f.write("EEG_CH%d = [" % i)
    for j in range(0,len(EEG_data[i])-1):
        f.write("%d," % EEG_data[i][j])
    f.write("%d];\n" % EEG_data[i][len(EEG_data[i])-1])

f.close()

print("File %s in JSON converted to %s matlab file" % (EEG_File, EEG_Matlab_File))
