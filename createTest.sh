ARRAY="["

for i in $(seq 20 $END); do 
	AUX="[$i, $i, $i, $i, $i, $i, $i, $i]"
	
	ARRAY="$ARRAY$AUX"

	if [ $i -lt 20 ] 
	then
		ARRAY="${ARRAY}, ";
	fi
done

ARRAY="${ARRAY}]";

echo ${ARRAY} >> 'test.txt'

python transmissorClient.py -i test.txt