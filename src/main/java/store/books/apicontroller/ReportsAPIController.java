package store.books.apicontroller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.ReportsServicePortal;
import store.books.Mong_DAL.model.UpdateRequest;

@RestController
@RequestMapping("/lb-literature")
public class ReportsAPIController {



    @RequestMapping(path="/add/{consumable}", method= RequestMethod.POST)
    public void addReport(@RequestBody String obj, @PathVariable String consumable) {
        ReportsServicePortal.createReport(obj);
    }

    @RequestMapping(path="/update/{consumable}", method=RequestMethod.PATCH)
    public void updateReport(@RequestBody UpdateRequest obj, @PathVariable String consumable) {
        ReportsServicePortal.updateReport(obj);
    }

    @RequestMapping(path= "/delete/{consumable}",method = RequestMethod.DELETE)
    public void deleteReport(@RequestBody String json, @PathVariable String consumable){
        ReportsServicePortal.deleteReport(json);
    }
}
