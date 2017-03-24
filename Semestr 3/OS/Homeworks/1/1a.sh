#!/bin/bash
n=1
while [ $n -lt 34 ]
do
	touch a$n;
	touch b$n;
	touch c$n;
	n=$[$n+1];
done