package safeTravel.tools;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import safeTravel.model.*;
import safeTravel.dao.*;


/* Offense */
public class Inserter {
	
	public static void main(String[] args) throws SQLException {
		// DAO instances.
		OffenseGroupsDao offenseGroupDao = OffenseGroupsDao.getInstance();
		OffenseTypesDao offenseTypeDao = OffenseTypesDao.getInstance();
		PoliceSectorsDao policesectorsDao = PoliceSectorsDao.getInstance();
		NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
		HundredBlockAddressDao hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
		LocationsDao locationsDao = LocationsDao.getInstance();
		OffensesDao offensesDao = OffensesDao.getInstance();
		PropertyTypeDao propertyTypeDao = PropertyTypeDao.getInstance();
		HostDao hostDao = HostDao.getInstance();
		ListingDao listingDao = ListingDao.getInstance();
		WithInDistancesDao withInDistancesDao = WithInDistancesDao.getInstance();
		
		
		// CREATE
		OffenseGroups offenseGroup1 = new OffenseGroups("ARSON1", OffenseGroups.CrimeAgainstCategory.PROPERTY);
		offenseGroup1 = offenseGroupDao.create(offenseGroup1);
		OffenseGroups offenseGroup2 = new OffenseGroups("BRIBERY1", OffenseGroups.CrimeAgainstCategory.PROPERTY);
		offenseGroup2 = offenseGroupDao.create(offenseGroup2);
		
		OffenseTypes offenseType1 = new OffenseTypes("13Bbb", "Simple Assault", "ARSON1");
		offenseType1 = offenseTypeDao.create(offenseType1);
		OffenseTypes offenseType2 = new OffenseTypes("13Ccc", "Indimidation", "BRIBERY1");
		offenseType2 = offenseTypeDao.create(offenseType2);
		
		PoliceSectors policeSector1 = new PoliceSectors("B222",  PoliceSectors.Precinct.valueOf("N"));
		policeSector1 = policesectorsDao.create(policeSector1);
		PoliceSectors policeSector2 = new PoliceSectors("C222",  PoliceSectors.Precinct.valueOf("E"));
		policeSector2 = policesectorsDao.create(policeSector2);
		
		Neighborhoods neighborhood1 = new Neighborhoods(5, "BALLARD SOUTH", policeSector1);
		neighborhood1 = neighborhoodsDao.create(neighborhood1);
		Neighborhoods neighborhood2 = new Neighborhoods(80, "MILLER PARK", policeSector2);
		neighborhood2 = neighborhoodsDao.create(neighborhood2);
		
		HundredBlockAddress hundredBlockAddress1 = new HundredBlockAddress("100XX BLOCK OF 15TH AVE NW");
		hundredBlockAddress1 = hundredBlockAddressDao.create(hundredBlockAddress1);
		
		Locations location1 = new Locations("47.681910", "-122.130500");
		location1 = locationsDao.create(location1);
		
		Timestamp reportDateTime1 = new Timestamp(System.currentTimeMillis());
		Offenses offense1 = new Offenses("66666666", reportDateTime1, offenseType2, 
				neighborhood2, hundredBlockAddress1, location1);
		offense1 = offensesDao.create(offense1);
		
		PropertyType property1 = new PropertyType(1, "Aparthotel");
		property1 = propertyTypeDao.create(property1);
		PropertyType property2 = new PropertyType(2, "Apartment");
		property2 = propertyTypeDao.create(property2);
		PropertyType property3 = new PropertyType(3, "Bed and breakfast");
		property3 = propertyTypeDao.create(property3);

		
		Date date = new Date();;		
		Host host1 = new Host(1, "Tom Jerry", date, 0.8f, true, "Seattle", 5);
		host1 = hostDao.create(host1);
		Host host2 = new Host(2, "Tom Hanks", date, 0.9f, true, "Los Angelos", 10);
		host2 = hostDao.create(host2);
		Host host3 = new Host(3, "Tom Riddle", date, 0.5f, false, "New York", 20);
		host3 = hostDao.create(host3);
		
		Listing listing = new Listing(2318, "Casa Madrona","Madrona", location1.getLatitude(), location1.getLongitude(), 10, "$296.00 "
				, "Gorgeous-COMMA", property1.getPropertyId(), Listing.RoomType.Entire_home_apt, host1.getHostId());
		listing = listingDao.create(listing);
		
		WithInDistances withInDistance = new WithInDistances(2318, "66666666");
		withInDistance = withInDistancesDao.create(withInDistance);

		
		// READ
		
		List<OffenseGroups> pList2 = offenseGroupDao.getAllOffenseGroups();
		for(OffenseGroups p : pList2) {
			System.out.println("Looping offenseGroup: " + p.toString());
		}
		
		List<OffenseTypes> pList1 = offenseTypeDao.getAllOffenseTypes();
		for(OffenseTypes p : pList1) {
			System.out.println("Looping offenseGroup: " + p.toString());
		}
		
		PoliceSectors policesec = policesectorsDao.getPoliceSectorsByBeat("B222");
		System.out.println("Reading police sector: " + policesec);
		
		List<PoliceSectors> pSector1 = policesectorsDao.getPoliceSectorsByPrecinct(PoliceSectors.Precinct.N);
		for (PoliceSectors p: pSector1) {
			System.out.println("Looping police sector: " + p.toString());
		}
		
		Neighborhoods neighborhood = neighborhoodsDao.getNeighborhoodById(2);
		System.out.println("Reading neighborhood: " + neighborhood);
		
		List<Neighborhoods> neighborhoodsList2 = neighborhoodsDao.getNeighborhoodsByBeat("C222");
		for (Neighborhoods n: neighborhoodsList2) {
			System.out.println("Looping neighborhood: " + n.toString());
		}
		
		HundredBlockAddress address = hundredBlockAddressDao.getHundredBlockAddress("100XX BLOCK OF 15TH AVE NW");
		System.out.println("Reading 100BlockAddress: " + address);
		
		Locations local1 = locationsDao.getLocationByLatitude("47.681910");
		System.out.println("Reading location: " + local1);
		
		Offenses offense2 = offensesDao.getOffenseById("66666666");
		System.out.println("Reading Offense: " + offense2);
		
		List<Offenses> offenseList2 = offensesDao.getOffenseByNeighborhood(neighborhood1);
		for (Offenses i: offenseList2) {
			System.out.println("Looping Offenses: " + i.toString());
		}

		List<Offenses> offenseList3 = offensesDao.getOffenseByOffenseType(offenseType1);
		for (Offenses i: offenseList3) {
			System.out.println("Looping Offenses: " + i.toString());
		}
		
		List<Offenses> offenseList4 = offensesDao.getOffenseByReportDateTime(reportDateTime1);
		for (Offenses i: offenseList4) {
			System.out.println("Looping Offenses: " + i.toString());
		}
		
		Host host = hostDao.getHostByHostId(1);
		System.out.println("Reading host: " + host);
		
		
		List<Host> hostList = hostDao.getAllSuperHosts();
		for (Host h : hostList) {
			System.out.println("Looping super hosts: " + h);
		}
		
		PropertyType property = propertyTypeDao.getPropertyTypeById(1);
		System.out.println("Reading property: " + property);
		
		Listing l1 = listingDao.getListingById(2318);
		System.out.println("Reading listing: " + l1.toString());
		
		WithInDistances withInDistance1 = withInDistancesDao.getWithInDistanceById(1);
		System.out.println("Reading WithinDistance: " + withInDistance1.toString());
		
		List<WithInDistances> withInDistanceList1 = withInDistancesDao.getWithInDistanceByListingId(9531);
		for (WithInDistances w: withInDistanceList1) {
			System.out.println("Looping WithinDistance: " + w.toString());
		}
		
		List<WithInDistances> withInDistanceList2 = withInDistancesDao.getWithInDistanceByOffenseId("13489611830");
		for (WithInDistances w: withInDistanceList2) {
			System.out.println("Looping WithinDistance: " + w.toString());
		}
		

		// UPDATE
		Timestamp reportDateTime2 = new Timestamp(System.currentTimeMillis() - 5184000);
		offensesDao.updateReportDateTime(offense1, reportDateTime2);
		System.out.println("Reading updated offense: " + host1);
		
		hostDao.updateHostByHostName(host1, "Mickey");
		System.out.println("Reading updated host: " + host1);
		
		hundredBlockAddressDao.updateLastName(hundredBlockAddress1, "789 St");
		System.out.println("Reading updated 100BlockAddress: " + hundredBlockAddress1);
		
		listingDao.updateNeighborhood(listing, "Capital Hill");
		System.out.println("Reading updated listing: " + listing);
		
		
		// DELETE
		offenseGroupDao.delete(offenseGroup1);
		System.out.println("Reading deleted offense group: " + offenseGroup1);
		
		offenseTypeDao.delete(offenseType1);
		System.out.println("Reading deleted offense type: " + offenseType1);
		
		policesectorsDao.delete(policeSector1);
		System.out.println("Reading deleted police sector: " + policesectorsDao);
		
		neighborhoodsDao.delete(neighborhood1);
		System.out.println("Reading deleted neighborhood: " + neighborhood1);
		
		hundredBlockAddressDao.delete(hundredBlockAddress1);
		System.out.println("Reading deleted offense: " + hundredBlockAddress1);
		
		Locations location3 = new Locations("47.681910", "-122.130500");
		locationsDao.delete(location3);
		System.out.println("Reading deleted location: " + location3);
		
		offensesDao.delete(offense1);
		System.out.println("Reading deleted offense: " + offense1);
		
		WithInDistances withInDistance2 = new WithInDistances(69914, 631445, "10020278457");
		withInDistancesDao.delete(withInDistance2);
		System.out.println("Reading deleted WithDistance: " + withInDistance2);
		
		hostDao.delete(host1);
		System.out.println("Reading deleted host: " + host1);
		
		propertyTypeDao.delete(property3);
		System.out.println("Reading deleted property type: " + property3);
		
		listingDao.delete(listing);
		System.out.println("Reading deleted listing: " + listing);
		
		
	}
}
