package com.example.seabattlledima;

public class Cell {
    private int status;
    //логическая переменная, которая контролирует видимость отображения
    public boolean visible;

    public Cell() {
        //задаем статус клетки по умолчанию, а именно точку, или условно "пустая клетка"
        //также устанавливаем невидимость клетки
        this.status = 1;
        this.visible = false;
    }

    //геттер получения статуса клетки
    public int getStatus() {
        return status;
    }

    //сеттер для изменения статуса с проверкой
    public void setStatus(int status) {
        //если есть попытка установить не существующий статус, то игнорируем и выводим сообщение об ошибке (перестарховка)
        if (status > 0 && status <= 4) {
            this.status = status;
        } else {
            System.out.println("Ошибка изменения статуса!");
        }
    }

    //переопределение метода toString, а именно перевод статуса в строку (печать клетки, точнее её символа)
    @Override
    public String toString() {
        int i = this.status;
        String s = null;
        if (this.visible) {
            switch (i) {
                case 1:
                    //обозначение пустого поля
                    s = ".";
                    break;
                case 2:
                    //обозначение корабля
                    s = "Х";
                    break;
                case 3:
                    //обозначение подбитой клетки корабля
                    s = "Ж";
                    break;
                case 4:
                    //обозначение заранее пустых ходов (вокруг корабля)
                    s = "о";
                    break;
            }
        } else {
            s = ".";
        }
        return s;
    }

}
