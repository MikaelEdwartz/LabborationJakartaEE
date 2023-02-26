package se.iths.labborationjavaee.Flower.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "flowerServlet", value = "/flowers")
public class FlowerServlet extends HttpServlet {

    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html><body>");
        out.println("<h1>" + "blåsippemilf" + "<h1>");
        out.print("</body></html>");

    }

    public void destroy() {

    }
}





