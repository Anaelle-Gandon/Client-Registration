package net.intelmind.ClientRegister;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.intelmind.ClientRegister.dao.IClientRepository;
import net.intelmind.ClientRegister.entities.Client;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameUpdate extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUpdate frame = new FrameUpdate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FrameUpdate(IClientRepository dao, Client client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(49, Short.MAX_VALUE))
		);
		panel.setLayout(new GridLayout(0, 2, 3, 3));
		
		for(int i=1; i<=4; i++) {
			panel.add(new JPanel());
		}
		
		JLabel lblName = new JLabel("Name  ");
		panel.add(lblName);
		JTextField txtName = new JTextField();
		panel.add(txtName);
		JLabel lblPhone = new JLabel("Phone number  ");
		panel.add(lblPhone);
		JTextField txtPhone = new JTextField();
		panel.add(txtPhone);
		
		for(int i=1; i<=3; i++) {
			panel.add(new JPanel());
		}
		
		JButton btnUpdate = new JButton("Update");
		
		panel.add(btnUpdate);		
		
		contentPane.setLayout(gl_contentPane);
		
		///////////////////////////////////////////////////////////////////
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Client clientRetrieve = dao.findOne(client.getId());
				Client clientUpdate = new Client();
				clientUpdate.setId(clientRetrieve.getId());
				clientUpdate.setDateCreation(clientRetrieve.getDateCreation());
				clientUpdate.setName(txtName.getText());
				clientUpdate.setPhoneNum(txtPhone.getText());
				
				dao.save(clientUpdate);
			}
			
		});
	}

}
