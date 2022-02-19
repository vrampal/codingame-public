read N
readarray power
power=($(for each in ${power[@]}; do echo $each; done | sort -n))
#echo "${power[*]}" >&2
answer=10000000
for ((i=0; i<${#power[@]}-1; i++))
	do
		current=${power[$i]}
		next=${power[$i+1]}
		delta=$((next-current))
		#echo "$delta" >&2
		if [ $delta -lt $answer ]; then
			answer=$delta
		fi
	done

echo "$answer"
