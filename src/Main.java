import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            while (true){
                System.out.println("Выберите действие:\n1)Открыть процесс по его названию\n2)Закрыть процесс по его PID-у\n" +
                        "3)Получить информацию о всех процессах\n4)Завершить программу");
                int way = in.nextInt();
                switch (way) {
                    case 1 -> {
                        System.out.println("Введите название процесса(.exe):");
                        String process = in.next();
                        ProcessBuilder processBuilder = new ProcessBuilder(process);// объект класса для последующего запуска процессов

                        processBuilder.start();// запуск процесса

                        Thread.sleep(2000); // задержка 2 секунды
                        // /F - принудительное завершение процесса, /Т - убийство дочерних процессов, /IM - убийство процесса по его имени
                        Runtime.getRuntime().exec("taskkill /F /T /IM " + process);
                        System.out.println();
                    }
                    case 2 -> {
                        System.out.println("Введите PID процесса, который хотите закрыть:");
                        String pid = in.next();
                        // /F - принудительное завершение процесса, /Т - убийство дочерних процессов, /PID - убийство процесса по его PID-у
                        Runtime.getRuntime().exec("taskkill /F /T /PID " + pid);
                        System.out.println("Процесс уничтожен\n");
                    }
                    case 3 -> {
                        Process p = Runtime.getRuntime().exec("tasklist");// команда для получения информации о всех процессах

                        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));// открытие потока для чтения команды

                        String line;
                        // построчные чтение и вывод
                        while ((line = input.readLine()) != null) {
                            System.out.println(line);
                        }
                        input.close(); // закрытие потока для освобождения места
                        System.out.println();
                    }
                    case 4 -> System.exit(0); // принужительное завершение программы
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}