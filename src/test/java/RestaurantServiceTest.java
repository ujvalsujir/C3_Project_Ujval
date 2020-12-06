import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class
RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    RestaurantTest restaurantTest;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    void setUp() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        this.restaurant= new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {

        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");


        service.addRestaurant ( "Pumpkin Tales","Chennai", restaurant.openingTime, restaurant.closingTime);
        service.addRestaurant ( "Amelie's cafe","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00") );


        String resName = "Amelie's cafe";
        Restaurant sampleRestaurant = new Restaurant ( "Amelie's cafe", "Chennai", restaurant.openingTime, restaurant.closingTime );

        assertEquals ( sampleRestaurant.getName (), service.findRestaurantByName ( resName ).getName () );




    }
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {

        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");


        service.addRestaurant ( "Pumpkin Tales","Chennai",restaurant.openingTime,restaurant.closingTime );
        service.addRestaurant ( "Amelie's cafe","Chennai",restaurant.openingTime,restaurant.closingTime );



        Restaurant sampleRestaurant = new Restaurant ( "resName", "Chennai", restaurant.openingTime, restaurant.closingTime );


        assertThrows(restaurantNotFoundException.class,()->{

            service.findRestaurantByName ( sampleRestaurant.getName () );
        });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",restaurant.openingTime,restaurant.closingTime);


        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        //restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);


        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        //LocalTime openingTime = LocalTime.parse("10:30:00");
        //LocalTime closingTime = LocalTime.parse("22:00:00");
        //restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);


        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>



    }
