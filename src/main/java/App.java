import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/animal/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
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
