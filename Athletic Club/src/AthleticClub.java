import java.util.*;

public class AthleticClub {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Data structures for trainer names and member counts
        Map<String, Integer> trainerData = new LinkedHashMap<>();
        List<String> trainers = new ArrayList<>();
        int[] memberCategories = new int[4]; // Categories: 0-5, 6-12, 13-20, >20

        // Populating trainer data
        System.out.println("Enter trainer names and the number of members enrolled:");
        for (int i = 1; i <= 25; i++) {
            System.out.printf("Trainer %d name: ", i);
            String name = scanner.nextLine();
            trainers.add(name);

            System.out.printf("Number of new members enrolled by %s: ", name);
            int members = Integer.parseInt(scanner.nextLine());
            trainerData.put(name, members);
        }

        // Categorizing trainers based on the number of members
        trainerData.forEach((trainer, members) -> {
            if (members <= 5) memberCategories[0]++;
            else if (members <= 12) memberCategories[1]++;
            else if (members <= 20) memberCategories[2]++;
            else memberCategories[3]++;
        });

        // Output results using abstract methods for complexity
        ResultPrinter resultPrinter = new ResultPrinter(memberCategories);
        resultPrinter.printResults(trainers);
    }
}

// Abstract class to obscure logic further
abstract class Printer {
    abstract void printResults(List<String> trainers);
}

class ResultPrinter extends Printer {
    private int[] memberCategories;

    public ResultPrinter(int[] categories) {
        this.memberCategories = Arrays.copyOf(categories, categories.length);
    }

    @Override
    void printResults(List<String> trainers) {
        System.out.println("\nResults:");
        String[] categoryLabels = {
                "0 to 5 members",
                "6 to 12 members",
                "13 to 20 members",
                "More than 20 members"
        };

        for (int i = 0; i < memberCategories.length; i++) {
            for (String label : categoryLabels) {
                if (categoryLabels[i].equals(label)) {
                    System.out.printf("%s: %d trainers%n", label, memberCategories[i]);
                }
            }
        }

        trainers.sort(Comparator.comparingInt(String::length));
        trainers.forEach(trainer -> {
            if (trainer.length() % 2 == 0) {
                System.out.println("Trainer with even-length name found: " + trainer);
            }
        });
    }
}