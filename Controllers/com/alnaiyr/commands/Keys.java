/**
 * an Al Naiyr production, all right reserved. Une production Al naiyr, tous
 * droits réservés
 */
package com.alnaiyr.commands;

/**
 * An improvement to the Input class of slick, but still works with the input
 * class. just the way
 * 
 * @author IgoЯ
 * @version 1.0
 *          <p>
 *          <Strong>Version Change:</Strong>
 *          <ul>
 *          <li><em>No Changes</em></li>
 *          </ul>
 */
public enum Keys {

	/** */
	ESCAPE(0x01),
	/** */
	N1(0x02),
	/** */
	N2(0x03),
	/** */
	N3(0x04),
	/** */
	N4(0x05),
	/** */
	N5(0x06),
	/** */
	N6(0x07),
	/** */
	N7(0x08),
	/** */
	N8(0x09),
	/** */
	N9(0x0A),
	/** */
	N0(0x0B),
	/** */
	MINUS(0x0C), /* - on main keyboard */
	/** */
	EQUALS(0x0D),
	/** */
	BACK(0x0E), /* backspace */
	/** */
	TAB(0x0F),
	/** */
	Q(0x10),
	/** */
	W(0x11),
	/** */
	E(0x12),
	/** */
	R(0x13),
	/** */
	T(0x14),
	/** */
	Y(0x15),
	/** */
	U(0x16),
	/** */
	I(0x17),
	/** */
	O(0x18),
	/** */
	P(0x19),
	/** */
	LBRACKET(0x1A),
	/** */
	RBRACKET(0x1B),
	/** */
	RETURN(0x1C), /* Enter on main keyboard */
	/** */
	ENTER(0x1C), /* Enter on main keyboard */
	/** */
	LCONTROL(0x1D),
	/** */
	A(0x1E),
	/** */
	S(0x1F),
	/** */
	D(0x20),
	/** */
	F(0x21),
	/** */
	G(0x22),
	/** */
	H(0x23),
	/** */
	J(0x24),
	/** */
	K(0x25),
	/** */
	L(0x26),
	/** */
	SEMICOLON(0x27),
	/** */
	APOSTROPHE(0x28),
	/** */
	GRAVE(0x29), /* accent grave */
	/** */
	LSHIFT(0x2A),
	/** */
	BACKSLASH(0x2B),
	/** */
	Z(0x2C),
	/** */
	X(0x2D),
	/** */
	C(0x2E),
	/** */
	V(0x2F),
	/** */
	B(0x30),
	/** */
	N(0x31),
	/** */
	M(0x32),
	/** */
	COMMA(0x33),
	/** */
	PERIOD(0x34), /* . on main keyboard */
	/** */
	SLASH(0x35), /* / on main keyboard */
	/** */
	RSHIFT(0x36),
	/** */
	MULTIPLY(0x37), /* * on numeric keypad */
	/** */
	LMENU(0x38), /* left Alt */
	/** */
	SPACE(0x39),
	/** */
	CAPITAL(0x3A),
	/** */
	F1(0x3B),
	/** */
	F2(0x3C),
	/** */
	F3(0x3D),
	/** */
	F4(0x3E),
	/** */
	F5(0x3F),
	/** */
	F6(0x40),
	/** */
	F7(0x41),
	/** */
	F8(0x42),
	/** */
	F9(0x43),
	/** */
	F10(0x44),
	/** */
	NUMLOCK(0x45),
	/** */
	SCROLL(0x46), /* Scroll Lock */
	/** */
	NUMPAD7(0x47),
	/** */
	NUMPAD8(0x48),
	/** */
	NUMPAD9(0x49),
	/** */
	SUBTRACT(0x4A), /* - on numeric keypad */
	/** */
	NUMPAD4(0x4B),
	/** */
	NUMPAD5(0x4C),
	/** */
	NUMPAD6(0x4D),
	/** */
	ADD(0x4E), /* + on numeric keypad */
	/** */
	NUMPAD1(0x4F),
	/** */
	NUMPAD2(0x50),
	/** */
	NUMPAD3(0x51),
	/** */
	NUMPAD0(0x52),
	/** */
	DECIMAL(0x53), /* . on numeric keypad */
	/** */
	F11(0x57),
	/** */
	F12(0x58),
	/** */
	F13(0x64), /* (NEC PC98) */
	/** */
	F14(0x65), /* (NEC PC98) */
	/** */
	F15(0x66), /* (NEC PC98) */
	/** */
	KANA(0x70), /* (Japanese keyboard) */
	/** */
	CONVERT(0x79), /* (Japanese keyboard) */
	/** */
	NOCONVERT(0x7B), /* (Japanese keyboard) */
	/** */
	YEN(0x7D), /* (Japanese keyboard) */
	/** */
	NUMPADEQUALS(0x8D), /*
						 * (on numeric keypad (NEC PC98)
						 */
	/** */
	CIRCUMFLEX(0x90), /* (Japanese keyboard) */
	/** */
	AT(0x91), /* (NEC PC98) */
	/** */
	COLON(0x92), /* (NEC PC98) */
	/** */
	UNDERLINE(0x93), /* (NEC PC98) */
	/** */
	KANJI(0x94), /* (Japanese keyboard) */
	/** */
	STOP(0x95), /* (NEC PC98) */
	/** */
	AX(0x96), /* (Japan AX) */
	/** */
	UNLABELED(0x97), /* (J3100) */
	/** */
	NUMPADENTER(0x9C), /* Enter on numeric keypad */
	/** */
	RCONTROL(0x9D),
	/** */
	NUMPADCOMMA(0xB3), /*
						 * , on numeric keypad (NEC PC98)
						 */
	/** */
	DIVIDE(0xB5), /* / on numeric keypad */
	/** */
	SYSRQ(0xB7),
	/** */
	RMENU(0xB8), /* right Alt */
	/** */
	PAUSE(0xC5), /* Pause */
	/** */
	HOME(0xC7), /* Home on arrow keypad */
	/** */
	UP(0xC8), /* UpArrow on arrow keypad */
	/** */
	PRIOR(0xC9), /* PgUp on arrow keypad */
	/** */
	LEFT(0xCB), /* LeftArrow on arrow keypad */
	/** */
	RIGHT(0xCD), /* RightArrow on arrow keypad */
	/** */
	END(0xCF), /* End on arrow keypad */
	/** */
	DOWN(0xD0), /* DownArrow on arrow keypad */
	/** */
	NEXT(0xD1), /* PgDn on arrow keypad */
	/** */
	INSERT(0xD2), /* Insert on arrow keypad */
	/** */
	DELETE(0xD3), /* Delete on arrow keypad */
	/** */
	LWIN(0xDB), /* Left Windows key */
	/** */
	RWIN(0xDC), /* Right Windows key */
	/** */
	APPS(0xDD), /* AppMenu key */
	/** */
	POWER(0xDE),
	/** */
	SLEEP(0xDF),

	/** A helper for left ALT */
	LALT(LMENU.value),
	/** A helper for right ALT */
	RALT(RMENU.value),

	/** */
	MOUSE_LEFT_BUTTON(0),

	/** */
	MOUSE_RIGHT_BUTTON(1),

	/** */
	MOUSE_MIDDLE_BUTTON(2);

	public int value;
	public Boolean activated;

	private Keys(int value) {
		this.value = value;
	}

}
