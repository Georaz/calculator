import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InputException, TypesException, OperatorException, RomanException, FormatException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        calc(input);
        scanner.close();
    }

    public static String calc(String input) throws InputException, TypesException, OperatorException, RomanException, FormatException {

        // Разбиваем входящую строку с выражением и создаём массив.
        String[] strings = input.split(" ");

        // Проверка формата выражения.
        if (strings.length != 3) {
            throw new FormatException("Неверный формат выражения!");
        }

        // Создаём массив из объектов перечисления с парами римских и арабских чисел.
        TenRomans[] romans = TenRomans.values();

        // Сохраняем оператор в переменную.
        char op = strings[1].charAt(0);

        // Создаём переменные для записи результата в числе и строке.
        int result = 0;
        String resultRoman;

        // Объявляем и инициализируем операнды.
        int a = 0;
        int b = 0;

        // Создаём вспомогательные переменные для определения типа числа.
        String firstType = "";
        String secondType = "";

        for (int i = 0; i < romans.length; i++) {
            // Символ а содержит римское число.
            if (strings[0].equals(romans[i].key)) {
                a = romans[i].value;
                firstType = "Roman";
            }
            if (strings[0].equals(Integer.toString(romans[i].value))) {
            // Символ а содержит арабское число
                a = romans[i].value;
                firstType = "Numeric";
            }
        }

        for (int i = 0; i < romans.length; i++) {
            if (strings[2].equals(romans[i].key)) {
                // Символ b содержит римское число.
                b = romans[i].value;
                secondType = "Roman";
            }
            if (strings[2].equals(Integer.toString(romans[i].value))) {
                // Символ b содержит арабское число
                b = romans[i].value;
                secondType = "Numeric";
            }
        }
        // Проверка на диапазон чисел.
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10)) {
            throw new InputException("Числа должны быть в диапазоне от 1 до 10 (I до X) включительно!");
        }
        // Проверка на совпадение типов чисел.
        if (!(firstType.equals(secondType))) {
            throw new TypesException("Типы чисел не совпадают!");
        }

        // Проверка оператора.
        try {
            switch (op) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;
                    default:
                throw new OperatorException("Некорректный оператор!");
            }
        } catch(OperatorException e) {
            throw new OperatorException("Некорректный оператор!");
        }

        // Последняя проверка и запись результата в зависимости от типа чисел в операндах.
        if (firstType == "Roman" && secondType == "Roman" && (result < 1 || result > 100)) {
            throw new RomanException("Римские числа не могут равняться или быть меньше 0!");
        } else if (firstType == "Roman" && secondType == "Roman" && (result > 0 && result < 101)) {
            System.out.println("Результат: " + allRomans[result - 1]);
            resultRoman = allRomans[result - 1];
            return resultRoman;
        } else {
            System.out.println("Результат: " + result);
            return Integer.toString(result);
        }
    }

    enum TenRomans {
        I("I", 1),
        II("II", 2),
        III("III", 3),
        IV("IV", 4),
        V("V", 5),
        VI("VI", 6),
        VII("VII", 7),
        VIII("VIII", 8),
        IX("IX", 9),
        X("X", 10);

        private String key;
        private int value;

        TenRomans(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static String[] allRomans = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
}