package com.Harmon.climber;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Sound{
	
	public AudioClip sound, countSound, bgSound, rainSound;
	
	public Sound(){
		
	}
	public void Thunder(){
		File fileThunder = new File("/Users/macuser/Documents/workspace/Climber/src/com/Harmon/climber/thunder.aiff");
		URI uri = fileThunder.toURI();
		URL url;
		try {
			url = uri.toURL();
			sound = Applet.newAudioClip(url);
			sound.play();
		} catch (MalformedURLException e) { e.printStackTrace();}
	}// end of thunder method
	public void CountDown3(){
		File fileCount = new File("/Users/macuser/Documents/workspace/Climber/src/com/Harmon/climber/countdown.wav");
		URI uriCount = fileCount.toURI();
		try{
			URL urlCount = uriCount.toURL();
			countSound = Applet.newAudioClip(urlCount);
			countSound.play();
		}catch(MalformedURLException ee){ ee.printStackTrace(); }
	}///  end of countdown method
	public void BackgroundMusic(){
		File bgFile = new File("/Users/macuser/Documents/workspace/Climber/src/com/Harmon/climber/ClimberBgMusic.aiff");
		URI bgURI = bgFile.toURI();
		try {
			URL bgURL = bgURI.toURL();
			bgSound = Applet.newAudioClip(bgURL);
			bgSound.loop();
		} catch (MalformedURLException e) { e.printStackTrace();}
	}/// end of background music method
	public void stopBgMusic(){
		bgSound.stop();
	}
	public void RainSound(){
		File rainFile = new File("/Users/macuser/Documents/workspace/Climber/src/com/Harmon/climber/Rain_Background-Mike_Koenig-1681389445.wav");
		URI uriRain = rainFile.toURI();
		URL rainURL;
		try {
			rainURL = uriRain.toURL();
			rainSound = Applet.newAudioClip(rainURL);
			rainSound.play();
		} catch (MalformedURLException e) { e.printStackTrace(); }
	}/// end of rain sound
	public AudioClip getSound() {
		return sound;
	}
	public void setSound(AudioClip sound) {
		this.sound = sound;
	}
	public AudioClip getCountSound() {
		return countSound;
	}
	public void setCountSound(AudioClip countSound) {
		this.countSound = countSound;
	}
	public AudioClip getBgSound() {
		return bgSound;
	}
	public void setBgSound(AudioClip bgSound) {
		this.bgSound = bgSound;
	}
	public AudioClip getRainSound() {
		return rainSound;
	}
	public void setRainSound(AudioClip rainSound) {
		this.rainSound = rainSound;
	}
}/// end of sound class
