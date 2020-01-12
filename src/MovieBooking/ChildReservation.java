/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// File name : ChildReservation.java

package MovieBooking;

/**
 *
 * @author 91ken
 */
public class ChildReservation extends SeatReservation{

    public ChildReservation(char row, int col) {
        super(row, col);
    }

    @Override
    public float getTicketPrice() {
        float price;
        if(this.complementary == true){
            price = 0.00f;
        }
        else{
            price = 8.00f;
        }
        return price;
    }

}
