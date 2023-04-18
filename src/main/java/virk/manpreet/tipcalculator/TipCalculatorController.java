package virk.manpreet.tipcalculator;

// TipCalculatorController.java
// Controller that handles calculateButton and tipPercentageSlider events
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController { 
   // formatters for currency and percentages
private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
private static final NumberFormat percent = NumberFormat.getPercentInstance();

private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default tip

// GUI controls defined in FXML and used by the controller's code
@FXML
private TextField amountTextField;

@FXML
private TextField peopleTextField;

@FXML
private TextField eachPersonTextField;

@FXML
private Label tipPercentageLabel;
@FXML
private Slider tipPercentageSlider;

@FXML
private TextField tipTextField;

@FXML
private TextField totalTextField;

// calculates and displays the tip and total amounts
@FXML
private void calculateButtonPressed(ActionEvent event) {
try {
BigDecimal amount = new BigDecimal(amountTextField.getText());
BigDecimal people = new BigDecimal(peopleTextField.getText());
BigDecimal tip = amount.multiply(tipPercentage);
BigDecimal total = amount.add(tip);
BigDecimal eachP = total.divide(people);

tipTextField.setText(currency.format(tip));
totalTextField.setText(currency.format(total));
eachPersonTextField.setText(currency.format(eachP));
} catch (NumberFormatException ex) {
amountTextField.setText("Enter amount");
peopleTextField.setText("Enter no. of people");
amountTextField.selectAll();
amountTextField.requestFocus();
}
}

// called by FXMLLoader to initialize the controller
public void initialize() {
// 0-4 rounds down 1.0 to 1.4 = 1, 5-9 rounds up 1.5 to 1.9 = 2
currency.setRoundingMode(RoundingMode.HALF_UP);
// listener for changes to the tipPercentageSlider's value
tipPercentageSlider.valueProperty().addListener(new ChangeListener() {
public void changed(ObservableValue ov, Object oldValue, Number newValue) {
tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
tipPercentageLabel.setText(percent.format(tipPercentage));
}

@Override
public void changed(ObservableValue observable, Object oldValue, Object newValue) {
tipPercentage = BigDecimal.valueOf(((Number) newValue).intValue() / 100.0);
tipPercentageLabel.setText(percent.format(tipPercentage));
}
});
}
}