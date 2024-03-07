package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;

class AscendingPersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person first, Person second) {
		return first.getFullName().compareTo(second.getFullName());
	}
}

class DescendingPersonComparator implements Comparator<Person> {
	@Override
	public int compare(Person first, Person second) {
		return second.getFullName().compareTo(first.getFullName());
	}
}

public class ComparatorTester {
	
	public static void main(String [] args) {

		Person [] array = { new Person("Antti", "Juustila"), 
		new Person("Seppo", "Zippaaja"),
		new Person("Atte", "Aurinkoinen"),
		new Person("Simo", "Hiltunen"),
		new Person("Heikki", "Iivari")};

		Algorithms.sortWithComparator(array, new AscendingPersonComparator());
		System.out.println(Arrays.toString(array));
		Algorithms.sortWithComparator(array, new DescendingPersonComparator());
		System.out.println(Arrays.toString(array));
	}

}
