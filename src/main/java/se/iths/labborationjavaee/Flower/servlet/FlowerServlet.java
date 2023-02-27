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
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.repository.FlowerRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "flowerServlet", value = "/flowers/*")
public class FlowerServlet extends HttpServlet {

    @Inject
    private FlowerRepository repository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        for (int i = 0; i < 3; i++) {

            var flower = new Flower();
            flower.setName("ros");
            repository.insertFlower(flower);
        }
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

            resp.setContentType("text/html");
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {

        }

        Flower flower = JsonbBuilder.create().fromJson(buffer.toString(), Flower.class);
        repository.insertFlower(flower);

    }
}



/*

        if(path == null || path.equals("/")){
            resp.setContentType("text/html");
            var out = resp.getWriter();
            out.println("<html><body>");
            out.print("<h1>" + "Flowers" + "</h1>");
            out.print("<h1>" + path + "</h1>");
            for (Flower flower : repository.getAll())
                out.print("<div>" + flower.getId() + "--" + flower.getName() + "</div>");
            out.print("</body></html>");
 */



