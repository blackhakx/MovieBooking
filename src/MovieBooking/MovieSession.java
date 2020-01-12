/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : MovieSession.java

package MovieBooking;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 91ken
 */
public class MovieSession implements Comparable<MovieSession>{
    private final String movieName;
    private final char rating;
    private final Time sessionTime;
    private SeatReservation[][] sessionSeats = new SeatReservation[NUM_ROWS][NUM_COLS];
    public static int NUM_ROWS = 8;
    public static int NUM_COLS = 6;
    
    public MovieSession(String movieName, char rating, Time sessionTime) throws InvalidParameterException{
        if((rating == 'R') || (rating == 'M') || (rating == 'G')){
            this.movieName = movieName;
            this.rating = rating;
            this.sessionTime = sessionTime;
        }
        else{
            throw new InvalidParameterException("Invalid Rating Parameter");
        }
    }
    
    public static int convertRowToIndex(char rowLetter){
        return (rowLetter - 'A');
    }
    
    public static char convertIndexToRow(int rowIndex){
        return (char) (rowIndex + 65);
    }
    
    public char getRating() {
        return rating;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public Time getSessionTime() {
        return sessionTime;
    }
    //Implement
    public SeatReservation getSeat(char row, int col){
        if(!isSeatAvailable(row,col)){
            return sessionSeats[MovieSession.convertRowToIndex(row)][col];
        }
        else{return null;}
    }
    //Implement
    public boolean isSeatAvailable(char row, int col){
        Character.toUpperCase(row);
        return (this.sessionSeats[MovieSession.convertRowToIndex(row)][col] == null);
    }
    //Implement
    public boolean applyBookings(List<SeatReservation> reservations){
        
        boolean hasChild = false;
        boolean hasAdult = false;
        for(SeatReservation seat : reservations){
            //check seat available
            if(!isSeatAvailable(seat.getRow(), seat.getCol())){
                return false;
            }
            //Check if booking has adult
            if(seat instanceof ChildReservation){
                hasChild = true;
            }
            else {
               hasAdult = true; 
            }
            //If movie is R and there is a child - return false.
            if(this.rating == 'R' && hasChild){
                return false;
            }
        }
        //Check if M movie and child is not accompanied by adult - return false.
        if(this.rating == 'M' && hasChild && !hasAdult){
            return false;
        }
        //Place all seat on list to sessionSeats.
        for(SeatReservation setSeat : reservations){
            sessionSeats[MovieSession.convertRowToIndex(setSeat.getRow())][setSeat.getCol()] = setSeat;
        }
        return true;
    }
    
    public void printSeats(){
        for(int y = 0; y < NUM_ROWS; y++){
            System.out.print("\n");
            for(int x = 0; x < NUM_COLS; x++){
                System.out.print("|");
                if(this.isSeatAvailable(MovieSession.convertIndexToRow(y), x)){
                    System.out.print("_");
                }
                // && sessionSeats[y][x].getClass() != AdultReservation.class
                else if(sessionSeats[y][x] instanceof AdultReservation){
                    System.out.print("A");
                }
                else if(sessionSeats[y][x] instanceof ChildReservation){
                    System.out.print("C");
                }
                else if(sessionSeats[y][x] instanceof ElderlyReservation){
                    System.out.print("E");
                }
                
                System.out.print("|");
            }
        } 
    }
    
    public static void main(String[] args){
        //Add and test list sorting
        ArrayList<MovieSession> moviesList = new ArrayList();
        moviesList.add(new MovieSession("Boss Baby", 'G', new Time(14,30)));
        moviesList.add(new MovieSession("Moana", 'M', new Time(12)));
        moviesList.add(new MovieSession("Kong: Skull Island", 'M', new Time(10)));
        moviesList.add(new MovieSession("Logan", 'R', new Time(10)));
        moviesList.add(new MovieSession("Power Rangers", 'M', new Time(13)));
        Collections.sort(moviesList);
        for(MovieSession mv : moviesList){
            System.out.println(mv.toString());
            SeatReservation seat1 = new AdultReservation('A', 2);
            //SeatReservation seat2 = new AdultReservation('A', 3);
            SeatReservation seat2 = new ChildReservation('A', 3);
            //SeatReservation seat3 = new ChildReservation('A', 4);
            SeatReservation seat3 = new ElderlyReservation('B',5);
            ArrayList<SeatReservation> seats = new ArrayList();
            seats.add(seat1);
            seats.add(seat2);
            seats.add(seat3);
            boolean done = mv.applyBookings(seats);
            System.out.println(done);
            mv.printSeats();
        }
    }

    @Override
    public int compareTo(MovieSession t) {
        if((this.sessionTime.compareTo(t.getSessionTime())) == 0){
            int result = this.movieName.compareTo(t.getMovieName());
            if(result == 0){return 0;}
            else if(result > 0){return 1;}
            else{return -1;}
        }
        else{return (this.sessionTime.compareTo(t.getSessionTime()));}
    }

    @Override
    public String toString() {
        return (this.getMovieName() + " (" + this.getRating() + ") " + "- " + this.getSessionTime().toString());
    }
    
    

}
