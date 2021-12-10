package rofl;

import java.io.Serializable;

public class ROFL implements Serializable {

	static {
		System.out.println("ROFL CINIT!");
	}

	public ROFL() {
		System.out.println("ROFL CTOR!");
	}

	@Override public String toString() {
		System.out.println("ROFL TOSTRING!");
		return "ROFL!";
	}
}
