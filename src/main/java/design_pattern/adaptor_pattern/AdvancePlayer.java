package design_pattern.adaptor_pattern;

public class AdvancePlayer implements AdvanceMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("play vlc");
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("play mp4");
	}

}
