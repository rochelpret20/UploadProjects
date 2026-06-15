#!/bin/bash

echo "===================="
echo "SYSTEM AUDIT"
echo "===================="

echo ""
echo "[USERS]"
cat /etc/passwd

echo ""
echo "[OPEN PORTS]"
ss -tuln

echo ""
echo "[RUNNING PROCESSES]"
ps aux

echo ""
echo "[DISK USAGE]"
df -h

echo ""
echo "[MEMORY]"
free -m

echo ""
echo "[LAST LOGINS]"
last

#Para ejecutar el script:
#1. chmod +x audit.sh
#2. ./audit.sh