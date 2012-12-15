package com.alnaiyr.generator.names.enums;

public enum Alphabet{
	A("a"),E("e"),I("i"),
	O("o"),U("u"),Y("y"),
	B("b"),C("c"),D("d")
	,F("f"),G("g"),H("h"),
	J("j"),K("k"),L("l")
	,M("m"),N("n"),P("p"),
	Q("q"),R("r"),S("s")
	,T("t"),V("v"),W("w"),
	X("x"),Z("z");
	
	
	public String letter;
    private Alphabet(String letter) {
            this.letter = letter;
    }
	
}
	
