#!/bin/bash -eu

i=$#

while [ $i -ge 0 ]
do
	echo ${!i}
	((i=i-1))
done