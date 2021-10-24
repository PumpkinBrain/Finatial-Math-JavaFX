package application;

public class Data {
	private double pv, fv, i, n;
	private boolean boolPv, boolI, boolN;
	private char tax, time;
	protected static byte state;
	protected static boolean unknown = false;
	
//formula section
	//simple
	protected double simpleFv() {
		i = i * n;
		i = 1 + i;
		fv = pv * i;
		return fv;
	}
	
	protected double simplePv() {
		i = i * n;
		i = 1 + i;
		pv = fv / i;
		return pv;
	}
	
	protected double simpleI() {
		fv = fv / pv;
		fv = fv - 1;
		i = fv / n;
		return i;
	}
	
	protected double simpleN() {
		fv = fv / pv;
		fv = fv - 1;
		n = fv / i;
		return n;
	}
	
	//compound
	protected double compoundFv() {
		i = Math.pow(i, n);
		i = 1 + i;
		fv = pv * i;
		return fv;
	}
	
	protected double compoundPv() {
		i = Math.pow(i, n);
		i = 1 + i;
		pv = fv / i;
		return pv;
	}
	
	protected double compoundI() {
		fv = fv / pv;
		fv = fv - 1;
		i = Math.pow(fv, 1/n);
		return i;
	}
	
	protected double compoundN() {
		fv = fv / pv;
		fv = Math.log10(fv);
		i = 1 + i;
		Math.log10(i);
		n = fv / i;
		return n;
	}
	
//getters and setters
	protected double getPv() {
		return pv;
	}
	protected void setPv(String pv) {
		try {
			this.pv = Double.parseDouble(pv);
			boolPv = false;
			unknown = false;
			System.out.println("Pv false");
		} catch (Exception e) {
			boolPv = true;
			unknown = true;
			System.out.println("Pv true");
		}
	}

	protected double getFv() {
		return fv;
	}
	protected void setFv(String fv) {
		try {
			this.fv = Double.parseDouble(fv);
		} catch(Exception e) {
			System.out.println("fv not a number");
		}
	}

	protected double getI() {
		return i;
	}
	protected void setI(String i) {
		try {
			this.i = Double.parseDouble(i);
			boolI = false;
			unknown = false;
			System.out.println("I false");
		} catch(Exception e) {
			boolI = true;
			unknown = true;
			System.out.println("I true");
		}
	}

	protected double getN() {
		return n;
	}
	protected void setN(String n) {
		try {
			this.n = Double.parseDouble(n);
			boolN = false;
			unknown = false;
			System.out.println("N false");
		} catch(Exception e) {
			boolN = true;
			unknown = true;
			System.out.println("N true");
		}
	}

	protected boolean isBoolPv() {
		return boolPv;
	}

	protected boolean isBoolI() {
		return boolI;
	}

	protected boolean isBoolN() {
		return boolN;
	}

	protected char getTax() {
		return tax;
	}
	protected void setTax(char tax) {
		if((tax == 'd') || (tax == 'm') || (tax == 'y')) {
			this.tax = tax;
		} else {
			System.out.println("invalid");
		}
	}

	protected char getTime() {
		return time;
	}
	protected void setTime(char time) {
		if((time == 'd') || (time == 'm') || (time == 'y')) {
			this.time= time;
		} else {
			System.out.println("invalid");
		}
	}
}