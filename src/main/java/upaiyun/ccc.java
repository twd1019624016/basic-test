package upaiyun;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.core.util.SystemClock;
import org.junit.Test;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class ccc {

	public static void main(String[] args) {
		// 1561910400000
		System.out.println();
		System.out.println(LocalDate.now());

		System.out.println(new BigDecimal("12").equals(null));

		String paramValue = "";
		System.out.println(paramValue != "");
		
		Date fff = new Date(1561910400000L);
		System.out.println(fff);
	/*	LocalDateTime dateTime = LocalDateTime.ofEpochSecond(epochSecond, nanoOfSecond, offset)*/
		Instant ofEpochMilli = Instant.ofEpochMilli(1561910400000L);
		System.out.println(ofEpochMilli);
		LocalDateTime ofInstant = LocalDateTime.ofInstant(ofEpochMilli, Clock.systemDefaultZone().getZone());
		System.out.println(ofInstant);
	}

	public void demo1() {
		String str = "start";
		for (int i = 0; i < 100; i++) {
			str = str + "hello";
		}
	}

	@Test
	public void demo2() {
		String str = "start";

		str = str + "hello";

		str = str + 1;

		List<?> list2 = new ArrayList<Foo>();

		ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
			@Override
			protected DateFormat initialValue() {
				return new SimpleDateFormat("yyyy-MM-dd");
			}
		};

		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");
		for (String temp : a) {
			if ("1".equals(temp)) {
				a.remove(temp);
			}
		}

	}

	class Foo {

	}
	
}
