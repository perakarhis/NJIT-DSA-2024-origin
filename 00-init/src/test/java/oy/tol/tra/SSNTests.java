package oy.tol.tra;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;


/**
 * Unit test for testing verfier of Finnish person ids, SSN numbers.
 * The test id's here were generated randomly using a web service and are 
 * not real SSN ids!!
 */
@DisplayName("Testing valid and invalid SSNs")
public class SSNTests 
{

    @Test
    @DisplayName("Testing valid SSNs")
    public void validSSNTests()
    {
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("210911+0785"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("130311+8502"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("190427A874M"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("261027A053H"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("261027C053H"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("190427A874M"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("261027A053H"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("050301-679T"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("050301Y679T"));
        assertEquals(SSN.Result.VALID_SSN, SSN.verifySSN("161001-154L"));
    }

    @Test
    @DisplayName("Testing invalid SSNs")
    public void invalidSSNTests()
    {
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN(null));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("naurispelto"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("12345678901"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("261027J053H"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("52345262724345324523462"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("42"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("161001*154L"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("161a01-154L"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("     42    "));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("050301--79T"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("-050301-179"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("161001-1p4L"));
        assertEquals(SSN.Result.INVALID_SSN, SSN.verifySSN("211323A965F"));
    }

    @Test
    @DisplayName("Testing test SSNs")
    public void testSSNTests() {
        assertEquals(SSN.Result.VALID_TEST_SSN, SSN.verifySSN("260503-998S"));
        assertEquals(SSN.Result.VALID_TEST_SSN, SSN.verifySSN("260503U998S"));
        assertEquals(SSN.Result.VALID_TEST_SSN, SSN.verifySSN("220303+996H"));
        assertEquals(SSN.Result.VALID_TEST_SSN, SSN.verifySSN("211123A965F"));
        assertEquals(SSN.Result.VALID_TEST_SSN, SSN.verifySSN("211123F965F"));
    }
}
