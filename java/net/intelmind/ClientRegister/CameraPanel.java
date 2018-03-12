package net.intelmind.ClientRegister;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraPanel extends JPanel implements Runnable {

	BufferedImage bi;
	VideoCapture vidCap;
	//JButton btnCapture; 
		
	public CameraPanel() {
		
		/*btnCapture = new JButton("Capture");		
		btnCapture.addActionListener(this);
		this.add(btnCapture);*/
	}

	/*@Override
	public void actionPerformed(ActionEvent arg0) {
		File output = new File("photos/camPhoto.jpg");
		
		try {
			ImageIO.write(bi, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	@Override
	public void run() {
		System.loadLibrary("opencv_java340");
		Mat webcam_img = new Mat();
		vidCap = new VideoCapture(0);
		
		if(vidCap.isOpened()) {
			
			while(true) {
			
				vidCap.read(webcam_img);
				if(!webcam_img.empty()) {
					//JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
					//topFrame.setSize(webcam_img.width()+550, webcam_img.height()+450);
					matToBufferedImage(webcam_img);
					repaint();
				}				
			}	
		}		
	}//fermer run()
	
	public void matToBufferedImage(Mat matRGB) {
		
		int width = matRGB.width(), height = matRGB.height(), channels = matRGB.channels();
		byte[] source = new byte[width*height*channels];
		matRGB.get(0,0, source);
		
		bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		final byte[] target = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
		System.arraycopy(source, 0, target, 0, source.length);
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		if(bi == null) return;
		g.drawImage(bi, 10, 40, bi.getWidth(), bi.getHeight(), null);
	}
	
	public void switchCamera(int x) {
		
		this.vidCap = new VideoCapture(x);
	}
	
	
}//fermer constructeur


















