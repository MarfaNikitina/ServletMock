import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import logic.Model;
import logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//        int id = Integer.parseInt(request.getParameter("id"));
//        if(id == 0){
//            pw.print("<html" + "<h3> Доступные Пользователи: </h3><br/>" +
//                    "ID пользователя " +
//                    "<ul>");
//            for (Map.Entry<Integer, User> entry: model.getFromList().entrySet()) {
//                pw.print("<li>" + entry.getKey() + "</li>" +
//                        "<li>Имя: " + entry.getValue().getName() + "</li>" +
//                        "<li>Фамилия: " + entry.getValue().getSurname() + "</li>" +
//                        "<li>Зарплата: " + entry.getValue().getSalary() + "</li>" +
//                        "</ul>");
//            }
//            pw.print("</ul>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html");
//        } else if (id > 0) {
//            if (id > model.getFromList().size()) {
//                pw.print("<html>" + "<h3> No such User </h3>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html");
//            } else {
//                pw.print("<html>" + "<h3> Запршенный пользователь </h3>" +
//                        "Имя: " + model.getFromList().get(id).getName() + "<br/>" +
//                        "Фамилия: " + model.getFromList().get(id).getSurname() + "<br/>" +
//                        "Зарплата: " + model.getFromList().get(id).getSalary() + "<br/>" +
//                        "<a href=\"index.jsp\">Домой</a>" +
//                        "</html");
//            }
//            } else {
//            pw.print("<html>" + "<h3> ID должен быть больше 0 </h3>" +
//                    "<a href=\"index.jsp\">Домой</a>" +
//                    "</html");
//
//        }
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
//        BufferedReader reader = request.getReader();
        PrintWriter pw = response.getWriter();
        int input = Integer.parseInt(request.getParameter("id"));
        if (0 == input) {
            pw.print(gson.toJson(model.getFromList()));
        } else if (input > model.getFromList().size()) {
            pw.print("No such user with ID" + request.getParameter("id"));
        } else if (input < 0) {
            pw.print("ID должно быть положительным числом");
        } else {
            User user = model.getUser(input);
            pw.print(gson.toJson(user));
        }

    }
}
