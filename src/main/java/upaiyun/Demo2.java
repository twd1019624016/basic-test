package upaiyun;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.junit.Test;

public class Demo2 {

	public static void main(String[] args) {

		String str = "陈小春=独家的记忆;郑伊健=古惑仔;贾立平=魔方盲拧";
		StringTokenizer tokenizer = new StringTokenizer(str, ";=");
		while (tokenizer.hasMoreTokens()) {
			System.out.println(tokenizer.nextToken());
		}
	}

	@Test
	public void BitSetTest() {

		BitSet bitSet1 = new BitSet(16);
		BitSet bitSet2 = new BitSet(16);
		for (int i = 0; i < 16; i++) {

			if (i % 2 == 0)
				bitSet1.set(i);
			if (i % 5 == 0)
				bitSet2.set(i);
		}

		Optional<Integer> reduce = Arrays.asList(1, 2, 3).stream().reduce((a, b) -> {
			return (a + b);
		});
		Integer reduce2 = Arrays.asList(1, 4, 9, 16).stream().reduce(1, (a, b) -> {
			return (int) (a * Math.sqrt(b));
		});
		Integer reduce3 = Arrays.asList(1, 4, 9, 16).parallelStream().reduce(1, (a, b) -> {
			return (int) (a * Math.sqrt(b));
		}, (a, b) -> {
			return a * b;
		});
		Stream<String> map = Arrays.asList(1, 4, 9, 16).stream().map(a -> (a + ""));
		LongStream mapToLong = Arrays.asList(1, 4, 9, 16).stream().mapToLong(a -> a);
		System.out.println(bitSet1);
		System.out.println(bitSet2);
	}
}
