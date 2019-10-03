import requests, json, sys, getopt, time

def main(argv):
    treatment_id = 0

    type = argv[0]
    file_number = argv[1]

    if type == 'relaxation':
        treatment_id = 2
    elif type == 'cognitive':
        treatment_id = 3
    elif type == 'focus':
        treatment_id = 4
    elif type == 'memory':
        treatment_id = 5

    inputfile = type + file_number + '.txt'

    print 'Input file is ', inputfile

    url = 'http://localhost:8080/trackSession/treatmentSession/' + str(treatment_id)

    payload = []
    headers = {
        "Content-type": "application/json"
    }

    acumulated_data = 0

    with open('testFiles/' + inputfile) as fp:
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
