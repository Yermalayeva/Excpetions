/**
 * Создайте класс счетчик, у которого есть метод add(), увеливающий
 * значение внутренний int переменной на 1. Сделайте так, чтобы с обьектом
 * такого типа можно было работать в блоке try-with-resources. Подумайте, что
 * должно происходить при закрытии этого ресурса? Напишите метод ля проверки
 * закрыт ли ресурс. При попытке вызвать add() у закрытого ресурса,
 * должен выбрасываться IOException.
 * 
 */


public class Task2 {
    public static void main(String[] args) {
        try(Counter counter1 = new Counter()){
            System.out.printf("Текущее значение счетчика %s \n", counter1);
            counter1.add();
            System.out.printf("Текущее значение счетчика %s \n", counter1);
            counter1.closeCounter();
            counter1.add();
        }
        catch (CloseCounterException e){
            System.out.println("Ошибка  " + e.getMessage());
        }
        
    }
}


class Counter implements AutoCloseable{
    private int counter;
    private boolean isOpen;

    public Counter(){
        this.counter = 0;
        this.isOpen = true;
    }

    public Counter(int count){
        this.counter = count;
        this.isOpen = true;
    }

    public int add() throws CloseCounterException{
        if (isOpen){
            return ++counter; 
        }
        throw new CloseCounterException("Счетчик закрыт");
    }

    public void closeCounter(){
        this.isOpen = false;
    }

    @Override
    public String toString(){
        return "" + this.counter;
    }

    @Override
    public void close(){
        closeCounter();
    }

}



class CloseCounterException extends Exception{
    public CloseCounterException(String message){
        super(message);
    }
}
