package com.jc.designpattern.enumerated;

import static com.jc.designpattern.enumerated.Outcome.DRAW;
import static com.jc.designpattern.enumerated.Outcome.LOSE;
import static com.jc.designpattern.enumerated.Outcome.WIN;


/**
 * 用枚举实现多路分发，由于枚举类型都一样，所以在这里不能使用重载，
 * 在这里用数组技巧来实现多路分发
 * @author jevoncode
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {
	PAPER, SCISSORS, ROCK;
	private static Outcome[][] table = { 
			{ DRAW, LOSE, WIN }, // PAPER
			{ WIN, DRAW, LOSE }, // SCISSORS
			{ LOSE, WIN, DRAW }, // ROCK
	};

	public Outcome compete(RoShamBo6 other) {
		return table[this.ordinal()][other.ordinal()];
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo6.class, 20);
	}
}