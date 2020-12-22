import csv

with open('policesector_clean.csv', 'r') as sector:
    sectorFile = csv.reader(sector)
    beat = []

    for lines in sectorFile:
        beat.append(lines[0])

# clean data to ignore the records with unknown beat or ooj
with open('neighborhood.csv', 'r') as file:
    csvFile = csv.reader(file)
    result = []

    for lines in csvFile:
        if (lines in result):
            continue
        if (lines[1] not in beat):
            continue
        result.append(lines)
        print(lines)

fields = ['NeighborhoodId', 'MCPP', 'Beat']
filename = 'neighborhood_clean.csv'
with open(filename, 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(1, len(result)):
        result[i].insert(0, i)
        csvwriter.writerow(result[i])
