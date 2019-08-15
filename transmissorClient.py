import requests, json, sys, getopt

def main(argv):
    inputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:",["ifile="])
    except getopt.GetoptError:
        print 'test.py -i <inputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'test.py -i <inputfile>'
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg

    print 'Input file is ', inputfile

    url = 'http://localhost:8080/data'
    payload = [1, 2, 3]

    # Adding empty header as parameters are being sent in payload
    headers = {
        "Content-type": "application/json"
    }

    r = requests.post(url, data=json.dumps(payload), headers=headers)
    print(r.content)

if __name__ == "__main__":
    main(sys.argv[1:])
