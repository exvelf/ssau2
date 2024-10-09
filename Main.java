import functions.FunctionsPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        // Пример создания табулированной функции
        double[] values = {0, 1, 4, 9, 16}; // Значения функции x^2
        TabulatedFunction function = new TabulatedFunction(0, 4, values);

        // Выводим точки
        function.displayPoint();

        // Проверка значений функции в различных точках
        System.out.println("Значение функции в точке 2: " + function.getFunctionValue(2));
        System.out.println("Значение функции в точке 3: " + function.getFunctionValue(3));
        System.out.println("Значение функции в точке 5 (вне области определения): " + function.getFunctionValue(6));

        // Удаление точки и вывод результатов
        function.deletePoint(1); // Удаляем точку (1; 1)
        System.out.println("После удаления точки:");
        function.displayPoint();

        // Добавление новой точки
        function.addPoint(new FunctionsPoint(1, 2)); // Добавляем точку (1; 2)
        System.out.println("После добавления точки (1; 2):");
        function.displayPoint();

        // Проверка значений функции после изменений
        System.out.println("Значение функции в точке 1: " + function.getFunctionValue(1));
    }
}
