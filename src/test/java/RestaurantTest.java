//import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @BeforeEach
    void setUp() {
        LocalTime openingTime = LocalTime.parse ( "10:30:00" );
        LocalTime closingTime = LocalTime.parse ( "22:00:00" );
        this.restaurant = new Restaurant ( "Amelie's cafe", "Chennai", openingTime, closingTime );

        restaurant.addToMenu ( "Sweet corn soup", 119 );
        restaurant.addToMenu ( "Vegetable lasagne", 269 );
        restaurant.addToMenu ( "Idli", 50 );
        restaurant.addToMenu ( "vada", 40 );
        restaurant.addToMenu ( "Coffee", 0 );
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {


        LocalTime localTime = LocalTime.parse ( "12:00:00" );


        Restaurant spyRestaurant = Mockito.spy ( restaurant );
        Mockito.when ( spyRestaurant.getCurrentTime () ).thenReturn ( localTime );


        assertThat ( spyRestaurant.isRestaurantOpen (), equalTo ( true ) );

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {


        LocalTime localTime = LocalTime.parse ( "23:00:00" );


        Restaurant spyRestaurant = Mockito.spy ( restaurant );
        Mockito.when ( spyRestaurant.getCurrentTime () ).thenReturn ( localTime );


        assertThat ( spyRestaurant.isRestaurantOpen (), equalTo ( false ) );

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {


        int initialMenuSize = restaurant.getMenu ().size ();
        restaurant.addToMenu ( "Sizzling brownie", 319 );

        assertEquals ( initialMenuSize + 1, restaurant.getMenu ().size () );
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu ().size ();
        restaurant.removeFromMenu ( "Vegetable lasagne" );

        assertEquals ( initialMenuSize - 1, restaurant.getMenu ().size () );
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows ( itemNotFoundException.class,
                () -> restaurant.removeFromMenu ( "French fries" ) );

    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void Order_without_items_Should_throw_invalidOrderException() throws invalidOrderException{

        List<String> myOrder=new ArrayList <> ();


        assertThrows(invalidOrderException.class,
                ()->restaurant.totalOrderValue (myOrder));
    }

    @Test
    public void Total_Order_Value_LessThanOrEqualToZero_Should_Throw_InvalidOrderException() throws invalidOrderException{

        List<String> myOrder=new ArrayList <> ();
        myOrder.add ("Coffee");



        assertThrows(invalidOrderException.class,
                ()->restaurant.totalOrderValue (myOrder));
    }



    @Test
    public void Selecting_Item_from_menu_Should_give_the_total_Cost() throws invalidOrderException{

        List<String> myOrder=new ArrayList <> ();
        myOrder.add ("Idli");
        myOrder.add ("vada");
        int orderValue= restaurant.totalOrderValue ( myOrder);

        assertEquals (90, orderValue  );
    }

}