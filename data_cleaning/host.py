import csv

with open('host.csv', 'r') as file:
    csvreader = csv.reader(file)

    host = []
    host_ids = []

    for lines in csvreader:
        id = lines[0]
        if id not in host_ids:
            host_ids.append(id)
            host.append(lines)
print(host[0])


fields = ['HostId', 'HostName', 'HostSince', 'HostResponseRate',
          'HostIsSuperhost', 'HostNeighborhood', 'HostListingCount']
with open('host_clean.csv', 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(1, len(host)):
        csvwriter.writerow(host[i])
