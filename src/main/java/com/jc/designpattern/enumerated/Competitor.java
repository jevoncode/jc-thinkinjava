package com.jc.designpattern.enumerated;

public interface Competitor<T extends Competitor<T>> {
	Outcome compete(T competitor);
}