package controller.modals;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import errors.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.BO.OrderBO;
import model.BO.OrderProductBO;
import model.VO.OrderProductVO;
import model.VO.OrderVO;
import model.VO.ProductVO;

public class GenerateInvoiceModal {
    @FXML TextFlow date;
    @FXML TextFlow customer;
    @FXML TextFlow products;
    @FXML TextFlow paymentMethod;
    @FXML TextFlow totalPrice;

    public void initialize() { 
        String productsContentString = " ";
        OrderVO order = new OrderVO();

        try {
            FinishSaleModal finishSaleModal = new FinishSaleModal();
            order = OrderBO.findById(finishSaleModal.getOrder());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

            generateTexts(date, "Data", order.getDate().format(formatter));
            generateTexts(customer, "Cliente", order.getCustomer().getName());

            //Produtos
            List<OrderProductVO> orderProducts = OrderProductBO.findByOrder(order);

            int length = orderProducts.size();

            for(int ind = 0 ; ind < length ; ind++) {
                int quantity = orderProducts.get(ind).getQuantity();
                ProductVO product = orderProducts.get(ind).getProduct();

                int verifyNumber = Double.valueOf(product.getPrice()).toString().split("\\.")[1].length();
                String extraZero = (verifyNumber == 1) ? "0" : "";

                productsContentString = productsContentString.concat("(" + quantity + "x) " + product.getName());
                productsContentString = productsContentString.concat(" (R$ " + product.getPrice() + extraZero + ")").replace(".", ",");

                if(ind + 1 != length) {
                    productsContentString = productsContentString.concat(", ");
                }
            }
        } catch (SQLException | ValidationException err) {
            System.out.println(err.getMessage());
        }  

        generateTexts(products, "Produtos", productsContentString);
        generateTexts(paymentMethod, "Método de pagamento", order.getPaymentMethod().toString());

        int verifyNumber = Double.valueOf(order.getTotalPrice()).toString().split("\\.")[1].length();
        String extraZero = (verifyNumber == 1) ? "0" : "";

        generateTexts(totalPrice, "Preço total", "R$ " + 
            Double.valueOf(order.getTotalPrice()).toString().replace(".", ",") + extraZero
        );
    }

    private static void generateTexts(TextFlow flow, String title, String content) {
        Text textTitle = new Text(title + ": ");
        textTitle.setStyle("-fx-font-weight: bold");

        Text textContent = new Text(content);
        
        flow.getChildren().addAll(textTitle, textContent);
    }
}
