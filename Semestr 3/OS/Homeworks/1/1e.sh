#!/bin/bash
read str;
cat file.txt | grep $str | cat > find.txt