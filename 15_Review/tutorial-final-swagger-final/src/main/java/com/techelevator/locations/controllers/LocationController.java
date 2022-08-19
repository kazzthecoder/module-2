package com.techelevator.locations.controllers;

import com.techelevator.locations.dao.LocationDao;
import com.techelevator.locations.exception.LocationNotFoundException;
import com.techelevator.locations.model.Location;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private LocationDao dao;

    public LocationController(LocationDao dao) {
        this.dao = dao;
    }

    @ApiOperation("Returns all of the Locations in the system")
    //@RequestMapping(path = "", method = RequestMethod.GET)
    @GetMapping
    public List<Location> list() {
        return dao.list();
    }

    @ApiOperation("Returns the Location with the given ID")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Location get(@PathVariable @ApiParam("ID of the Location to retrieve") int id) throws LocationNotFoundException {
        return dao.get(id);
    }

    @ApiOperation("Adds a new Location to the system")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Location add(@Valid @RequestBody @ApiParam("The Location to add") Location location) {
        return dao.create(location);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Location update(@Valid @RequestBody Location location, @PathVariable int id)
            throws LocationNotFoundException {
        return dao.update(location, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) throws LocationNotFoundException {
        dao.delete(id);
    }

}
