#!/bin/sh
javac -nowarn -g:none trainer.java 
java trainer $1 $2 $3 $4 $5 
