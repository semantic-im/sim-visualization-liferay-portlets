package ro.utcluj.larkc.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;

public class LarkcLeds {
	
	Boolean disp;
	Boolean platformRunning;
	Boolean platfromError;
	HorizontalPanel mp;
	Image img;
	String url = "http://users.utcluj.ro/~negrum/images/"; 
	//String url = "C:\\bundles\\plugins\\portlets\\vis-toolbar-portlet\\docroot\\images\\";
	
	
	Timer refreshTimer_leds = new Timer() {
		public void run() {
			refresh_leds();
		}
	};
	
	public LarkcLeds(HorizontalPanel hp) {
		this.mp = hp;
		mp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		img = new Image();
		
		img.setSize("110","33");
		img.setUrl(url + "gray_led.png");
		hp.add(img);
				
		/*
		led_label = new Label();
		led_label.setAlign(Alignment.CENTER);
		led_label.setHeight(33);
		led_label.setWidth(110);
		//icon is located in the war/images directory
		led_label.setIcon(url + "gray_led.png");
		led_label.setIconHeight(33);
		led_label.setIconWidth(110);
		*/
		disp = true;
		platformRunning = true;
		platfromError = false;
		
		
		refreshTimer_leds.scheduleRepeating(1000);
	}
	
	private void refresh_leds() {
		if(platfromError) {
			//led_label.setIcon("/images/red_led.png");
			img.setUrl(url + "red_led.png");
		}
		else if (!platformRunning) {
			//led_label.setIcon("/images/gray_led.png");
			img.setUrl(url + "gray_led.png");
		}
		else if (disp) {
			disp = false;
			//led_label.setIcon("/images/white_led.png");
			//led_label.redraw();
			img.setUrl(url + "white_led.png");
		}
		else {
			disp = true;
			//led_label.setIcon("/images/green_led.png");
			//led_label.redraw();
			img.setUrl(url + "green_led.png");
		}
	}

	public Boolean getPlatformRunning() {
		return platformRunning;
	}

	public void setPlatformRunning(Boolean platformRunning) {
		this.platformRunning = platformRunning;
	}

	public Boolean getPlatfromError() {
		return platfromError;
	}

	public void setPlatfromError(Boolean platfromError) {
		this.platfromError = platfromError;
	}
}
