package servlet;

import dao.UtilisateurDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utilisateur;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/ImportEtudiantsServlet")
@MultipartConfig
public class ImportEtudiantsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {
        int count = 0;

        try {
            Part filePart = request.getPart("fichier");
            InputStream is = filePart.getInputStream();

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            UtilisateurDAO dao = new UtilisateurDAO();

            for(int i = 1; i <= sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                if(row == null) continue;

                String email      = getCellValue(row.getCell(0));
                String motDePasse = getCellValue(row.getCell(1));

                if(email == null || email.isEmpty()) continue;
                if(dao.existeParEmail(email)) continue;

                Utilisateur u = new Utilisateur();
                u.setEmail(email);
                u.setMotDePasse(motDePasse);
                u.setRole("ETUDIANT");

                dao.save(u);
                count++;
            }

            workbook.close();
            response.sendRedirect("importEtudiants.jsp?success=" + count);

        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("importEtudiants.jsp?error=1");
        }
    }

    private String getCellValue(Cell cell) {
        if(cell == null) return "";
        switch(cell.getCellType()) {
            case STRING:  return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default:      return "";
        }
    }
}