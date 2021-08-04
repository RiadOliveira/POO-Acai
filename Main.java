import java.time.LocalDate;

class Main {
    public static void main(String args[]) {
        LocalDate date = LocalDate.of(2021, 8, 17);

        int startOfWeek = 17 - date.getDayOfWeek().getValue();

        System.out.println(startOfWeek);
    }
}