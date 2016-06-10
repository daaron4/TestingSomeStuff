package CardModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class CardBack {
	
	private int place;
	private BufferedImage image;
	private JLabel label;
	private static final String imageDir = System.getProperty("user.dir")
			+ File.separator + "cardImages" + File.separator;
	
	public CardBack() {
		loadImage();
		setLabel((new JLabel(new ImageIcon(image))));
		setPlace(-1);
	}
	
	public void loadImage() {
		try {
			setImage(ImageIO.read(new File(imageDir + "backOfCard.png")));
		} catch (IOException e) {
			System.out.println("Could not find the image!");
		}
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	
}
