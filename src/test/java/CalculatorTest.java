import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorTest {

    private Calculator calculator;


    @BeforeClass
    public void setUp() {
        calculator = new Calculator();
    }


    @AfterClass
    public void tearDown() {
        calculator = null;
    }


    @Test(description = "Перевірка методу додавання", priority = 1)
    public void testAddition() {
        int result = calculator.add(5, 3);
        System.out.println("Результат додавання: " + result);
        Assert.assertEquals(result, 8);
    }


    @Test(description = "Перевірка методу віднімання", priority = 2)
    public void testSubtraction() {
        int result = calculator.subtract(10, 5);
        System.out.println("Результат віднімання: " + result);
        Assert.assertEquals(result, 5);
    }


    @Test(description = "Перевірка методу множення", priority = 3)
    public void testMultiplication() {
        int result = calculator.multiply(4, 2);
        System.out.println("Результат множення: " + result);
        Assert.assertEquals(result, 8);
    }


    @Test(description = "Перевірка методу ділення", priority = 4)
    public void testDivision() {
        double result = calculator.divide(10, 2);
        System.out.println("Результат ділення: " + result);
        Assert.assertEquals(result, 5.0);
    }


    @Test(description = "Перевірка на виняток при діленні на нуль", priority = 5, expectedExceptions = ArithmeticException.class)
    public void testDivisionByZero() {
        calculator.divide(10, 0);
    }
}
