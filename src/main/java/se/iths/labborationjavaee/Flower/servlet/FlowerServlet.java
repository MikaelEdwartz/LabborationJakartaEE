package se.iths.labborationjavaee.Flower.servlet;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.iths.labborationjavaee.Flower.entity.Flower;
import se.iths.labborationjavaee.Flower.repository.FlowerRepository;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "flowerServlet", value = "/flowers")
public class FlowerServlet extends HttpServlet {

    @Inject
    private FlowerRepository repository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String value = req.getParameter("q");

        String header = req.getHeader("host");
        // Hello
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "message" + "</h1>");
        out.println("</body></html>");
        /*      var flower = new Flower();
        flower.setName("blåsippemilf");
        repository.insertFlower(flower);

        super.doGet(req, resp);

        var path = req.getPathInfo();

        //  if (path == null || path.equals("/")) {
        resp.setContentType("text/html");
        var out = resp.getWriter();
        out.println("<html><body>");
        out.print("<h1>" + "Flowers" + "</h1>");
        // out.print("<h1>" + path + "</h1>");
        //   for (Flower flowers : repository.getAll())
        //      out.print("<div>" + flowers.getId() + "--" + flowers.getName() + "</div>");
        out.print("</body></html>");
*/
    }
}
//}




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



