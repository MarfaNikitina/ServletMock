import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import logic.Model;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
//        BufferedReader reader = request.getReader();
        PrintWriter pw = response.getWriter();
        try {
            int input = Integer.parseInt(request.getParameter("id"));
            if (input > model.getFromList().size()) {
                pw.print("No such user with ID: " + request.getParameter("id"));
            } else if (input <= 0) {
                pw.print("ID должно быть положительным числом");
            } else {
                model.delete(input);
                pw.println("Пользователь успешно удален");
                pw.print(gson.toJson(model.getFromList()));
            }
        }
        catch (Exception e) {
            System.out.println("Error");
        }

    }
}
