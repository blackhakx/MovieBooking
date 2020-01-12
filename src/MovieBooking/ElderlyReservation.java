/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : ElderlyReservation.java

package MovieBooking;

/**
 *
 * @author 91ken
 */
public class ElderlyReservation extends SeatReservation{

    public ElderlyReservation(char row, int col) {
        super(row, col);
    }
    
    @Override
    public float getTicketPrice() {
        float price;
        if(this.complementary == true){
            price = 0.00f;
        }
        else{
            price = super.getTicketPrice() * 0.70f;
            //price =  12.50f * 0.70f;
        }
        return price;
    }

}
