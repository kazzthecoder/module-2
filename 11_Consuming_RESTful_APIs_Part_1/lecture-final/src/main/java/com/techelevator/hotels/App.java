package com.techelevator.hotels;

import com.techelevator.hotels.model.Hotel;
import com.techelevator.hotels.model.StarWarsMovie;
import com.techelevator.hotels.services.ConsoleService;
import com.techelevator.hotels.services.HotelService;

public class App {

    private final ConsoleService consoleService = new ConsoleService();
    private final HotelService hotelService = new HotelService();

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        int menuSelection = -1;

        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection();
            if (menuSelection == ConsoleService.LIST_ALL_HOTELS) {
                Hotel[] hotels = hotelService.listHotels();
                consoleService.printHotels(hotels);
            } else if (menuSelection == ConsoleService.LIST_ALL_REVIEWS) {
                consoleService.printReviews(hotelService.listReviews());
            } else if (menuSelection == ConsoleService.SHOW_HOTEL_1) {
                System.out.println(hotelService.getHotelById(999));
            } else if (menuSelection == ConsoleService.LIST_HOTELS_3_STAR) {
                System.out.println("Not implemented");
            } else if (menuSelection == ConsoleService.PUBLIC_API_QUERY) {
                StarWarsMovie movie = hotelService.getWithCustomQuery();
                System.out.println(movie);
            } else if (menuSelection == ConsoleService.EXIT) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

}
