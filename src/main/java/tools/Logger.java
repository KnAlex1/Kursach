package tools;

import java.io.*;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Logger{//створюємо клас Logger
    private BufferedWriter fileWriter;//створюємо змінну fileWriter

    public Logger(){//створюємо конструктор класу Logger
        try{//починаємо блок try
            this.fileWriter = new BufferedWriter(new FileWriter("log.txt"));//створюємо об'єкт fileWriter
        }
        catch (Exception ex) {//починаємо блок catch
            System.out.println("Logger init error");;//виводимо повідомлення про помилку
        }
    }

    // level = 1 - Status: OK
    // level = 2 - Critical error
    public void log(int level, String text){//створюємо метод log
        String toLog = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.SSS] ").format(new Date()) + text;//створюємо змінну toLog
//
//      Якщо помилка критична - Відправити на мейл
//      if (level == 2)
//          Tools.sendEmail(EMAIL_TO, "Error", toLog);

        try{//починаємо блок try
            this.fileWriter.write(toLog);//записуємо в файл toLog
            this.fileWriter.newLine();//переходимо на новий рядок
        }
        catch (Exception ex) {//починаємо блок catch
            System.out.println(ex.toString());//виводимо повідомлення про помилку
        }
    }

    public void close(){//створюємо метод close
        try{//починаємо блок try
            this.fileWriter.close();//закриваємо файл
        }
        catch(Exception e){//починаємо блок catch
            System.out.println("Can't close logger!");//виводимо повідомлення про помилку
        }
    }
}