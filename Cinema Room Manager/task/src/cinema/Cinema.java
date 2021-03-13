package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        int rows, seats, row, seat, boughtTickets = 0;
        float income = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows:\n> ");
        rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row:\n> ");
        seats = scanner.nextInt();
        String[][] cinema = theatreSeats(rows, seats);

        menu();
        int choice = scanner.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    printTheatreSeats(cinema, rows, seats);

                    menu();
                    choice = scanner.nextInt();
                    break;

                case 2:
                    System.out.print("\nEnter a row number:\n> ");
                    row = scanner.nextInt();
                    System.out.print("Enter a seat number in that row:\n> ");
                    seat = scanner.nextInt();
                    if (row > rows || seat > seats) {
                        System.out.println("Wrong input!");
                        choice = 2;
                        break;
                    } else if (cinema[row][seat].equals("B")) {
                        System.out.println("That ticket has already been purchased!");
                        choice = 2;
                        break;
                    } else if (cinema[row][seat].equals("S")) {
                        cinema[row][seat] = "B";
                        boughtTickets++;
                        income += searchingPrice(rows,seats,row);
                    }

                    System.out.println("\nTicket price: $" + searchingPrice(rows, seats, row));

                    menu();
                    choice = scanner.nextInt();
                    break;

                case 3:

                    System.out.println("\nNumber of purchased tickets: " + boughtTickets + "\n" +
                            "Percentage: " + String.format("%.2f", ((boughtTickets / (double)(rows * seats)) * 100)) + "%\n" +
                            "Current income: $" + (int)income + "\n" +
                            "Total income: $" + countingCost(rows, seats) + "\n");

                    menu();
                    choice = scanner.nextInt();
                    break;
            }
        }
    }

    public static int countingCost(int rows, int seats) {
        int total;
        if (rows * seats <= 60) {
            total = rows * seats * 10;
        } else {
            total = rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8;
        }
        return total;
    }

    public static void menu() {
        System.out.println("\n1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit");
        System.out.print("> ");
    }

    public static int searchingPrice(int rows, int seats, int row) {
        int cost;

        if (rows * seats <= 60) {
            cost = 10;
        } else if (row <= rows / 2) {
            cost = 10;
        } else {
            cost = 8;
        }
        return cost;
    }

    public static String[][] theatreSeats(int rows, int seats) {

        String[][] cinema = new String[rows + 1][seats + 1];

        for (int i = 0; i < rows + 1; i++) {

            for (int j = 0; j < seats + 1; j++) {

                if (i == 0 && j == 0) {
                    cinema[i][j] = " ";
                } else if (i == 0) {
                    cinema[i][j] = Integer.toString(j);
                } else if (j == 0) {
                    cinema[i][j] = Integer.toString(i);
                } else {
                    cinema[i][j] = "S";
                }
            }
        }
        return cinema;
    }

    public static void printTheatreSeats(String[][] cinema, int rows, int seats) {
        System.out.println("\nCinema:");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
}