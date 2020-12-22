import csv

with open('neighborhood_clean.csv', 'r') as neighborhood:
    neighborhoodFile = csv.reader(neighborhood)
    neis = {}

    for lines in neighborhoodFile:
        id = lines[0]
        value = lines[1] + lines[2]
        neis[value] = id

with open('Offense.csv', 'r') as file:
    csvFile = csv.reader(file)
    result = []

    for lines in csvFile:
        value = lines[4] + lines[5]
        if value not in neis.keys():
            continue
        id = neis[value]
        line = []
        line.append(lines[1])
        line.append(lines[2])
        line.append(lines[3])
        line.append(id)
        line.append(lines[6])
        line.append(lines[7])
        line.append(lines[8])
        result.append(line)
print(len(result))

fields = ['OffenseId', 'ReportDateTime', 'OffenseCode', 'NeighborhoodId',
          '100BlockAddress', 'Latitude', 'Longtitude']
filename = 'offense_clean.csv'
with open(filename, 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(1, len(result)):
        csvwriter.writerow(result[i])
