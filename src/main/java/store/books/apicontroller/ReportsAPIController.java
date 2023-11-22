package store.books.apicontroller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.ReportsServicePortal;
import store.books.Mong_DAL.model.UpdateRequest;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class ReportsAPIController {

    @RequestMapping(path="/add/{type}", method= RequestMethod.POST)
    public void addReport(@RequestBody String obj, @PathVariable String type) {
        ReportsServicePortal.createReport(obj, type);
    }

    @RequestMapping(path="/search/{type}/{year}/{month}", method = RequestMethod.GET)
    public <E> ArrayList<E> searchForReport(@PathVariable String type, @PathVariable int year, @PathVariable String month) throws IOException {
        return ReportsServicePortal.search(type, year, month);
    }

    @RequestMapping(path="/update/{type}/{year}/0", method=RequestMethod.PATCH)
    public <E> ArrayList<E> updateReport(@RequestBody UpdateRequest obj, @PathVariable String type, @PathVariable int year) throws IOException {
        return ReportsServicePortal.search(type, year);
    }

    @RequestMapping(path= "/delete/{type}/0/{month}",method = RequestMethod.DELETE)
    public <E> ArrayList<E> deleteReport(@PathVariable String type, @PathVariable String month) throws IOException {
        return ReportsServicePortal.search(type, month);
    }
}
