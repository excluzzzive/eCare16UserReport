package ru.simflex.ex.services.implementation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import ru.simflex.ex.dao.interfaces.TariffDao;
import ru.simflex.ex.dao.interfaces.UserDao;
import ru.simflex.ex.entities.WSTariff;
import ru.simflex.ex.entities.WSUser;
import ru.simflex.ex.exceptions.TariffBusinessException;
import ru.simflex.ex.exceptions.UserBusinessException;
import ru.simflex.ex.services.interfaces.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User service implementation.
 */
@Stateless
public class UserServiceImplementation implements UserService {

    /**
     * UserDao instance.
     */
    @EJB
    private UserDao userDao;

    /**
     * TariffDao instance.
     */
    @EJB
    private TariffDao tariffDao;

    /**
     * {@inheritDoc}
     */
    public String generateUserReportById(int tariffId) {
        try {
            List<WSTariff> tariffList = tariffDao.getTariffList();
            WSTariff selectedTariff = null;

            if (!tariffList.isEmpty()) {
                for (WSTariff tariff : tariffList) {
                    if (tariff.getId() == tariffId) {
                        selectedTariff = tariff;
                    }
                }
            }

            if (selectedTariff == null) {
                throw new TariffBusinessException("Tariff not found! Maybe it was deleted.");
            }

            List<WSUser> userList = userDao.getUserListByTariffId(selectedTariff.getId());

            Document document = new Document(PageSize.A4, 50, 10, 30, 30);
            ServletContext servletContext = (ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext();
            String realPath = servletContext.getRealPath("/WEB-INF/reports");
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = selectedTariff.getName().replaceAll("[^A-z0-9]", "")
                    + "_" + dateFormat.format(new Date()) + ".pdf";
            System.out.println(fileName);
            System.out.println(realPath);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(realPath + "\\" + fileName));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.NORMAL, new BaseColor(0, 128, 192));
            Font tariffTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.NORMAL,
                    new BaseColor(0, 128, 192));
            Font tableHeaderFont = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
            Font tableBodyFont = FontFactory.getFont(FontFactory.HELVETICA, 6, Font.NORMAL);

            Paragraph title = new Paragraph("T-Systems eCare16 User Report", titleFont);
            Paragraph tariffTitle = new Paragraph("Tariff: " + selectedTariff.getName(), tariffTitleFont);
            Chapter chapter = new Chapter(title, 1);
            chapter.setNumberDepth(0);
            Section section = chapter.addSection(tariffTitle, 1);
            section.setNumberDepth(0);
            section.add(new Paragraph("_________________"));

            if (userList != null && !userList.isEmpty()) {

                PdfPTable userTable = new PdfPTable(5);
                userTable.setWidthPercentage(100);
                userTable.setSpacingBefore(25);
                userTable.setSpacingAfter(25);

                String[] titleArray = {"Contract", "Owner", "Email", "Tariff", "Chosen Options"};
                for (int i = 0; i < titleArray.length; i++) {
                    Phrase header = new Phrase(titleArray[i], tableHeaderFont);
                    Float fontSize = header.getFont().getSize();
                    Float capHeight = header.getFont().getBaseFont().getFontDescriptor(BaseFont.CAPHEIGHT, fontSize);
                    Float padding = 5f;
                    PdfPCell cell = new PdfPCell(header);
                    cell.setPadding(padding);
                    cell.setPaddingTop(capHeight - fontSize + padding);
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    userTable.addCell(cell);
                }

                for (WSUser user : userList) {
                    userTable.addCell(new PdfPCell(new Phrase(user.getPhoneNumberString(), tableBodyFont)));
                    userTable.addCell(new PdfPCell(new Phrase(user.getFirstName() + " " + user.getLastName(),
                            tableBodyFont)));
                    userTable.addCell(new PdfPCell(new Phrase(user.getEmail(), tableBodyFont)));
                    userTable.addCell(new PdfPCell(new Phrase(user.getTariff(), tableBodyFont)));

                    String chosenOptionsString = convertChosenOptionListToString(user.getChosenOptions());
                    userTable.addCell(new PdfPCell(new Phrase(chosenOptionsString, tableBodyFont)));
                }

                section.add(userTable);
            } else {
                section.add(new Paragraph(" - No any users with such tariff", tableHeaderFont));
            }

            document.add(chapter);
            document.close();

            return realPath + "\\" + fileName;

        } catch (Exception e) {
            throw new UserBusinessException("Something gone wrong!", e);
        }

    }

    /**
     * Utility method for converting list of options to string.
     *
     * @param chosenOptionList List of options
     * @return String of options
     */
    private String convertChosenOptionListToString(List<String> chosenOptionList) {
        String chosenOptionsString = "";
        if (chosenOptionList != null && !chosenOptionList.isEmpty()) {
            for (int i = 0; i < chosenOptionList.size(); i++) {
                chosenOptionsString += chosenOptionList.get(i);
                if (i != chosenOptionList.size() - 1) {
                    chosenOptionsString += ", ";
                }
            }
        } else {
            chosenOptionsString = "-";
        }
        return chosenOptionsString;
    }
}
