package practical.practical5.actions;

public class Summing implements Action {
    public double invoke(String firstNumber, String secondNumber){
        return Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
    }
}
