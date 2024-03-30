import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import logic.Model;
import logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;


/*
{
"name": "Anna",
"surname": "Linn",
"salary": 20000
}
 */
@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
//        int id = Integer.parseInt(request.getParameter("id"));
//        if (id > model.getFromList().size()) {
//            pw.print("{\"message:\" \"No such user with ID:" + request.getParameter("id") + "\"}");
//        } else if (id <= 0) {
//            pw.print("{\"message:\" \"ID должно быть положительным числом\"}");
//        } else {
//            JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);
//
//            request.setCharacterEncoding("UTF-8");
//
//            String name = jobj.get("name").getAsString();
//            String surname = jobj.get("surname").getAsString();
//            double salary = jobj.get("salary").getAsDouble();
//
//            User user = new User(name, surname, salary);
//            model.put(id, user);
//
//            pw.print("{\"message:\" \"Пользователь успешно изменен\"}");
//
//            pw.print(gson.toJson(model.getFromList()));

        try {
            int input = Integer.parseInt(request.getParameter("id"));
            if (input <= 0) {
                pw.print("{\"message:\" \"ID должно быть положительным числом\"}");
            } else {
                for (Map.Entry<Integer, User> pair : model.getFromList().entrySet()) {
                    if (input == pair.getKey()) {
                        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

                        request.setCharacterEncoding("UTF-8");

                        String name = jobj.get("name").getAsString();
                        String surname = jobj.get("surname").getAsString();
                        double salary = jobj.get("salary").getAsDouble();
                        User user = new User(name, surname, salary);
                        model.put(input, user);
                        pw.print("{\"message:\" \"Пользователь успешно изменен\"}");
                        pw.print(gson.toJson(model.getFromList()));  // Добавлено для наглядности, что удаляются
                        return;
                    }
                }
                pw.print("{\"message:\" \"No such user with ID:" + request.getParameter("id") + "\"}");
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}