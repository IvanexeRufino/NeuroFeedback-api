import requests, json, sys, getopt, time

def main(argv):
    treatment_id = 0

    type = argv[0]
    file_number = argv[1]

    if type == 'relaxation':
        treatment_id = 4
    elif type == 'cognitive':
        treatment_id = 5
    elif type == 'focus':
        treatment_id = 6
    elif type == 'memory':
        treatment_id = 7

    inputfile = type + file_number + '.txt'

    print ('Input file is ', inputfile)

    url = 'http://localhost:8080/trackSession/treatmentSession/' + str(treatment_id)

    payload = []
    headers = {
        "Content-type": "application/json"
    }

    acumulated_data = 0

    with open('../testFiles/' + inputfile) as fp:
        line = fp.readline()
        while line:
            splitted_array = line.split(',')
            if acumulated_data == 0:
                payload = [[] for i in range(len(splitted_array))]

            for i in range(len(splitted_array)):
                payload[i].append(float(splitted_array[i]))

            acumulated_data += 1

            if acumulated_data == 128:

                print(payload)
                r = requests.post(url, data=json.dumps(payload), headers=headers)
                print(r.content)
                acumulated_data = 0
                time.sleep(0.9)

            line = fp.readline()

    url = 'http://localhost:8080/trackSession/endTreatment/' + str(treatment_id)
    requests.post(url, headers=headers)

if __name__ == "__main__":
    main(sys.argv[1:])
