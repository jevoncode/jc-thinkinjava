package com.jc.designpattern.enumerated;

import static com.jc.designpattern.enumerated.Outcome.DRAW;
import static com.jc.designpattern.enumerated.Outcome.LOSE;
import static com.jc.designpattern.enumerated.Outcome.WIN;
/**
 * 用枚举实现多路分发，由于枚举类型都一样，所以在这里不能使用重载，
 * 在这里用常量相关的方法技巧来实现多路分发
 * @author jevoncode
 */
public enum RoShamBo3 implements Competitor<RoShamBo3> {
	PAPER {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default: // To placate the compiler
			case PAPER:
				return DRAW;
			case SCISSORS:
				return LOSE;
			case ROCK:
				return WIN;
			}
		}
	},
	SCISSORS {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default:
			case PAPER:
				return WIN;
			case SCISSORS:
				return DRAW;
			case ROCK:
				return LOSE;
			}
		}
	},
	ROCK {
		public Outcome compete(RoShamBo3 it) {
			switch (it) {
			default:
			case PAPER:
				return LOSE;
			case SCISSORS:
				return WIN;
			case ROCK:
				return DRAW;
			}
		}
	};
	public abstract Outcome compete(RoShamBo3 it);

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo3.class, 20);
	}
}