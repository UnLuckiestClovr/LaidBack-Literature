package store.books.apicontroller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.ReportsServicePortal;
import store.books.Mong_DAL.model.UpdateRequest;

import java.nio.file.Path;

@RestController
@RequestMapping("/lb-literature")
public class ReportsAPIController {



    @RequestMapping(path="/add/{type}", method= RequestMethod.POST)
    public void addReport(@RequestBody String obj, @PathVariable String type) {
        ReportsServicePortal.createReport(obj, type);
    }

    @RequestMapping(path="/update/{type}/{year}/{month}", method=RequestMethod.PATCH)
    public void updateReport(@RequestBody UpdateRequest obj, @PathVariable String type, @PathVariable int year, @PathVariable String month) {
        ReportsServicePortal.updateReport(obj, type, year, month);
    }

    @RequestMapping(path= "/delete/{type}/{year}/{month}",method = RequestMethod.DELETE)
    public void deleteReport(@PathVariable String type, @PathVariable int year, @PathVariable String month){
        ReportsServicePortal.deleteReport(year, month, type);
    }
}
