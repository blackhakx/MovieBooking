/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : MovieBookingGUI.java

package MovieBooking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author 91ken
 */
public class MovieBookingGUI extends JPanel implements ActionListener{
    
    public final int PANEL_WIDTH = 400;
    public final int PANEL_HEIGHT = 400;
    //private DrawingPanel drawPanel;
    private final JRadioButton adultButton, elderlyButton, childButton;
    private final JCheckBox complementaryButton;
    private final JButton exitButton;
    private final JButton cancelButton;
    private final JButton bookButton;
    private final JList<MovieSession> movieSessionsList;
    private final DefaultListModel movieList;
    private final JLabel titleLabel;
    private final ArrayList<MovieSession> movieSessions;
    private final Color adultColor = Color.BLUE;
    private final Color elderlyColor = Color.WHITE;
    private final Color childColor = Color.YELLOW;
    private final Color emptyColor = Color.LIGHT_GRAY;
    private final JButton[][] seatingButtons;
    private static final int NUM_ROWS = MovieSession.NUM_ROWS;
    private static final int NUM_COLS = MovieSession.NUM_COLS;
    private ArrayList<SeatReservation> currentReservation = new ArrayList();
    private JPanel seatPanel;
    
    
    public static void main(String[] args){
        ArrayList<MovieSession> movies = new ArrayList();
        //Test to catch invalid parameters in constructor
        try{
            movies.add(new MovieSession("Boss Baby", 'R', new Time(25)));
        }
        catch(InvalidParameterException e){
            System.out.println("Invalid Parameter: " + e.getMessage());
        }
        
        try{
            movies.add(new MovieSession("Boss Baby", 'L', new Time(23)));
        }
        catch(InvalidParameterException e){
            System.out.println("Invalid Parameter: " + e.getMessage());
        }
        
        try{
            movies.add(new MovieSession("Boss Baby", 'r', new Time(23,66)));
        }
        catch(InvalidParameterException e){
            System.out.println("Invalid Parameter: " + e.getMessage());
        }
        
        try{
            movies.add(new MovieSession("Boss Baby", 'R', new Time(23,23,66)));
        }
        catch(InvalidParameterException e){
            System.out.println("Invalid Parameter: " + e.getMessage());
        }
        //Test accept all valid parameter constructor
        try{
            movies.add(new MovieSession("Boss Baby", 'G', new Time(14,30)));
        movies.add(new MovieSession("Moana", 'M', new Time(12)));
        movies.add(new MovieSession("Kong: Skull Island", 'M', new Time(10)));
        movies.add(new MovieSession("Logan", 'R', new Time(10)));
        movies.add(new MovieSession("Power Rangers", 'M', new Time(13)));
        }
        catch(InvalidParameterException e){
            System.out.println("Invalid Parameter: " + e.getMessage());
        }
        
        MovieBookingGUI myPanel = new MovieBookingGUI(movies);
        JFrame frame = new JFrame("Movies N Chill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(myPanel);
        frame.pack();
        frame.setMinimumSize(new Dimension(550,400));
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(new Point((d.width/2) - (frame.getWidth() / 2), (d.height / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }

    public MovieBookingGUI(ArrayList<MovieSession> movieSessions) {
        super(new BorderLayout());
        this.movieSessions = movieSessions;
        Collections.sort(this.movieSessions);

        //Seat Panels
        seatPanel = new JPanel(new GridLayout(NUM_ROWS,NUM_COLS));
        seatPanel.setPreferredSize(new Dimension(500,500));
        seatingButtons = new JButton[NUM_ROWS][NUM_COLS];
        for (int y=0; y<NUM_ROWS; y++){
            for (int x=0; x<NUM_COLS; x++){
                seatingButtons[y][x] = new JButton(Character.toString(MovieSession.convertIndexToRow(y))+ Integer.toString(x));
                seatingButtons[y][x].addActionListener(this);
                seatingButtons[y][x].setBackground(emptyColor);                
                seatPanel.add(seatingButtons[y][x]);
            }
        }
        add(seatPanel, BorderLayout.CENTER);
        
        //JList section
        movieList = new DefaultListModel();
        for(MovieSession movieItem : movieSessions){
            
            this.movieList.addElement(movieItem);
        }
        movieSessionsList = new JList(movieList);
        movieSessionsList.setSelectedIndex(0);
        movieSessionsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                setSeatButtons();
            }
        });
        //If movie list cannot fit on jlist window
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(movieSessionsList);

        //Button Panel
        adultButton = new JRadioButton("Adult", true);
        elderlyButton = new JRadioButton("Elderly");
        childButton = new JRadioButton("Child");
        
        //Make button group for adult, elderly, child
        ButtonGroup cusTypeButton = new ButtonGroup();
        cusTypeButton.add(adultButton);
        cusTypeButton.add(elderlyButton);
        cusTypeButton.add(childButton);
        //Complementary Button
        complementaryButton = new JCheckBox("Complementary", false);
        
        //JButtons
        exitButton = new JButton("Exit");
        cancelButton = new JButton("Cancel");
        bookButton = new JButton("Book");
        bookButton.addActionListener(this);
        cancelButton.addActionListener(this);
        exitButton.addActionListener(this);
        
        //Create Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(this.adultButton);
        buttonPanel.add(this.elderlyButton);
        buttonPanel.add(this.childButton);
        buttonPanel.add(this.complementaryButton);
        buttonPanel.add(this.exitButton);
        buttonPanel.add(this.cancelButton);
        buttonPanel.add(this.bookButton);
        //Title Panel
        JPanel titlePanel = new JPanel();
        titleLabel = new JLabel("Movies N Chill");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        titlePanel.add(titleLabel);
        
        add(seatPanel);
        add(titlePanel, BorderLayout.NORTH);
        //add(this.movieSessionsList, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);
    }
    
    public void setSeatButtons(){
        MovieSession movieSelected = movieSessionsList.getSelectedValue();
        //loop seat
        for (int y=0; y<NUM_ROWS; y++){
            for (int x=0; x<NUM_COLS; x++){
                //if seat is empty
                if(movieSelected.isSeatAvailable(MovieSession.convertIndexToRow(y), x)){
                    seatingButtons[y][x].setBackground(emptyColor);
                    seatingButtons[y][x].setForeground(Color.BLACK);
                    seatingButtons[y][x].setEnabled(true);     
                }
                //Seat is taken
                else{
                    seatingButtons[y][x].setForeground(Color.GRAY);
                    seatingButtons[y][x].setEnabled(false);
                    SeatReservation seat = movieSelected.getSeat(MovieSession.convertIndexToRow(y) ,x);
                    //Determine seat class
                    
                    if(seat instanceof AdultReservation){
                        
                        seatingButtons[y][x].setBackground(adultColor);
                        
                    }
                    else if(seat instanceof ElderlyReservation){
                        seatingButtons[y][x].setBackground(elderlyColor);
                        
                        
                    }
                    //if(seat instanceof ChildReservation)
                    else {
                        seatingButtons[y][x].setBackground(childColor);
                        
                    }
                }
            }
        }
        seatPanel.repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == bookButton){
            if(currentReservation.isEmpty()){
                JOptionPane.showMessageDialog(this,"No Seats Selected\nPlease Select Seat", "No Seats Selected", JOptionPane.ERROR_MESSAGE);
            }
            else{
                boolean bookSuc = (movieSessionsList.getSelectedValue()).applyBookings(currentReservation);
                if(bookSuc){
                    System.out.println("Booking Successful");
                    float totalCost = 0;
                    //get total seat cost
                    for(SeatReservation seat : currentReservation){
                        totalCost += seat.getTicketPrice();
                    }
                    JOptionPane.showMessageDialog(this,"TICKET COST IS: $"+String.format("%.2f", totalCost), currentReservation.size() + " Tickets", JOptionPane.INFORMATION_MESSAGE);
                    currentReservation.clear();
                    setSeatButtons();  
                }
                else{
                    JOptionPane.showMessageDialog(this,"CANNOT BOOK CHILD IN R OR UNSUPERVISED IN M MOVIES", currentReservation.size() + " Tickets Not Booked", JOptionPane.ERROR_MESSAGE);
                    currentReservation.clear();
                    setSeatButtons();
                }
            }
        }
        else if(source == cancelButton){
            setSeatButtons();
            currentReservation.clear();
        }
        else if(source == exitButton){
            System.exit(0);
        }
        //else if(seatingButtonsae.getActionCommand() == )
        else{
            boolean exitLoop = false;
            for(int y = 0; y<NUM_ROWS; y++){
                for(int x=0; x<NUM_COLS; x++){
                   //StringTokenizer tokenizer = new StringTokenizer(ae.getActionCommand(), "[]");
            
                    //Change Selected Color
                    if(source == seatingButtons[y][x]){
                        SeatReservation selectedSeat;
                        if(adultButton.isSelected()){
                            seatingButtons[y][x].setBackground(adultColor);
                            seatingButtons[y][x].setForeground(Color.GRAY);
                            selectedSeat = new AdultReservation(MovieSession.convertIndexToRow(y), x);
                            if(complementaryButton.isSelected()){
                                selectedSeat.setComplementary(true);
                            }
                            currentReservation.add(selectedSeat);
                            
                        }
                        else if(elderlyButton.isSelected()){
                            seatingButtons[y][x].setBackground(elderlyColor);
                            selectedSeat = new ElderlyReservation(MovieSession.convertIndexToRow(y), x);
                            if(complementaryButton.isSelected()){
                                selectedSeat.setComplementary(true);
                            }
                            currentReservation.add(selectedSeat);
                        }
                        else if(childButton.isSelected()){
                            seatingButtons[y][x].setBackground(childColor);
                            selectedSeat = new ChildReservation(MovieSession.convertIndexToRow(y), x);
                            if(complementaryButton.isSelected()){
                                selectedSeat.setComplementary(true);
                            }
                            currentReservation.add(selectedSeat);
                        }
                        seatingButtons[y][x].setEnabled(false);
                        exitLoop = true;
                    }
                    
                    if(exitLoop){
                        break;
                    }
                }
                if(exitLoop){break;}
            }
        }
    }
}
