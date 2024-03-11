package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    public Person(final Person person) {
        this.firstName = new String(person.firstName);
        this.lastName = new String(person.lastName);
    }
    
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public String toString() {
        return getFullName();
    }

    /**
     * TODO: Implement the method below to return a hash value. It must be calculated from the
     * first and last name of the person.
     * 
     * @return Hash value of the person.
     */
    @Override
    public int hashCode() {
        int hash = 5381;
        // Implement hash function here.
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.getFullName().equals(((Person)other).getFullName());
        }
        return false;
    }

    /**
     * Compares two persons, this and the other one.
     * <p>
     * In a phonebook, persons are identified by the last and first names.
     * So if a person is the same or another, depends on if they have
     * the same name. You can use String.compareTo in implementing this.
     * <p>
     * Return <0 if the person's full name (as string) is smaller than the other's.
     * Return 0 if the full name is identical.
     * Return >0 if the other persons full name is larger (as string).
     * Note: String class also implements <code>compareTo()</code> you can use here.
     * @returns Returns 0 if persons are the same otherwise depending on the full name, <0 or >0.
     */
    @Override
    public int compareTo(Person other) {
        return getFullName().compareTo(other.getFullName());
    }
}
