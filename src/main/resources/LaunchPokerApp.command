#!/bin/bash
dir="$(dirname "$BASH_SOURCE")"
echo $dir/resources/poker.properties
java -DconfigDir=$dir/resources/ -jar $dir/PokerApp.jar

