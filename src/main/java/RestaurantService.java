import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();


    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{

        Restaurant returnRest = null;

            for (Restaurant restaurant : restaurants) {                       //To browse through the array of restaurants
                if (( restaurant.getName () ).equals ( restaurantName )) {    // Match the input restaurant with the ones in the array
                    returnRest = restaurant;

                }

            }
    if (returnRest == null)
            throw new restaurantNotFoundException (restaurantName);


    return returnRest;


    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if (restaurantToBeRemoved == null)
            throw new restaurantNotFoundException(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
