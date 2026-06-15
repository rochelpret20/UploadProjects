from collections import Counter

file = "access.log"

ips = []

with open(file) as f:

    for line in f:

        ip = line.split()[0]

        ips.append(ip)

counter = Counter(ips)

print("\nTOP 10 IPs")

for ip,count in counter.most_common(10):

    print(ip,count)