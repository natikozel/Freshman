#!/bin/bash -eu

if test $# -lt 1
then
	echo "Please try again, enter one argument (File or Directory)"
	exit 1
fi

argument=$1

if test -f $argument
then
cat $argument
exit 0
elif test -d $argument
then
ls $argument
exit 0
fi

echo "The argument is not a file or a directory"
exit 1


	
		
		