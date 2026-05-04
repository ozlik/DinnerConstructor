import service.DinnerConstructor;

static DinnerConstructor dc;
static Scanner scanner;

public static void main(String[] unused) {
    dc = new DinnerConstructor();
    scanner = new Scanner(System.in);
    boolean isActive = true;

    while (isActive) {
        printMenu();
        int command;
        try {
            command = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введите число!");
            scanner.next();
            continue;
        }

        switch (command) {
            case 1 -> addNewDish();

            case 2 -> generateDishCombo();

            case 3 -> isActive = false;

        }
    }
}

private static void printMenu() {
    System.out.println("Выберите команду:");
    System.out.println("1 - Добавить новое блюдо");
    System.out.println("2 - Сгенерировать комбинации блюд");
    System.out.println("3 - Выход");
}

private static void addNewDish() {
    System.out.println("Введите тип блюда:");
    String dishType = scanner.nextLine().trim();
    System.out.println("Введите название блюда:");
    String dishName = scanner.nextLine().trim();
    dc.addNewDish(dishType, dishName);
}

private static void generateDishCombo() {
    System.out.println("Начинаем конструировать обед...");

    System.out.println("Введите количество наборов, которые нужно сгенерировать:");
    int numberOfCombos = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). " +
            "Для завершения ввода введите пустую строку");
    String nextItem = scanner.nextLine().trim();

    List<String> selectedTypes = new ArrayList<>();
    while (!nextItem.isEmpty()) {
        if (dc.checkType(nextItem)) {
            selectedTypes.add(nextItem);
        } else {
            System.out.println("Такой тип блюд мы еще не умеем готовить. Попробуйте что-нибудь другое!");
        }
        nextItem = scanner.nextLine();
    }

    List<List<String>> generatedCombos = dc.generateCombos(numberOfCombos, selectedTypes);
    for (int i = 0; i < numberOfCombos; i++) {
        System.out.println("Комбинация " + (i + 1));
        System.out.println(generatedCombos.get(i));
    }
}
