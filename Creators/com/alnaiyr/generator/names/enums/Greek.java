
package com.alnaiyr.generator.names.enums;

public enum Greek {
	ALPHA("α"), BETA("β"), GAMMA("γ", "Γ"), DELTA("δ", "Δ"), EPSILON("ε"), ZETA("ζ"), ETA("η"), THETA("θ", "Θ"), IOTA("ι"), KAPPA("κ"), LAMBDA("λ", "Λ"), MU(
			"μ"), NU("ν"), XI("ξ"), OMICRON("ο"), PI("π", "Π"), RHO("ρ"), SIGMA("σ", "Σ", "ς"), TAU("τ"), UPSILON("υ"), PHI("φ", "Φ"), CHI("χ"), PSI("ψ", "Ψ"), OMEGA(
			"ω", "Ω");
	
	public String	capital;
	
	public String	letter;
	
	public String	other;
	
	private Greek(String letter) {
	
		this.letter = letter;
		capital=letter;
		other=letter;
	}
	
	private Greek(String letter, String capital) {
	
		this.letter = letter;
		this.capital = capital;
		other=letter;
	}
	
	private Greek(String letter, String capital, String other) {
	
		this.letter = letter;
		this.capital = capital;
		this.other = other;
	}
}
