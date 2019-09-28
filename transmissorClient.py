import requests, json, sys, getopt, time

def main(argv):
    inputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:",["ifile="])
    except getopt.GetoptError:
        print 'transmissorClient.py -i <inputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'transmissorClient.py -i <inputfile>'
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg

    print 'Input file is ', inputfile

    url = 'http://localhost:8080/trackSession/treatmentSession/4'

    payload = []
    headers = {
        "Content-type": "application/json"
    }

    acumulated_data = 0

    with open(inputfile) as fp:
        line = fp.readline()
        while line:
            payload.append([float(line.rstrip('\n')) -5 ,float(line.rstrip('\n')) + 5,line.rstrip('\n'),
                            float(line.rstrip('\n')) + 23, float(line.rstrip('\n')) + 2,float(line.rstrip('\n')) - 18,
                            float(line.rstrip('\n')) -2, float(line.rstrip('\n')) + + 50])
            acumulated_data += 1

            if acumulated_data == 128:
                r = requests.post(url, data=json.dumps(payload), headers=headers)
                print(r.content)
                payload = []
                acumulated_data = 0
                time.sleep(0.9)

            line = fp.readline()

if __name__ == "__main__":
    main(sys.argv[1:])
