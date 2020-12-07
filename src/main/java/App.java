import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sightings",Sightings.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals",Animals.all());
            model.put("endangered",EndangeredAnimal.allEndangered());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/sighting/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals",Animals.all());
            model.put("endangered",EndangeredAnimal.allEndangered());
            return new ModelAndView(model, "sightings-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals",Animals.all());
            model.put("endangered",EndangeredAnimal.allEndangered());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        get("/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("adult",EndangeredAnimal.ADULT);
            model.put("young",EndangeredAnimal.YOUNG);
            model.put("newborn",EndangeredAnimal.NEWBORN);
            model.put("healthy",EndangeredAnimal.HEALTHY);
            model.put("average",EndangeredAnimal.AVERAGE);
            model.put("ill",EndangeredAnimal.ILL);
            return new ModelAndView(model, "endangeredAnimal-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/animal", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            Animals animal = new Animals(name);
            animal.save();
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        post("/sighting", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            Sightings newSight = new Sightings(animalId,location,rangerName);
            newSight.save();
            response.redirect("/sightings");
            return null;
        }, new HandlebarsTemplateEngine());

        post("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            EndangeredAnimal animal = new EndangeredAnimal(name,health,age);
            animal.save();
            response.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());
    }
}
