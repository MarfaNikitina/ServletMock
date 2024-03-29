import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Model;
import logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;


@WebServlet(urlPatterns = "/addUser.html")
public class ServletAddView extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User user = new User(name, surname, salary);
        model.Add(user, counter.getAndIncrement());

        pw.print("<html" + "<h3> Пользователь " + name + " " + surname + " с зарплатой" +
                salary + " успешно создан! :)</h3>" +
                "<a href=\"addUser.html\">Создать нового пользователя<a><br/>" +
                "<a href=\"index.jsp\">Домой<a><br/>" + "</html>");
    }
}