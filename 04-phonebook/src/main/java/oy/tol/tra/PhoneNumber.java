package oy.tol.tra;

public class PhoneNumber {
    private String countryCode;
    private String areaCode;
    private String localNumber;

    public PhoneNumber(String countryCode, String areaCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.localNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return countryCode + "-" + areaCode + "-" + localNumber;
    }

}
