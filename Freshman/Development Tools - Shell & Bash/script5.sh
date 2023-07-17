#!/bin/bash -eu

num=$(( $RANDOM % 50 + 1 ))
echo $num
while true
read -p "Guess a number between 1 to 50: " num2
do
if [ $num2 -gt $num ]
then
echo "Too big"
elif [ $num2 -lt $num ]
then
echo "Too small"
else 
echo "Correct!"
exit 0
fi
done