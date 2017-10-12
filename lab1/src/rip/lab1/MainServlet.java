package rip.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Integer;

import rip.lab1.model.Atomizers;
import java.util.*;

import java.io.PrintWriter;

@WebServlet("/addAtomizer")
public class MainServlet extends HttpServlet {
    private List<Atomizer> m_atomizers = new ArrayList();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        renderHeader(response);
        String action = (String) request.getParameter("action");

        if (action.equals("show")) {
            showAtomizers(response);
        }
        else {
            addAtomizerForm(response);
        }

        renderFooter(response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Atomizer atomizer = new Atomizer();

        atomizer.name = request.getParameter("name");
        atomizer.manufacturer = request.getParameter("manufacturer");
        atomizer.type = request.getParameter("type");
        atomizer.numberOfCoils = Integer.parseInt(request.getParameter("numberOfCoils"));

        m_atomizers.add(atomizer);

        doGet(request, response);
    }

    private void showAtomizers(HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("<table border=\"1\">\n" +
                "   <caption>Atomizer</caption>\n" +
                "   <thead>" +
                "   <tr>\n" +
                "    <th>Name</th>\n" +
                "    <th>Manufacturer</th>\n" +
                "    <th>Type</th>\n" +
                "    <th>Number of coils</th>\n" +
                "   </tr>" +
                "   </thead>" +
                "   <tbody>" +
                "   <tr>");
        for ( Atomizer car: m_atomizers) {
            response.getWriter().println(
                    "<td>" + car.name + "</td>\n" +
                    "<td>" + car.manufacturer + "</td>\n" +
                    "<td>" + car.type + "</td>\n" +
                    "<td>" + car.numberOfCoils + "</td>\n" +
            );
        }
        response.getWriter().println( "</tr></tbody></table");
    }

    private void addAtomizerForm(HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        String htmlRespone =
                "    <form action=\"addAtomizer?action=\" method=\"post\">\n" +
                "        Name: <input type=\"text\" name=\"name\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Manufacturer: <input type=\"text\" name=\"manufacturer\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Type: <input type=\"text\" name=\"type\" pattern=\"[A-Za-zА-Яа-яЁё]{3, 40}\" /></br>\n" +
                "        Number of coils: <input type=\"text\" name=\"numberOfCoils\" pattern=\"^[0-9]+$\"/></br>\n" +
                "        <input name=\"action\" type=\"hidden\" value=\"addBook\"/>" +
                "        <input type=\"submit\" value=\"send\" />" +
                "    </form>\n";
        writer.println(htmlRespone);
    }

    public void renderHeader(HttpServletResponse response) throws IOException {
        response.getWriter().println("<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n");
    }

    private void renderFooter(HttpServletResponse response) throws IOException {
        response.getWriter().println("</body></html>");
    }
}