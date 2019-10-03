import requests, json, sys, getopt, time, random
from scipy.io import loadmat
import mne
import numpy as np

def main(argv):
    inputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:",["ifile="])
    except getopt.GetoptError:
        print('transmissorClient.py -i <inputfile>')
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print('transmissorClient.py -i <inputfile>')
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg

    print('Input file is ', inputfile)

    raw_data = get_raw_data(inputfile)

    with open('cognitive3.txt', 'w+') as f:
        for i in range(len(raw_data[0])):
            string_data = str(raw_data[7][i] * 10) + ',' + str(raw_data[8][i] * 10) + ',' + str(raw_data[9][i] * 10) + ',' + str(raw_data[11][i] * 10) + ',' + str(raw_data[13][i] * 10)
            f.write(string_data + '\n')

def get_raw_data(inputfile):

    data = loadmat(inputfile)

    S = data['SIGNAL'][:, 1:15]
    stim_close = data['SIGNAL'][:, 14]
    stim_open = data['SIGNAL'][:, 15]
    stim = 1 * stim_close + 2 * stim_open

    chnames = [
        'Fp1',
        'Fp2',
        'Fz',
        'T7',
        'Cz',
        'T8',
        'P7',
        'P3',
        'Pz',
        'P4',
        'P8',
        'O1',
        'Oz',
        'O2',
        'stim']
    chtypes = ['eeg'] * 14 + ['stim']
    X = np.concatenate([S, stim[:, None]], axis=1).T

    info = mne.create_info(ch_names=chnames, sfreq=512,
                           ch_types=chtypes, montage='standard_1020',
                           verbose=False)
    raw = mne.io.RawArray(data=X, info=info, verbose=False)

    fmin = 3
    fmax = 40
    raw.filter(fmin, fmax, verbose=False)
    raw.resample(sfreq=128, verbose=False)

    print(len(raw['data'][0][2]))

    return raw['data'][0]

if __name__ == "__main__":
    main(sys.argv[1:])