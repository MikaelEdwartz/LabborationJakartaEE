package se.iths.labborationjavaee.Flower.servlet;

import jakarta.inject.Inject;
import jakarta.json.bind.JsonbBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.iths.labborationjavaee.Flower.controller.FoodController;
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.repository.FlowerRepository;
import se.iths.labborationjavaee.Flower.resource.RegexMatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "flowerServlet", value = "/flowers/*")
public class FlowerServlet extends HttpServlet {

    @Inject
    private FlowerRepository repository;
    @Inject
    private FoodController foodController;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        var path = req.getPathInfo();

        if (path == null || path.equals("/")) {
            resp.setContentType("text/html");
            var out = resp.getWriter();
            out.println("<html><body>");
            out.print("<h1>" + "Flowers" + "</h1>");
            out.print("<h1>" + path + "</h1>");
            for (Flower flowers : repository.findAll())
                out.print("<div>" + flowers.getId() + ":" + flowers.getName() + "</div>");
            out.print("</body></html>");

        } else {
            var search = path.substring(1);
            resp.setContentType("application/json");

            var out = resp.getWriter();

            if (RegexMatcher.isLetter(search)) {
                var flower = repository.findByName(search);
                out.print(JsonbBuilder.create().toJson(flower));
            } else if (RegexMatcher.isNumber(search)) {
                var id = Long.parseLong(search);
                var flower = repository.findById(id);
                out.print(JsonbBuilder.create().toJson(flower));
            } else {
                resp.setContentType("text/html");
                resp.sendError(404);
            }

        }

    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        StringBuffer buffer = new StringBuffer();
//        String line = null;
//
//        try {
//            BufferedReader reader = req.getReader();
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line);
//            }
//        } catch (Exception e) {
//
//        }
//
//        Flower flower = JsonbBuilder.create().fromJson(buffer.toString(), Flower.class);
//        repository.insertFlower(flower);
//
//    }
}



