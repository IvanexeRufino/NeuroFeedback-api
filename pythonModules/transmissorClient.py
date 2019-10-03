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

    url = 'http://localhost:8080/trackSession/treatmentSession/5'

    payload = []
    headers = {
        "Content-type": "application/json"
    }

    acumulated_data = 0

    with open(inputfile) as fp:
        line = fp.readline()
        while line:
            data_array = []
            splitted_array = line.split(',')

            for i in range(len(splitted_array)):
                data_array.append(float(splitted_array[i]))

            payload.append(data_array)
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
