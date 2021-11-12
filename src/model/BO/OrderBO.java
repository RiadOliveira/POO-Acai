package model.BO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.swing.JFileChooser;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfWriter;

import errors.ValidationException;

import java.util.ArrayList;

import model.DAO.CustomerDAO;
import model.DAO.OrderDAO;
import model.VO.OrderVO;
import model.VO.ProductVO;
import model.VO.CustomerVO;
import model.VO.OrderProductVO;
import utils.OrderStatus;
import utils.PaymentMethod;
import utils.ReportType;

public class OrderBO {
    private static CustomerDAO<CustomerVO> customerDAO = new CustomerDAO<CustomerVO>();
    private static OrderDAO<OrderVO> orderDAO = new OrderDAO<OrderVO>();
    
    public static void insert(OrderVO order) throws Exception {
        if(customerDAO.findById(order.getCustomer()) == null) {
            throw new Exception("Requested customer does not exist.");
        }
        
        orderDAO.insert(order);
    }
    
    public static List<OrderVO> findByDate(List<OrderVO> allOrders, LocalDate date) {
        int searchedOrdersLength = 0;
        int searchedPositions[] = new int[allOrders.size()];

        for(int ind=0, i=0 ; ind<allOrders.size() ; ind++) {
            if(allOrders.get(ind).getDate().compareTo(date) == 0) {
                searchedOrdersLength++;
                searchedPositions[i++] = ind;
            }
        }

        List<OrderVO> searchedOrders = new ArrayList<OrderVO>();

        for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
            searchedOrders.add(allOrders.get(searchedPositions[ind]));
        }

        return searchedOrders;
    }

    public static void generateReport(
        List<OrderVO> allOrders, ReportType type, LocalDate date
    ) throws FileNotFoundException, DocumentException, SQLException, ValidationException {
        Document report = generatePdf();

        List<OrderVO> searchedOrders = new ArrayList<OrderVO>();

        allOrders = OrderBO.findAll();
        type = ReportType.day;
        date = LocalDate.now();

        switch (type) { 
            case day: searchedOrders = OrderBO.findByDate(allOrders, date);
            
            case week: {
                int startOfWeek = date.getDayOfMonth() - date.getDayOfWeek().getValue();

                LocalDate initialDate = LocalDate.of(date.getYear(), date.getMonthValue(), startOfWeek);
                LocalDate finalDate = initialDate.plusDays(7);

                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.size()];

                for(int ind=0, i=0 ; ind<allOrders.size() ; ind++) {
                    if(
                        allOrders.get(ind).getDate().compareTo(initialDate) >= 0 &&
                        allOrders.get(ind).getDate().compareTo(finalDate) < 0
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders.add(allOrders.get(searchedPositions[ind]));
                }
            }
            
            case month: {
                int searchedOrdersLength = 0;
                int searchedPositions[] = new int[allOrders.size()];

                for(int ind=0, i=0 ; ind < allOrders.size() ; ind++) {
                    if(
                        allOrders.get(ind).getDate().getYear() == date.getYear() &&
                        allOrders.get(ind).getDate().getMonthValue() == date.getMonthValue()
                    ) {
                        searchedOrdersLength++;
                        searchedPositions[i++] = ind;
                    }
                }

                for(int ind=0 ; ind<searchedOrdersLength ; ind++) {
                    searchedOrders.add(allOrders.get(searchedPositions[ind]));
                }
            }
        }

        for(OrderVO order : searchedOrders) {
            List<Paragraph> documentParagraphs = new ArrayList<Paragraph>();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

            documentParagraphs.add(generateParagraph("Data", order.getDate().format(formatter)));
            documentParagraphs.add(generateParagraph("Cliente", order.getCustomer().getName()));

            String productsContentString = " ";
            List<OrderProductVO> orderProducts = OrderProductBO.findByOrder(order);
            int length = orderProducts.size();

            for(int ind = 0 ; ind < length ; ind++) {
                int quantity = orderProducts.get(0).getQuantity();
                ProductVO product = orderProducts.get(ind).getProduct();

                int verifyNumber = Double.valueOf(product.getPrice()).toString().split("\\.")[1].length();
                String extraZero = (verifyNumber == 1) ? "0" : "";

                productsContentString = productsContentString.concat("(" + quantity + "x) " + product.getName());
                productsContentString = productsContentString.concat(" (R$ " + product.getPrice() + extraZero + ")").replace(".", ",");

                if(ind + 1 != length) {
                    productsContentString = productsContentString.concat(", ");
                }
            }

            documentParagraphs.add(generateParagraph("Produtos", productsContentString));
            documentParagraphs.add(generateParagraph("Método", order.getPaymentMethod().toString()));
            documentParagraphs.add(generateParagraph("Status", order.getOrderStatus().toString()));

            int verifyNumber = Double.valueOf(order.getTotalPrice()).toString().split("\\.")[1].length();
            String extraZero = (verifyNumber == 1) ? "0" : "";

            documentParagraphs.add(generateParagraph("Preço total", "R$ " + 
                Double.valueOf(order.getTotalPrice()).toString().replace(".", ",") + extraZero)
            );

            for(Paragraph paragraph : documentParagraphs) {
                report.add(paragraph);
            }

            report.add(Chunk.NEWLINE);
            report.add(Chunk.NEWLINE);
        }

        report.close();
    }

    private static String chooseGenerateReportFolder() {
        JFileChooser chooser;

        chooser = new JFileChooser(); 
        chooser.setDialogTitle("Selecione o local para salvar o PDF");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
            return chooser.getCurrentDirectory().getPath();
        } else {
            return null;
        }
    }

    public static Document generatePdf() throws FileNotFoundException, DocumentException {
        String directoryPath = chooseGenerateReportFolder();
        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(directoryPath + "/report.pdf"));

        document.open();

        Paragraph title = new Paragraph("Dados do relatório", FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);

        document.add(title);
        document.add(Chunk.NEWLINE);

        return document;
    }

    public static Paragraph generateParagraph(String title, String content) {
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD);
        Font fontContent = FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL);

        Chunk paragraphTitle = new Chunk(title + ": ", fontTitle);
        Chunk paragraphContent = new Chunk(content, fontContent);

        Paragraph paragraph = new Paragraph();
        paragraph.add(paragraphTitle);
        paragraph.add(paragraphContent);

        return paragraph;
    }

    public static OrderVO findById(OrderVO order) throws SQLException, ValidationException {
        ResultSet findedOrderDB = orderDAO.findById(order);

        CustomerVO customer = new CustomerVO();
        customer.setId(UUID.fromString(findedOrderDB.getString("customer_id")));
        customer = CustomerBO.findById(customer);
        
        PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();
        
        OrderVO findedOrder = new OrderVO();

        findedOrder.setId(UUID.fromString(findedOrderDB.getString("id")));
        findedOrder.setCustomer(customer);
        findedOrder.setPaymentMethod(paymentMethod[findedOrderDB.getInt("payment_method")]);
        findedOrder.setOrderStatus(orderStatus[findedOrderDB.getInt("status")]);
        findedOrder.setDate(findedOrderDB.getDate("order_date").toLocalDate());

        return findedOrder;
    }

    public static List<OrderVO> findByCustomer(CustomerVO customer) throws SQLException, ValidationException {
        ResultSet findedOrdersDB = orderDAO.findByCustomer(customer);
        List<OrderVO> findedOrders = new ArrayList<OrderVO>();

        PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();

        while(findedOrdersDB.next()) {
            OrderVO order = new OrderVO();
            
            order.setId(UUID.fromString(findedOrdersDB.getString("id")));
            order.setPaymentMethod(paymentMethod[findedOrdersDB.getInt("payment_method")]);
            order.setOrderStatus(orderStatus[findedOrdersDB.getInt("status")]);
            order.setTotalPrice(findedOrdersDB.getDouble("total_price"));
            order.setDate(findedOrdersDB.getDate("order_date").toLocalDate());
            
            order.setCustomer(customer);

            findedOrders.add(order);
        }

        return findedOrders;
    }

    public static List<OrderVO> findAll() throws SQLException, ValidationException {
        ResultSet findedOrdersDB = orderDAO.findAll();
        List<OrderVO> findedOrders = new ArrayList<OrderVO>();

        PaymentMethod[] paymentMethod = PaymentMethod.values();
        OrderStatus[] orderStatus = OrderStatus.values();

        while(findedOrdersDB.next()) {
            OrderVO order = new OrderVO();
            
            order.setId(UUID.fromString(findedOrdersDB.getString("id")));
            order.setPaymentMethod(paymentMethod[findedOrdersDB.getInt("payment_method")]);
            order.setOrderStatus(orderStatus[findedOrdersDB.getInt("status")]);
            order.setTotalPrice(findedOrdersDB.getDouble("total_price"));
            order.setDate(findedOrdersDB.getDate("order_date").toLocalDate());
            
            CustomerVO customer = new CustomerVO();
            customer.setId(UUID.fromString(findedOrdersDB.getString("customer_id")));
            customer = CustomerBO.findById(customer);
            
            order.setCustomer(customer);

            findedOrders.add(order);
        }

        return findedOrders;
    }

    public static void update(OrderVO order) throws Exception {
        if(orderDAO.findById(order) == null) {
            throw new Exception("Order not found.");
        }

        if(customerDAO.findById(order.getCustomer()) == null) {
            throw new Exception("Customer not found.");
        }

        orderDAO.update(order);
    }

    public static void delete(OrderVO order) throws Exception {
        if(orderDAO.findById(order) == null) {
            throw new Exception("Order not found.");
        }

        orderDAO.delete(order);
    }
}
