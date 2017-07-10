package com.jc.designpattern.enumerated;

/**
 * 用枚举实现多路分发，由于枚举类型都一样，所以在这里不能使用重载，
 * 在这里用常量相关的方法技巧来实现多路分发，RoShamBo3的优化版
 * @author jevoncode
 */
public enum RoShamBo4 implements Competitor<RoShamBo4> {
	ROCK {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(SCISSORS, opponent);
		}
	},
	SCISSORS {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(PAPER, opponent);
		}
	},
	PAPER {
		public Outcome compete(RoShamBo4 opponent) {
			return compete(ROCK, opponent);
		}
	};
	Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
		return ((opponent == this) ? Outcome.DRAW : ((opponent == loser) ? Outcome.WIN : Outcome.LOSE));
	}

	public static void main(String[] args) {
		RoShamBo.play(RoShamBo4.class, 20);
	}
}