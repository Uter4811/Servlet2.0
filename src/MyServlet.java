import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", value = "/test")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(request.getInputStream(), "UTF-8")
        );

        String line = null;
        String message = new String();
        final StringBuffer buffer = new StringBuffer(2048);
        while ((line = rd.readLine()) != null) {
            // buffer.append(line);
            message += line;
        }
        String requiredString2 = message.substring(message.indexOf("<ubiNum>") + 2, message.indexOf("</ubiNum>"));

        int num = Integer.parseInt(requiredString2);

        response.setContentType ("text/xml; charset=UTF-8");


        String s = "";
        if (num > 10){
            s = "ok";
        }else s = "fail";
        String line2 = String.format("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                "      <status>%s/status>\n" +
                "    </NumberToWords>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>\n", s);

        PrintWriter pw = response.getWriter();
        pw.write(line2);
       /* pw.append("test");
        pw.flush();*/
        pw.close();

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //  String requiredString = request.toString();
     //   String requiredString2 = requiredString.substring(requiredString.indexOf("<ubiNum>") + 2, requiredString.indexOf("</ubiNum>"));

       // int num = Integer.parseInt(requiredString2);
       /* int num = 9;

        response.setContentType ("text/xml; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        String s = "";
        if (num > 10){
            s = "ok";
        }else s = "fail";
        String line = String.format("<status> %s </status>", s);

        pw.write(line);
       *//* pw.append("test");
        pw.flush();*//*
        pw.close();*/


    }

}
