/*
 * Robert Robinson
 * SNHU CS499 
 * Slide Show enhancement with Data Structures/Algorithms 
 * December 7, 2019
 */


//Import libraries 

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

// Original class creation from first program

public class SlideShow extends JFrame {

	//Declare Variables
	private JPanel slidePane;
	private JPanel textPane;
	private JPanel buttonPane;
	private CardLayout card;
	private CardLayout cardText;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnAlpha;
	private JButton btnNumber;
	private JButton btnExit;
	private JLabel lblSlide;
	private JLabel lblTextArea;
	

	/**
	 * Create the application.
	 */
	public SlideShow() throws HeadlessException {
		initComponent();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponent() {
		
		//Initialize variables to empty objects
		card = new CardLayout();
		cardText = new CardLayout();
		slidePane = new JPanel();
		textPane = new JPanel();
		textPane.setBackground(Color.WHITE); 
		textPane.setBounds(5, 470, 790, 50);
		textPane.setVisible(true);
		buttonPane = new JPanel();
		btnPrev = new JButton();
		btnNext = new JButton();
		btnNumber = new JButton();
		btnAlpha = new JButton();
		lblSlide = new JLabel();
		lblTextArea = new JLabel();
		btnExit = new JButton();

		//Setup frame attributes
		setSize(800, 600);
		setLocationRelativeTo(null);
		setTitle("Top 5 Detox/Wellness Destinations SlideShow Numerical order"); // Numberical order to start
		getContentPane().setLayout(new BorderLayout(10, 50));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Create Vector and add data to vector (Data Structure)
		 */

		Vector<Sites> site = new Vector<Sites>();
		
		Sites site1 = new Sites(1, "Hippocrates Health Institute", "Set in a lush, tropical 50-acre setting in West Palm Beach, Florida.");
		site.add(site1);
		
		Sites site2 = new Sites(2, "Dear Lake Lodge", "Rated one of the best spas in Texas.");
		site.add(site2);

		Sites site3 = new Sites(3, "The Raj", "America’s Premiere Ayurvedic Health Center for Full Life Transformation.");
		site.add(site3);

		Sites site4 = new Sites(4, "Tree of Life Rejuvenation Center", 
		           "For those who want to reach and/or enhance their optimal potential for health, wellbeing, longevity, and spiritual awareness.");
		site.add(site4);
		
		Sites site5 = new Sites(5, "Red Mountain Resort", "One of the Most Luxurious Adventure Resorts in St. George, Utah.");
		site.add(site5);
		 
		//Setting the layouts for the panels
		slidePane.setLayout(card);
		textPane.setLayout(cardText);
		
		//logic to add each of the slides and text
		for (int i = 1; i <= 5; i++) {
			lblSlide = new JLabel();
			lblTextArea = new JLabel();
			
			// Modified to include vector values 
			lblSlide.setText(getResizeIcon(site.get(i-1).getRank()));
			lblTextArea.setText(getTextDescription(site.get(i-1).getRank(), site.get(i-1).getName(), site.get(i-1).getDetails()));
			
			//Refresh pane 
			slidePane.add(lblSlide, "card" + i);
			textPane.add(lblTextArea, "cardText" + i);
		}

		//place pane on cards
		getContentPane().add(slidePane, BorderLayout.CENTER);
	 	getContentPane().add(textPane, BorderLayout.SOUTH);

	 	//create a button pane
	 	buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		
	 	//create a button to sort by rank (number)
		btnNumber.setText("Number Sorted");
		btnNumber.addActionListener(new ActionListener(){
			
			// What should happen when the button is pressed
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Bubble sort to order the vector by rank (number)
				for (int c = site.size(); c > 0; c--) {
			        for (int d = 0; d < (c -1); d++) {
			            int f = Integer.compare(site.get(d+1).getRank(), (site.get(d).getRank()));
			        	if (f < 1) {
			        		Collections.swap (site, d, (d+1));
			             	}
			        	}
			        }
				
				// Change the window title to show numerical order
				setTitle("Top 5 Detox/Wellness Destinations SlideShow Numerical order"); // Change title of slide show 
				
				//Setting the layouts for the panels
				
				slidePane.setLayout(card);
				textPane.setLayout(cardText);
				
				//logic to add each of the slides and text
				for (int i = 1; i <= 5; i++) {
					lblSlide = new JLabel();
					lblTextArea = new JLabel();
					lblSlide.setText(getResizeIcon(site.get(i-1).getRank()));
					lblTextArea.setText(getTextDescription(site.get(i-1).getRank(), site.get(i-1).getName(), site.get(i-1).getDetails()));
					slidePane.add(lblSlide, "card" + i);
					textPane.add(lblTextArea, "cardText" + i);
				}

				getContentPane().add(slidePane, BorderLayout.CENTER);
							
			}
			
		});
		buttonPane.add(btnNumber); // add button to pane
		
		
		// create button to sort slides alphabetically 
		btnAlpha.setText("Alpha Sorted");
		btnAlpha.addActionListener(new ActionListener(){
			
			@Override
			// whats suppose to happen when button is pressed
			public void actionPerformed(ActionEvent e) {
				
			//Bubble sort to sort vector by name				
			for (int c = site.size(); c > 0; c--) {
			  for (int d = 0; d < (c -1); d++) {
			      int compare = (site.get(d).getName()).compareTo(site.get(d+1).getName());
			         if (compare > 1) {
			        		Collections.swap (site, d, (d+1));
			             	}
			            }
			        }
			
			setTitle("Top 5 Detox/Wellness Destinations SlideShow Alphabetical"); // Change title of slide show
			
			//Setting the layouts for the panels
			
			slidePane.setLayout(card);
			textPane.setLayout(cardText);
			
			
			
			
			//logic to add each of the slides and text
			for (int i = 1; i <= 5; i++) {
				lblSlide = new JLabel();
				lblTextArea = new JLabel();
				lblSlide.setText(getResizeIcon(site.get(i-1).getRank()));
				lblTextArea.setText(getTextDescription(site.get(i-1).getRank(), site.get(i-1).getName(), site.get(i-1).getDetails()));
				slidePane.add(lblSlide, "card" + i);
				textPane.add(lblTextArea, "cardText" + i);
			}

			getContentPane().add(slidePane, BorderLayout.CENTER);
			
			}
		});
		buttonPane.add(btnAlpha); // add button to pane
				
		// create button to go back to previous card
		btnPrev.setText("Previous");
		btnPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goPrevious();
			}
		});
		buttonPane.add(btnPrev);

		//create button to advance to next slide 
		btnNext.setText("Next");
		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				goNext();
			}
		});
		buttonPane.add(btnNext);

		// create button to exit program
		btnExit.setText("Exit");
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		buttonPane.add(btnExit);
				
		
		
		
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

	/**
	 * Previous Button Functionality
	 */
	private void goPrevious() {
		card.previous(slidePane);
		cardText.previous(textPane);
	}
	
	/**
	 * Next Button Functionality
	 */
	private void goNext() {
		
		card.next(slidePane);
		cardText.next(textPane);
				
	}
	
	
	
	

	/**
	 * Method to get the images
	 * Images were downloaded from company website and then resized to fit
	 * within from photos were then added to the resource directory and
	 * then the code modified to include the photos in the frame
	 * rank is used to keep the photos with the proper location when sorted 
	 */
	private String getResizeIcon(int rank) {
		String image = ""; 
		if (rank==1){
			image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Hippocrates_Health_Institute.jpg") + "'</body></html>";
		} else if (rank==2){
			image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Deer_lake_Lodge.JPG") + "'</body></html>";
		} else if (rank==3){
			image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/The_Raj.JPG") + "'</body></html>";
		} else if (rank==4){
			image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Tree_of_Life.JPG") + "'</body></html>";
		} else if (rank==5){
			image = "<html><body><img width= '800' height='500' src='" + getClass().getResource("/resources/Red_Mountain_Resort.JPG") + "'</body></html>";
		}
		return image;
	}
	
	/**
	 * Method to get the text values
	 * Order of the top five site was at the discretion of the developer and can be modified
	 * if required. A name and small description was added to each photo and future
	 * versions can redirect the user to the homepage of each location if desired  
	 */
	
	private String getTextDescription(int rank, String name, String details) {
		String text = ""; 		
		text = "<html><body><font size='5'>" +  name + " is ranked # " + rank + "</font><br> " + details + "</body></html>";
		return text;
	}
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				SlideShow ss = new SlideShow();
				ss.setVisible(true);
			}
		});
	}
}