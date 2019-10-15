#!/bin/bash

sn=$1

for host in $(seq 1 30);
do

	ping -c 1  $sn.$host | grep "Unreachable" &>/dev/null

	if [ $? -eq 0 ]; then
		echo $sn.$host Unreachable

	else
		time=$(ping -c 1 $sn.$host | grep "ttl" | cut -d '=' -f3 | cut -d ' ' -f1)
		

		if [[ $time == "64" ]]; then
			echo $sn.$host is a Linux system.
		elif [[ $time == "128" ]]; then
			echo $sn.$host is a Windows system.
		elif [[ $time == "256" ]]; then
			echo $sn.$host is an ios system.
		fi
	fi

done

# Now test for the TTL to identify Operating Systems
# ttl~=64 - Linux
# ttl~=128 - Windows
# ttl~=255 - ioS
