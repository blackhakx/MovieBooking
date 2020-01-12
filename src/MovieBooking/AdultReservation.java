/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : AdultReservation.java

package MovieBooking;

/**
 *
 * @author 91ken
 */
public class AdultReservation extends SeatReservation{

    public AdultReservation(char row, int col) {
        super(row, col);
    }

    @Override
    public float getTicketPrice() {
        float price;
        if(this.complementary == true){
            price = 0.00f;
        }
        else{
            price = 12.50f;
        }
        return price;
    }
    
    

}
