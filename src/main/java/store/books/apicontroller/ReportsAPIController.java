package store.books.apicontroller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.ReportsServicePortal;
import store.books.Mong_DAL.model.UpdateRequest;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/lb-reports")
public class ReportsAPIController {

    @GetMapping("/get-all-reports/{type}")
    public <E> ArrayList<E> getAllArrays(@PathVariable String type) {
        try {
            return ReportsServicePortal.initReportsArrayFromDTB(type);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @RequestMapping(path="/add/{type}/{str}", method= RequestMethod.POST)
    public void addReport(@PathVariable String str, @PathVariable String type) {
        ReportsServicePortal.createReport(str, type);
    }

    @RequestMapping(path="/search/{type}/{year}/{month}", method = RequestMethod.GET)
    public <E> ArrayList<E> searchForReport(@PathVariable String type, @PathVariable int year, @PathVariable String month) throws IOException {
        return ReportsServicePortal.search(type, year, month);
    }

    @RequestMapping(path="/search/{type}/{year}/0", method=RequestMethod.PATCH)
    public <E> ArrayList<E> searchForYear(@PathVariable String type, @PathVariable int year) throws IOException {
        return ReportsServicePortal.search(type, year);
    }

    @RequestMapping(path="/search/{type}/0/{month}", method=RequestMethod.PATCH)
    public <E> ArrayList<E> searchForMonth(@PathVariable String type, @PathVariable String month) throws IOException {
        return ReportsServicePortal.search(type, month);
    }

    @RequestMapping(path="/update/{type}/{year}/{month}", method=RequestMethod.PATCH)
    public void updateReport(@RequestBody UpdateRequest obj, @PathVariable String type, @PathVariable int year, @PathVariable String month) throws IOException {
        ReportsServicePortal.updateReport(obj, type, year, month);
    }

    @RequestMapping(path= "/delete/{type}/{year}/{month}",method = RequestMethod.DELETE)
    public void deleteReport(@PathVariable int year, @PathVariable String month, @PathVariable String type) throws IOException {
        ReportsServicePortal.deleteReport(year, month, type);
    }
}
