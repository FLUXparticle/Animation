package de.fluxparticle.animation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnimationCollection {

	private final SortedMap<Integer, Collection<Timeframe>> animations = new TreeMap<>();

	public void add(int priority, Timeframe Timeframe) {
        animations.computeIfAbsent(priority, k -> new LinkedList<>()).add(Timeframe);
	}

	public void appendToClip(Clip clip) {
		for (Collection<Timeframe> values : animations.values()) {
			clip.invoke(() -> values.forEach(clip::addTimeframe), false);
		}
	}

    public void appendToClipRaw(Clip clip) {
        for (Collection<Timeframe> values : animations.values()) {
            values.forEach(clip::addTimeframe);
        }
    }

}
