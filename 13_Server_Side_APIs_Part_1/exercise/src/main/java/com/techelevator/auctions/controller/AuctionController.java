package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDao dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDao();
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {
        if (title_like.equals("") && currentBid_lte == 0) {
            return dao.list();
        }
        if (title_like != null && currentBid_lte == 0) {
            return dao.searchByTitle(title_like);
        }
        if (title_like.equals("") && currentBid_lte > 0) {
            return dao.searchByPrice(currentBid_lte);
        }
        return dao.searchByTitleAndPrice(title_like, currentBid_lte);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return dao.get(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Auction create(@RequestBody Auction id) {
        return dao.create(id);
    }
}






