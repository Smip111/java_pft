package ru.stqa.pft.soap;


import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("89.237.90.108");
        assertEquals(geoIP.getCountryCode(),"FRA");
    }

    @Test
    public void testInvalidIp(){
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("89.237.90.xxx");
        assertEquals(geoIP.getCountryCode(),"FRA");
    }
}
