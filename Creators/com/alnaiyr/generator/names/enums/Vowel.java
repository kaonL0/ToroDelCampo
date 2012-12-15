package com.alnaiyr.generator.names.enums;


public enum Vowel{
	A("a"),E("e"),I("i"),
	O("o"),U("u"),Y("y");
	
	public String letter;
    private Vowel(String letter) {
            this.letter = letter;
    }
}
