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

    headers = {
        "Content-type": "application/json"
    }

    with open(inputfile) as fp:
        dict_object = json.load(fp)
        for object in dict_object:
            transformed_timeseries = []
            for timeseries in object['Signals']:
                transformed_numbers = []
                for number in timeseries:
                    transformed_number = number * (1.1642*pow(10, -3))
                    transformed_numbers.append(transformed_number)
                transformed_timeseries.append(transformed_numbers)

            r = requests.post(url, data=json.dumps(transformed_timeseries), headers=headers)
            time.sleep(0.004)


if __name__ == "__main__":
    main(sys.argv[1:])
