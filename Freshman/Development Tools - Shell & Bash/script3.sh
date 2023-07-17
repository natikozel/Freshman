#!/bin/bash -eu

filename=$1
outputnewfile="good_ips.txt"

touch $outputnewfile
while IFS= read -r ip
do
	if ping -w 2 $ip 
	then
		echo $ip >> $outputfilename
	fi			
done < $filename		