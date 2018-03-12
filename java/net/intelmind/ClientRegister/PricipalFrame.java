package net.intelmind.ClientRegister;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.opencv.videoio.VideoCapture;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import net.intelmind.ClientRegister.dao.IClientRepository;
import net.intelmind.ClientRegister.entities.Client;
import net.intelmind.ClientRegister.myFunctions.MyFunctions;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JInternalFrame;
import java.awt.Rectangle;

@SpringBootApplication
public class PricipalFrame extends JFrame{

	private JPanel contentPane;
	private JPanel grayPanel;
	private JPanel pinkPanel;
	private JTextField textFieldName;
	private JTextField textFieldPhone;
	private JPanel camPanel;
	private CameraPanel cp;	 
	private PanelPhoto photoPanel;
	private List<Client> clients;
	
	
	//IClientRepository daoClientImpl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		ApplicationContext ctx = SpringApplication.run(PricipalFrame.class);
		
		IClientRepository daoClientImpl = ctx.getBean(IClientRepository.class);		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PricipalFrame frame = new PricipalFrame(daoClientImpl);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PricipalFrame(IClientRepository dao) {
		
				
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 800, 600);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			
			JPanel sidePanel = new JPanel();
			sidePanel.setBackground(new Color(51, 51, 51));
			
			JPanel topPanel = new JPanel();
			topPanel.setBackground(new Color(153, 0, 0));
			
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(BorderFactory.createTitledBorder(
			        ""));
			
			GroupLayout gl_contentPane = new GroupLayout(contentPane);
			gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(sidePanel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE))
							.addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
								.addGap(29)))
						.addGap(0))
			);
			sidePanel.setLayout(new BorderLayout(0, 0));
			
			/*JLabel lblList0 = new JLabel("Client List");
			JLabel lblList1 = new JLabel("Register new Client");
			JLabel[] listText = new JLabel[]{lblList0, lblList1};*/
			
			String[] listText = new String[] {"          Client List        ", "          Register new Client   "};
			
			JPanel panelMainNav = new JPanel();
			panelMainNav.setBackground(Color.BLACK);
			sidePanel.add(panelMainNav, BorderLayout.NORTH);
			
			JLabel lblMainNavigation = new JLabel("Main Navigation");
			panelMainNav.add(lblMainNavigation);
			lblMainNavigation.setHorizontalAlignment(SwingConstants.CENTER);
			lblMainNavigation.setForeground(Color.WHITE);
			
			JPanel panelJList = new JPanel();
			panelJList.setBackground(Color.DARK_GRAY);
			sidePanel.add(panelJList, BorderLayout.CENTER);
			
			JList list = new JList(listText);
			panelJList.add(list);
			list.setBackground(Color.DARK_GRAY);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setLayoutOrientation(JList.VERTICAL);
			list.setForeground(Color.GREEN);
			GridLayout gl_list = new GridLayout();
			list.setLayout(gl_list);
			
			////////////////////////////////////////////////////////////////////////////
			/////////// Add actions to list ///////////////////////////////////////////
			
			list.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					
					if (e.getValueIsAdjusting() == false) {
			
				        if (list.getSelectedIndex() == 1) {
				        //No selection, disable fire button.
				        	layeredPane.moveToFront(grayPanel);
			
				        } else {
				        //Selection, enable the fire button.
				        	layeredPane.moveToFront(pinkPanel);
				        }
				    }
					
					
				}
			});		
			sidePanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelMainNav}));
					
			grayPanel = new JPanel();
					
			pinkPanel = new JPanel();
			pinkPanel.setBackground(Color.PINK);
			
			camPanel = new JPanel();
			camPanel.setBackground(Color.GRAY);
			
			JPanel registPanel = new JPanel();
			
			GroupLayout gl_grayPanel = new GroupLayout(grayPanel);
			gl_grayPanel.setHorizontalGroup(
				gl_grayPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_grayPanel.createSequentialGroup()
						.addComponent(camPanel, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(registPanel, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
			);
			gl_grayPanel.setVerticalGroup(
				gl_grayPanel.createParallelGroup(Alignment.TRAILING)
					.addComponent(camPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addComponent(registPanel, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
			);
			
			photoPanel = new PanelPhoto();
			photoPanel.setBorder(new LineBorder(new Color(204, 153, 204)));
			
			JPanel infoPanel = new JPanel();
			infoPanel.setBorder(null);
			
			infoPanel.setLayout(new GridLayout(0, 2,0, 3));
					
			for(int i=1; i<=4; i++) {
				infoPanel.add(new JPanel());
			}
			
			JLabel lblName = new JLabel("Name");
			
			infoPanel.add(lblName);
			
			textFieldName = new JTextField();
			infoPanel.add(textFieldName);
			textFieldName.setColumns(10);
			
			for(int i=1; i<=4; i++) {
				infoPanel.add(new JPanel());
			}
			
			JLabel lblPhoneNum = new JLabel("Phone Num");
			
			infoPanel.add(lblPhoneNum);
			
			textFieldPhone = new JTextField();
			infoPanel.add(textFieldPhone);
			textFieldPhone.setColumns(10);
						
			for(int i=1; i<=13;i++) {
				infoPanel.add(new JPanel());
			}
			
			JButton btnRegister = new JButton("Register");
			infoPanel.add(btnRegister);
			GroupLayout gl_registPanel = new GroupLayout(registPanel);
			gl_registPanel.setHorizontalGroup(
				gl_registPanel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_registPanel.createSequentialGroup()
						.addGap(21)
						.addComponent(photoPanel, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addGap(18)
						.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
			);
			gl_registPanel.setVerticalGroup(
				gl_registPanel.createParallelGroup(Alignment.LEADING)
					.addComponent(infoPanel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addGroup(gl_registPanel.createSequentialGroup()
						.addGap(47)
						.addComponent(photoPanel, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
						.addGap(223))
			);
			registPanel.setLayout(gl_registPanel);
							
			for(int i=1; i<=13; i++) {
				infoPanel.add(new JPanel());
			}
					
			camPanel.setLayout(new BorderLayout(0, 0));
			
			JPanel northCamPanel = new JPanel();
			northCamPanel.setBackground(new Color(0, 204, 102));
			camPanel.add(northCamPanel, BorderLayout.NORTH);
					
			JPanel southCamPanel = new JPanel();
			southCamPanel.setBackground(new Color(0, 204, 102));
			camPanel.add(southCamPanel, BorderLayout.SOUTH);
			
			JButton btnCamera = new JButton("Camera on");
			northCamPanel.add(btnCamera);	
			
			JButton btnTakePhoto = new JButton("Take photo");
			southCamPanel.add(btnTakePhoto);
			
			grayPanel.setLayout(gl_grayPanel);
			GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
			gl_layeredPane.setHorizontalGroup(
				gl_layeredPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup()
						.addGap(4)
						.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(pinkPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
							.addComponent(grayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(4))
			);
			gl_layeredPane.setVerticalGroup(
				gl_layeredPane.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, gl_layeredPane.createSequentialGroup()
						.addGap(16)
						.addGroup(gl_layeredPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(pinkPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
							.addComponent(grayPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
						.addGap(55))
			);
					
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//////////// Fill the information of user's list ///////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			JPanel userResumeTitle = new JPanel();
			userResumeTitle.setLayout(new GridLayout(0, 5, 2, 2));
			
			JLabel lblPhoto = new JLabel("     Photo");
			userResumeTitle.add(lblPhoto);
			
			JLabel lblNameShow = new JLabel("     Name");
			userResumeTitle.add(lblNameShow);
			
			JLabel lblPhone = new JLabel("     Phone");
			userResumeTitle.add(lblPhone);
			
			JLabel lblDate = new JLabel("     Date");
			userResumeTitle.add(lblDate);
			
			JLabel lblAction = new JLabel("     Action");
			userResumeTitle.add(lblAction);
			
			JPanel userResumeContent = new JPanel();
			userResumeContent.setLayout(new GridLayout(0, 5, 1, 1));
			
			GroupLayout gl_pinkPanel = new GroupLayout(pinkPanel);
			gl_pinkPanel.setHorizontalGroup(
				gl_pinkPanel.createParallelGroup(Alignment.LEADING)
					.addComponent(userResumeTitle, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
					.addComponent(userResumeContent, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
			);
			gl_pinkPanel.setVerticalGroup(
				gl_pinkPanel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pinkPanel.createSequentialGroup()
						.addComponent(userResumeTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(userResumeContent, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
			);
			pinkPanel.setLayout(gl_pinkPanel);
			
			this.clients = dao.findAll();
			int numRows = clients.size();
			
			System.out.println(numRows);
			
			for(int i=0; i<numRows; i++) {
					
					int id = clients.get(i).getId();
										
					BufferedImage bi = ImageIO.read(new File("PhotosDatabase/"+id+".jpg"));
					
					PanelPhoto panelPhoto = new PanelPhoto();
					
					panelPhoto.setBi(bi);
					panelPhoto.repaint();
					
					userResumeContent.add(panelPhoto);					
					
					userResumeContent.add(new JLabel(clients.get(i).getName()));
					userResumeContent.add(new JLabel(clients.get(i).getPhoneNum()));
					
					String duration = MyFunctions.duration(clients.get(i).getDateCreation());
					userResumeContent.add(new JLabel(duration));
					
					JPanel panelButton = new JPanel();
					JButton btnUpdate = new JButton("Update"); JButton btnDelete = new JButton("Delete");					
					panelButton.add(btnUpdate); panelButton.add(btnDelete);					
					userResumeContent.add(panelButton);
					
					Client client = clients.get(i);
					
					btnDelete.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
														
							dao.delete(id);
							File photoDelete = new File("PhotosDatabase/"+id+".jpg");							
							photoDelete.delete();
						}						
					});
					
					btnUpdate.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							FrameUpdate frame = new FrameUpdate(dao, client);
							frame.setVisible(true);
							
						}						
					});
				
			}			
			
			layeredPane.setLayout(gl_layeredPane);		
			contentPane.setLayout(gl_contentPane);
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			btnTakePhoto.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					File output = new File("photos/camPhoto.jpg");
					
					try {
						ImageIO.write(cp.bi, "jpg", output);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						
						BufferedImage bi2 = ImageIO.read(new File("photos/camPhoto.jpg"));					
						
						photoPanel.setBi(bi2);
						photoPanel.repaint();												
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}			
				
			});

			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			btnCamera.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					JInternalFrame internalFrame = new JInternalFrame("");
					camPanel.add(internalFrame, BorderLayout.CENTER);
					internalFrame.setVisible(true);				
									
					System.loadLibrary("opencv_java340");
					
					VideoCapture list = new VideoCapture(0);
					cp = new CameraPanel();
					Thread thread = new Thread(cp);	
					JMenu cameras = new JMenu("Cameras");
					JMenuBar bar = new JMenuBar();
					bar.add(cameras);
					int i=1;
					while(list.isOpened()) {
						
						JMenuItem cam = new JMenuItem("Camera "+ i);
						cam.addActionListener(this);
						cameras.add(cam);
						list.release(); list = new VideoCapture(i);
						i++;
					}
					thread.start();
					internalFrame.getContentPane().add(cp);
					internalFrame.setJMenuBar(bar);	
					internalFrame.setClosable(true);
									
				}				
				
			});		
			
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			btnRegister.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {				
					
					
						
						String name = textFieldName.getText();
						String phoneNum = textFieldPhone.getText();
						
					// Save the user's data into the Database			
						
						Client ClientSave = dao.save(new Client(name, phoneNum, MyFunctions.dateTimeToString(LocalDateTime.now())));
						String photoName = ClientSave.getId()+".jpg";					
						
						
						// Copy and save the user's photo in the folder "PhotosDatabase". The photo's name is the same in the Database
							try {
								File img = new File("photos/camPhoto.jpg");
								FileInputStream fis = new FileInputStream(img);
								File photoDB = new File("PhotosDatabase/" + photoName);
								FileOutputStream fos = new FileOutputStream(photoDB);
								
								int c;
								while ((c=fis.read()) != -1){
									fos.write(c);
								}
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}						
							
				}			
			
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	
	}//close Constructor
}//close Class
