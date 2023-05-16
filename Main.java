import Toys.BaseToy;
import Toys.SimpleToy;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static ArrayList<BaseToy> StoreToys = new ArrayList<>();
    public static ArrayList<BaseToy> PrizeToys = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        init();
        getMenu();

    }

    public static void init() {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Введите количество видов игрушек");
        int counts = sc.nextInt();
        for (int i = 0; i < counts; i++) {
            System.out.println("Введите наименование игрушки");
            String toyName = sc.next();
            System.out.println("Введите количество игрушек");
            int toyCount = sc.nextInt();
            System.out.println("Введите вероятность выигрыша игрушки игрушки от 0 до 100");
            int toyChance = sc.nextInt();
            StoreToys.add(new SimpleToy(i + 1, toyName, toyCount, toyChance));
        }
        //sc.close();
    }

    public static void addNewToy() {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Введите наименование игрушки");
        String toyName = sc.next();
        System.out.println("Введите количество игрушек");
        int toyCount = sc.nextInt();
        System.out.println("Введите вероятность выигрыша игрушки игрушки от 0 до 100");
        int toyChance = sc.nextInt();
        StoreToys.add(new SimpleToy(StoreToys.size() + 1, toyName, toyCount, toyChance));
        //sc.close();
    }

    public static void changeChance() {
        //Scanner sc = new Scanner(System.in);
        System.out.println("Введите id игрушки");
        int id = sc.nextInt();

        for (BaseToy s : StoreToys) {
            if (s.getId() == id) {
                System.out.println(s.getChance());
                System.out.println("Введите новое значение вероятности");
                s.setChance(sc.nextInt());
            }
        }
        //sc.close();
    }

    public static void lottery() {
        int allChance = 0;
        for (BaseToy s : StoreToys) {
            allChance += s.getChance();
        }
        int tmp = 0;
        for (int i = 0; i < StoreToys.size(); i++) {
            StoreToys.get(i).ch1 = tmp + 1;
            tmp = (int) (100 / allChance) * StoreToys.get(i).getChance();
            StoreToys.get(i).ch2 = tmp;
        }
        tmp = new Random().nextInt(101);
        for (int i = 0; i < StoreToys.size(); i++) {
            if (StoreToys.get(i).ch1 < tmp & tmp > StoreToys.get(i).ch2) {
                PrizeToys.add(StoreToys.get(i));
                StoreToys.get(i).setCount(StoreToys.get(i).getCount() - 1);
            }
        }
    }

    public static void getPrice() {
        try (FileWriter writer = new FileWriter("price.txt", true)) {
            String text = PrizeToys.get(0).getName() + '\n';
            writer.write(text);
            
            PrizeToys.remove(0);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getMenu() {
        //Scanner sc = new Scanner(System.in);
        int menu = 0;
        while (menu != 5) {
            System.out.println("1 добавить игрушку");
            System.out.println("2 изменить вероятность");
            System.out.println("3 Провести розыгрыш");
            System.out.println("4 Получить приз");
            System.out.println("5 выход");
            System.out.println("Введите пункт меню");

            menu = sc.nextInt();

            switch (menu) {
                case 1:
                    addNewToy();
                    break;
                case 2:
                    changeChance();
                    break;
                case 3:
                    lottery();
                    break;
                case 4:
                    getPrice();
                    break;
                case 5:
                    menu = 5;
                    break;
            }
            
        }
        //sc.close();
    }
}