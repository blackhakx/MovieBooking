/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : SeatReservation.java

package MovieBooking;

/**
 *
 * @author 91ken
 */
public abstract class SeatReservation {
    private char row;
    private int col;
    protected boolean complementary;
    
    public SeatReservation(char row, int col){
        Character.toUpperCase(row);
        this.row = row;
        this.col = col;
    }
    
    public float getTicketPrice(){
      return 12.50f;  
    }
    
    public void setComplementary(boolean complementary){
        this.complementary = complementary;
    }

    public char getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    
}
