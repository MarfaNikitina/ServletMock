package Calculator;

import Calculator.logic.Calculator;
import Calculator.logic.NoSuchOperatorException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/calc")
public class ServletCalculator extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int x = jobj.get("a").getAsInt();
        int y = jobj.get("b").getAsInt();
        String operator = jobj.get("math").getAsString();
        Calculator calculator = new Calculator(x, y, operator);
        try{
            double result = calculator.getResult();
            pw.print("{\"result\" : \"" + result + "\"}");
        } catch (Calculator.ZeroDivisionException e) {
            pw.print("{\"message:\" \"Деление на 0 запрещено\"}");
        } catch (NoSuchOperatorException e) {
            pw.print("{\"message:\" \"Оператор не существует\"}");
        }
    }
}
