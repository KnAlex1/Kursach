package tools;

import entities.artists.Artist;
import entities.disks.Disk;
import entities.tracks.Track;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.internet.*;
import javax.mail.*;

public class Tools {//Клас інструментів
    public static String lengthToString(int length){//Метод перетворення довжини в строку
        int hours = (int)Math.round(length*1.0 / 3600);//Отримання годин
        int minutes = (length - hours*3600) / 60;//Отримання хвилин
        int seconds = length - hours * 3600 - minutes * 60;//Отримання секунд
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);//Повернення строки
    }

    public static void clearScreen() {//Метод очищення екрану
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }//Виконання команди очищення екрану
        catch (Exception ex) { System.out.println(ex.toString()); }//Виведення помилки
    }

    public static void pauseScreen() {//Метод паузи екрану
        try { new ProcessBuilder("cmd", "/c", "PAUSE>nul").inheritIO().start().waitFor(); }//Виконання команди паузи екрану
        catch (Exception ex) { System.out.println(ex.toString()); }//Виведення помилки
    }

    public static List<Artist> readArtistsFromFile(String filename){//Метод зчитування артистів з файлу
        String buffer;//Буферний рядок
        List<Artist> result = new ArrayList<>();//Створення списку артистів

        try {//Спроба зчитування
            FileReader fr = new FileReader(filename);//Створення об'єкту для зчитування з файлу
            Scanner scan = new Scanner(fr);//Створення об'єкту для зчитування з файлу

            while (scan.hasNextLine()) {//Поки є рядки
                buffer = scan.nextLine();//Зчитування рядка
                String[] info = buffer.split("\t");//Розділення рядка на частини
                result.add(new Artist(info[0], info[1]));//Додавання артиста до списку
            }

            fr.close();//Закриття файлу
        }
        catch (Exception e){//Помилка
            System.out.println("[readArtistsFromFile] Something gone wrong while reading file " + filename);//Виведення повідомлення
            System.out.println(e.toString());//Виведення помилки
        }

        return result;//Повернення списку артистів
    }


    public static List<Track> readTracksFromFile(String filename, List<Artist> artists){//Метод зчитування треків з файлу
        String buffer;//Буферний рядок
        List<Track> result = new ArrayList<>();//Створення списку треків

        try {//Спроба зчитування
            FileReader fr = new FileReader(filename);//Створення об'єкту для зчитування з файлу
            Scanner scan = new Scanner(fr);

            while (scan.hasNextLine()) {//Поки є рядки
                buffer = scan.nextLine();//Зчитування рядка
                String[] info = buffer.split("\t");//Розділення рядка на частини
                result.add(new Track(info[0], artists.get(Integer.parseInt(info[1])-1), Integer.parseInt(info[2]), Genre.valueOf(info[3])));//Додавання трека до списку
            }

            fr.close();//Закриття файлу
        }
        catch (Exception e){//Помилка
            System.out.println("[readTracksFromFile] Something gone wrong while reading file " + filename);//Виведення повідомлення
            System.out.println(e.toString());//Виведення помилки
        }

        return result;//Повернення списку треків
    }

    public static List<Disk> readDisksFromFile(String filename, List<Track> tracks){//Метод зчитування дисків з файлу
        String buffer;//Буферний рядок
        List<Disk> result = new ArrayList<>();//Створення списку дисків

        try {//Спроба зчитування
            FileReader fr = new FileReader(filename);//Створення об'єкту для зчитування з файлу
            Scanner scan = new Scanner(fr);//Створення об'єкту для зчитування з файлу

            while (scan.hasNextLine()) {//Поки є рядки
                List<Track> diskTracks = new ArrayList<>();//Створення списку треків диска

                buffer = scan.nextLine();//Зчитування рядка
                String[] info = buffer.split("\t");//Розділення рядка на частини
                String[] tracksBuffer = info[2].split(", ");

                for (String t : tracksBuffer){//Для кожного трека
                    diskTracks.add(tracks.get(Integer.parseInt(t)-1));//Додавання трека до списку треків диска
                }

                result.add(new Disk(info[0], Double.parseDouble(info[1]), diskTracks));//Додавання диска до списку
            }

            fr.close();//Закриття файлу
        }
        catch (Exception e){//Помилка
            System.out.println("[readDisksFromFile] Something gone wrong while reading file " + filename);//Виведення повідомлення
            System.out.println(e.toString());//Виведення помилки
        }

        return result;//Повернення списку дисків
    }

//    public static void sendEmail(String subject, String text) {// створюємо клас для відправки електронної пошти
//        String to = "obukhanich91@gmail.com";// адреса отримувача
//        String from = "obukhanich91@gmail.com";// адреса відправника
//        String host = "smtp.gmail.com";// адреса поштового сервера
//
//        Properties properties = System.getProperties();// отримуємо системні властивості
//        properties.put("mail.smtp.host", host);// встановлюємо адресу поштового сервера
//        properties.put("mail.smtp.port", "465");// встановлюємо порт
//        properties.put("mail.smtp.ssl.enable", "true");// встановлюємо властивість SSL
//        properties.put("mail.smtp.auth", "true");// встановлюємо властивість авторизації
//
//        Session session = Session.getInstance(properties, new Authenticator() {// створюємо сесію
//            protected PasswordAuthentication getPasswordAuthentication() {// перевизначаємо метод для авторизації
//                return new PasswordAuthentication(from, "!you@will#regret$123");// повертаємо об'єкт з логіном та паролем
//            }
//        });
//
//        session.setDebug(true);// встановлюємо властивість дебагу
//
//        try {// відправляємо повідомлення
//            MimeMessage message = new MimeMessage(session);// створюємо об'єкт повідомлення
//            message.setFrom(new InternetAddress(from));// встановлюємо адресу відправника
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));// встановлюємо адресу отримувача
//            message.setSubject(subject);// встановлюємо тему повідомлення
//            message.setText(text);// встановлюємо текст повідомлення
//
//            Transport.send(message);// відправляємо повідомлення
//        } catch (Exception mex) {// перехоплюємо помилку
//            mex.printStackTrace();// виводимо помилку
//        }
//    }
}
