import csv

with open('neighborhood_clean.csv', 'r') as neighborhood:
    neighborhoodFile = csv.reader(neighborhood)
    neis = {}

    for lines in neighborhoodFile:
        id = lines[0]
        value = lines[1] + lines[2]
        neis[value] = id

with open('location.csv', 'r') as file:
    csvFile = csv.reader(file)
    result = []

    for lines in csvFile:
        value = lines[3] + lines[4]
        if value not in neis.keys():
            continue
        id = neis[value]
        line = []  # 100 Block Address, Latitude, Longtitude, NeighborhoodId
        line.append(lines[0])
        line.append(lines[1])
        if line not in result:
            result.append(line)
print(len(result))  # total 19873 unique address
print(result[2])


fields = ['Latitude', 'Longtitude']
filename = 'location_clean.csv'
with open(filename, 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(1, len(result)):
        csvwriter.writerow(result[i])
