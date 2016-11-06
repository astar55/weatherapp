package controllers;

import models.ZipCode;
import org.json.JSONArray;
import org.json.JSONObject;
import play.Logger;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import play.data.FormFactory;
import javax.inject.Inject;
import javax.json.JsonObject;
import java.util.List;

import static play.libs.Json.*;

public class Application extends Controller {

    @Inject
    FormFactory formFactory;

    public Result index() {
        return ok(index.render());
    }

    @Transactional
    public Result addZipCode() {
        DataGet dataGet = new DataGet();
        ZipCode zipcode = formFactory.form(ZipCode.class).bindFromRequest().get();
        zipcode.htmldata = dataGet.obtainData(zipcode.zipcode);
        try {
            JPA.em().persist(zipcode);
        }
        catch(Exception e) {
            JPA.em().merge(zipcode);
        }
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public Result getZipCode() {
        List<ZipCode> zipcodes = (List<ZipCode>) JPA.em().createQuery("select p from ZipCode p Order by Id").getResultList();
        if(zipcodes.size() > 0) {
            ZipCode zipCode = zipcodes.get(zipcodes.size()-1);
            zipCode.htmldata = zipCode.htmldata.substring(1,zipCode.htmldata.length()-1)
                    .replace("\\", "");
            return  ok(toJson(zipCode));
        }
        return ok(toJson(zipcodes));
    }

    public Result details(String path) {
        return redirect(routes.Application.index());
    }
}
