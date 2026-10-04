package assignment_4;

public class Address {
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String zipCode;

    /* Creates a new Address. streetAddress2 is optional and may be null or empty. */
    public Address(String streetAddress1, String streetAddress2, String city, String state, String zipCode) {
        if (streetAddress1 == null || streetAddress1.isBlank()) {
            throw new IllegalArgumentException("Street address 1 is required.");
        }
        if (city == null || city.isBlank()) {
            throw new IllegalArgumentException("City is required.");
        }
        if (state == null || state.isBlank()) {
            throw new IllegalArgumentException("State is required.");
        }
        if (zipCode == null || zipCode.isBlank()) {
            throw new IllegalArgumentException("Zip code is required.");
        }

        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2 == null ? "" : streetAddress2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    /* Returns the full address formatted on a single line */
    public String getAddressInfo() {
        String street = streetAddress2.isBlank() ? streetAddress1 : streetAddress1 + ", " + streetAddress2;
        return street + ", " + city + ", " + state + " " + zipCode;
    }

    @Override
    public String toString() {
        return getAddressInfo();
    }
}
