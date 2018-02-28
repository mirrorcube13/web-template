package servlet;

import entity.Item;
import service.ItemsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by Andrey on 26.01.2017.
 */
@WebServlet(urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String fileName = "C:\\Users\\Andrey\\IdeaProjects\\MyWebProject\\report.txt";
        String fileType = "text/txt";

        response.setContentType(fileType);
        response.setHeader("Content-disposition", "attachment; filename=report.txt");
        File my_file = new File(fileName);
        createReport(fileName);
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    public void createReport(String FileName) {
        List<Item> items = ItemsService.getInstance().getAllItems();
        try (FileOutputStream fos = new FileOutputStream(FileName)) {
            for (Item item : items) {
                String text = item.toString() + "\n";
                byte[] buffer = text.getBytes();
                fos.write(buffer, 0, buffer.length);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}