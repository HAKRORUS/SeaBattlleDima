package com.example.seabattlledima;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Field {
    public static int dimensionField;
    static {
        System.out.println("В этой игре нужно задать размер поля для игры (от 10х10 (классика) до 28х28) " +
                "- укажите количество клеток по одной стороне");
        // и устанавливаем значение размероности поля для всех полей
        //  dimensionField = Main.choiceFromRangeNumbers(10,28);
    }

    //переменная количества кораблей (на будущее)
    public static int countShips = 10;

    //шаблон отображения столбцов поля (28 букв!)
    static Character[] alphabet = {'а','б','в','г','д','е','ж','з','и','к',
            'л','м','н','о','п','р','с','т','у','ф',
            'х','ц','ч','ш','щ','э','ю','я'};
    //шаблон отображения линий поля (по количеству букв!)
    static String[] numberOfLine = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28"};
    //шаблонный список ВСЕХ возможных ходов в человеческом формате!!!
    public static ArrayList<String> allCoordinates = new ArrayList<>();
    //помещаем заполнение статической переменной в блок статик, так как это делается один раз при инициализации класса
    static {
        for (int i = 0; i < dimensionField; i++) {
            for (int j = 0; j < dimensionField; j++) {
                allCoordinates.add(alphabet[i] + numberOfLine[j]);
            }
        }
    }
    //таблица перевода строчного ввода координат в цифровой, содеражщийся в парах
    public static HashMap<String,Pair<Integer,Integer>> translateTable = new HashMap<>();
    //помещаем заполнение статической переменной в блок статик, так как это делается один раз при инициализации класса
    static {
        //цикл для строк
        for (int i = 0; i < dimensionField; i++) {
            //значение строки получаем из значения итератора плюс 1, так как строки нумеруются с 1, а не с 0
            String number = String.valueOf(i+1);
            //цикл для заполнения столбцов в данной строке
            for (int j = 0; j < dimensionField; j++) {
                //берем букву из эталона столбцов по индексу
                char letter = alphabet[j];
                //соединяем столбец и строку, чтобы получить человеческий вид координаты (индекс клетки)
                String humanIndex = letter + number;
                //создаем пару для хранения индекса клетки
                Pair<Integer,Integer> pair = new Pair<>(i,j);
                //кладем в словарь человеческие координаты и координаты для массива
                translateTable.put(humanIndex,pair);
            }
        }
    }
    //создаем заготовку поля в качестве переменной объекта (поля класса)
    public Cell[][] cells = new Cell[dimensionField][dimensionField];
    //переменная (поле класса) для учета сделанных выстрелов по данному полю в форме списка пар координат
    public ArrayList<Pair<Integer, Integer>> availableSteps = new ArrayList<>(dimensionField * dimensionField);

    //список пар координат для упрощения расстановки кораблей компьютером
    public ArrayList<Pair<Integer, Integer>> availableCoordinateForFillField = new ArrayList<>(dimensionField * dimensionField);


    // переменная для хранения клеток кораблей также в форме списка пар координат
    public ArrayList<Ship> ships = new ArrayList<>();
    //переменная для хранения имени поля (игрока или ПК)
    public String name;
    //переменная для хранения информации о наличии раненных кораблей
    boolean feildHasHurtShip = false;


    //конструктор класса, в котором создаем пустое поле
    public Field(String name) {
        //задаем имя полю
        this.name = name;
        //заполняем поле элементами по умолчанию (пустые клетки = точки)
        for (int i = 0; i < dimensionField; i++) {
            for (int j = 0; j < dimensionField; j++) {
                //конструктор клетки
                cells[i][j] = new Cell();
                //тут же заносим в переменную список пар координат данного поля
                availableSteps.add(new Pair<>(i,j));
                availableCoordinateForFillField.add(new Pair<>(i,j));
            }
        }
    }

    //метод зачистки поля
    public void erase() {
        //очищаем список кораблей
        this.ships.clear();
        //очищаем список координат для расстановки
        this.availableCoordinateForFillField.clear();
        //проходим по всем клеткам поля
        for (int i = 0; i < Field.dimensionField; i++) {
            for (int j = 0; j < Field.dimensionField; j++) {
                //воссоздаем список координат
                this.availableCoordinateForFillField.add(new Pair<>(i,j));
                //каждой клетке ставим исходный статус
                this.cells[i][j].setStatus(1);
                //каждой клетке ставим НЕвидимость
                this.cells[i][j].visible = false;
            }
        }
    }


    //метод поиска корабля по координате
    public int findShip (Pair<Integer, Integer> pair) {
        //проходим циклом по списку кораблей данного поля
        for (int i = 0; i < this.ships.size(); i++) {
            //далее проходим циклом по позициям i-го корабля в списке
            for (int j = 0; j < this.ships.get(i).positions.length; j++) {
                //если нашлось совпадение выстрела и позиции корабля, то
//                if ((this.ships.get(i).positions[j].getKey().equals(pair.getKey()))
//                        && (this.ships.get(i).positions[j].getValue().equals(pair.getValue()))) {
//                    //возвращаем индекс корабля в списке корблей данного поля
//                    return i;

 //               }
            }
        }
        //ставим отрицательное значение, что должно вызвать исключение (НЕ ДОЛЖНО ПОЯВЛЯТЬСЯ!).
        // Мы так можем сделать, поскольку в данный метод мы зайдем только если попали в корабль,
        // а значит совпадение точно найдется.
        return -1;
    }

    //метод для удаления координат из списка (либо для списка возможных ходов,
    // либо из списка координат для расстановки)
    public void delFromAvailable (ArrayList<Pair<Integer, Integer>> list, Pair<Integer, Integer> pair) {
        Iterator<Pair<Integer, Integer>> iterator = list.iterator();
        while (iterator.hasNext()) {
            Pair<Integer, Integer> nextPair = iterator.next();
//            if ((nextPair.getKey().equals(pair.getKey())) && (nextPair.getValue().equals(pair.getValue()))) {
//                iterator.remove();
//            }
        }
    }

    //метод вывода в консоль полей с текущим состоянием клеток. В параметр name передаем имя поля,
    // которое надо исключить для вывода (когда не нужно выводить поле игрока)
    public static void print(String name, Field[] fields) {
        //разделитель
        for (Field field : fields) {
            //разделитель вместо цифр
            System.out.print("---");
            if (!field.name.equals(name)) {
                //выводим разделители по количеству столбцов
                for (int j = 0; j < dimensionField; j++) {
                    System.out.print("--");
                }
            }
        }
        //перевод на новую строку
        System.out.println();
        //цикл для вывода подписей полей
        for (int i = 0; i < fields.length; i++) {
            //если нет совпадения с переданным именем, то печатаем все, если же есть, то исключаем указанное поле
            if (!fields[i].name.equals(name)) {
                //для первого поля - поля игрока, так как длина имени отличается от остальных
                if (i == 0) {
                    System.out.print("   " + fields[i].name);
                    for (int j = 0; j < (dimensionField * 2 - 2); j++) {
                        System.out.print(" ");
                    }
                    //для вывода имен компьютеров
                } else {
                    System.out.print(fields[i].name);
                    for (int j = 0; j < (dimensionField * 2 - 8); j++) {
                        System.out.print(" ");
                    }
                }
            } else {
                System.out.print("   ");
            }
        }
        //перевод на новую строку
        System.out.println();
        //вывод подписей столбцов (алфавит)
        for (Field field : fields) {
            //исключаем вывод для указанного поля
            if (!field.name.equals(name)) {
                System.out.print("  ");
                for (int j = 0; j < dimensionField; j++) {
                    String s = " " + alphabet[j];
                    System.out.print(s.toUpperCase());
                }
                System.out.print(" ");
            }
        }
        //перевод на новую строку
        System.out.println();
        //вывод строк
        for (int i = 0; i < dimensionField; i++) {
            //каждая строка состоит из цифры и строки поля
            for (Field field : fields) {
                if (!field.name.equals(name)) {
                    //добавляем пробел, чтобы выровнить с двузначными числами
                    if (i < 9) {
                        System.out.print(" " + numberOfLine[i] + " ");
                    } else {
                        System.out.print(numberOfLine[i] + " ");
                    }
                    //в каждом поле выводим клетки i-й строки
                    for (int j = 0; j < dimensionField; j++) {
                        System.out.print(field.cells[i][j] + " ");
                    }
                }
            }
            //перевод на новую строку
            System.out.println();
        }
        //разделитель
        for (Field field : fields) {
            //разделитель вместо цифр
            System.out.print("---");
            if (!field.name.equals(name)) {
                //выводим разделители по количеству столбцов
                for (int j = 0; j < dimensionField; j++) {
                    System.out.print("--");
                }
            }
        }
        //перевод на новую строку
        System.out.println();
    }

    //метод вывода в консоль указанного поля с текущим состоянием клеток
    public static void print(Field field) {
        //разделитель
        System.out.print("---");
        // выводим разделители по количеству столбцов
        for (int j = 0; j < dimensionField; j++) {
            System.out.print("--");
        }
        //перевод на новую строку
        System.out.println();
        //вывод имени поля (без цикла, так как одно)
        System.out.println("   " + field.name);
        //отсутп для красоты
        System.out.print("  ");
        //алфавит
        for (int j = 0; j < dimensionField; j++) {
            String s = " " + alphabet[j];
            System.out.print(s.toUpperCase());
        }
        //перевод на новую строку
        System.out.println();
        //вывод строк
        for (int i = 0; i < dimensionField; i++) {
            //сначала идет цифра
            if (i < 9) {
                System.out.print(" " + numberOfLine[i] + " ");
            } else {
                System.out.print(numberOfLine[i] + " ");
            }
            //потом сами клетки
            for (int j = 0; j < dimensionField; j++) {
                System.out.print(field.cells[i][j] + " ");
            }
            //перевод на новую строку
            System.out.println();
        }
        //разделитель
        System.out.print("---");
        // выводим разделители по количеству столбцов
        for (int j = 0; j < dimensionField; j++) {
            System.out.print("--");
        }
        //перевод на новую строку
        System.out.println();
    }

}
