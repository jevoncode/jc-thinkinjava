package com.jc.designpattern.enumerated;

import static com.jc.designpattern.enumerated.Outcome.DRAW;
import static com.jc.designpattern.enumerated.Outcome.LOSE;
import static com.jc.designpattern.enumerated.Outcome.WIN;

/**
 * 用枚举实现多路分发，由于枚举类型都一样，所以在这里不能使用重载，
 * 还是用分组的技巧来实现多路分发
 * @author jevoncode
 *
 */
public enum RoShamBo2 implements Competitor<RoShamBo2> {
	PAPER(DRAW, LOSE, WIN), SCISSORS(WIN, DRAW, LOSE), ROCK(LOSE, WIN, DRAW);
	private Outcome vPAPER, vSCISSORS, vROCK;

	RoShamBo2(Outcome paper, Outcome scissors, Outcome rock) {
		this.vPAPER = paper;
		this.vSCISSORS = scissors;
		this.vROCK = rock;
	}

	public Outcome compete(RoShamBo2 it) {
		switch (it) {
		default:
		case PAPER:
			return vPAPER;
		case SCISSORS:
			return vSCISSORS;
		case ROCK:
			return vROCK;
		}
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo2.class, 20);
	}
}