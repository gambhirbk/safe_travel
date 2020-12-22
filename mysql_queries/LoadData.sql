USE SafeTravel;

LOAD DATA LOCAL INFILE '/CrimeData/data/offensecategory_clean.csv' INTO TABLE OffenseGroup
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/offensetype_clean.csv' INTO TABLE OffenseType
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;

LOAD DATA LOCAL INFILE '/CrimeData/data/policesector_clean.csv' INTO TABLE PoliceSector
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/neighborhood_clean.csv' INTO TABLE Neighborhood
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/100BlockAddress_clean.csv' INTO TABLE 100BlockAddress
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/location_whole.csv' INTO TABLE Location
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/offense_clean.csv' INTO TABLE Offense
  FIELDS TERMINATED BY ',' 
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/property_type.csv' INTO TABLE PropertyType
  FIELDS TERMINATED BY ','  
  LINES TERMINATED BY '\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/host_clean.csv' INTO TABLE Host
  FIELDS TERMINATED BY ','  
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
LOAD DATA LOCAL INFILE '/CrimeData/data/listing_clean.csv' INTO TABLE Listing
  FIELDS TERMINATED BY ','  
  LINES TERMINATED BY '\r\n'
  IGNORE 1 LINES;
  
  
  

  
  