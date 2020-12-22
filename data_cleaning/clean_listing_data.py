import csv

# Reads in property type - key mapping
with open('property_types_clean.csv', 'r') as sector:
    sectorFile = csv.reader(sector)
    property_type_id_map = {}

    for lines in sectorFile:
        property_type_id_map[lines[1]] = lines[0]

# Cleans data to ignore the records with unknown property type
with open('listings.csv', 'r') as file:
    csvFile = csv.reader(file)
    result = []

    for lines in csvFile:
        # Avoids dulicaiton
        if (lines in result):
            continue
        # Selection
        if (lines[8] not in property_type_id_map):
            continue
        lines[8] = property_type_id_map[lines[8]]
        result.append(lines)
        print(lines)

# Writes the cleaned data back into a new file
fields = ['ListingId', 'Name', 'Neighborhood', 'Latitude', 'Longtitude', 'ReviewRating', 'WeeklyPrice', 'Description', 'PropertyType', 'RoomType', 'HostId']
filename = 'listings_clean.csv'
with open(filename, 'w') as output:
    csvwriter = csv.writer(output)

    csvwriter.writerow(fields)
    for i in range(1, len(result)):
        csvwriter.writerow(result[i])
