package oy.tol.tra;

class Owner implements Comparable<Owner> {
	private String name;

	public Owner(String name) {
		 this.name = name;
	}

	public String getName() {
		 return name;
	}

	@Override
	public boolean equals(Object another) {
		 if (another instanceof Owner) {
			  return ((Owner)another).name.equals(this.name);
		 }
		 return false;
	}

	@Override
	public int compareTo(Owner another) {
		 return this.name.compareTo(another.name);
	}

	@Override
	public int hashCode() {
		 int hash = 31;
		 for (int index = 0; index < name.length(); index++) {
			  hash = hash + name.charAt(index);
		 }
		 return hash;
	}
}
