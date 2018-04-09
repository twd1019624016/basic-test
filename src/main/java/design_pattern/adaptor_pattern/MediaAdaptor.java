package design_pattern.adaptor_pattern;

public class MediaAdaptor implements MediaPlayer {
	
	AdvanceMediaPlayer advanceMediaPlayer;
	
	

	public MediaAdaptor() {
		super();
		this.advanceMediaPlayer = new AdvancePlayer();
	}



	@Override
	public void play(String audioType, String fileName) {
		if(audioType.equals("vlc")) {
			advanceMediaPlayer.playVlc(fileName);
		}else if(audioType.equals("mp5")){
			advanceMediaPlayer.playMp4(fileName);
		}else {
			System.out.println();
		}
		
		
	}

}
