package com.kronos.core.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TimeValue {
	long val;
	TimeUnit unit;

	public TimeValue(long val, TimeUnit unit) {
		this.val = val;
		this.unit = unit;
	}

	/**
	 * @return the val
	 */
	public long getVal() {
		return val;
	}

	/**
	 * @return the unit
	 */
	public TimeUnit getUnit() {
		return unit;
	}

	public TimeValue convert(TimeUnit tu) {
		return new TimeValue(tu.convert(getDuration(val, tu)), tu);

	}

	public static TimeUnit getNextTimeUnit(TimeUnit currentUnit) {
		switch (currentUnit) {
		case NANOSECONDS:
			return TimeUnit.MICROSECONDS;
		case MICROSECONDS:
			return TimeUnit.MILLISECONDS;
		case MILLISECONDS:
			return TimeUnit.SECONDS;
		case SECONDS:
			return TimeUnit.MINUTES;
		case MINUTES:
			return TimeUnit.HOURS;
		case HOURS:
			return TimeUnit.DAYS;
		case DAYS:
			// If the current unit is DAYS, there is no next unit
			throw new IllegalArgumentException("There is no next time unit after DAYS.");
		default:
			throw new IllegalArgumentException("Unknown time unit: " + currentUnit);
		}

	}

	public static Duration getDuration(long value, TimeUnit unit) {
		switch (unit) {
		case NANOSECONDS:
			return Duration.ofNanos(value);
		case MICROSECONDS:
			return Duration.ofNanos(TimeUnit.MICROSECONDS.toNanos(value));
		case MILLISECONDS:
			return Duration.ofMillis(value);
		case SECONDS:
			return Duration.ofSeconds(value);
		case MINUTES:
			return Duration.ofMinutes(value);
		case HOURS:
			return Duration.ofHours(value);
		case DAYS:
			return Duration.ofDays(value);
		default:
			throw new IllegalArgumentException("Unknown time unit: " + unit);
		}
	}
}
