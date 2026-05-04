package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DinnerConstructor {

    HashMap<String, List<String>> dinnersByType;
    Random random;

    public DinnerConstructor() {
        this.dinnersByType = new HashMap<>();
        this.random = new Random();
    }


    public void addNewDish(String dishType, String dishName) {
        List<String> dishesForType = new ArrayList<>();
        if (dinnersByType.containsKey(dishType)) {
            dishesForType = dinnersByType.get(dishType);
        } else {
            dinnersByType.put(dishType, dishesForType);
        }
        dishesForType.add(dishName);
    }

    public List<List<String>> generateCombos(int comboNumber, List<String> dishTypes) {
        List<List<String>> combos = new ArrayList<>(); //пустой список для хранения получившихся комбинаций блюд
        for (int i = 0; i <= comboNumber; i++) {
            List<String> combo = generateCombo(dishTypes); //одна комбинация блюд генерируется в отдельном методе
            combos.add(combo);
        }
        return combos;
    }

    public boolean checkType(String type) {
        return dinnersByType.containsKey(type);
    }

    private List<String> generateCombo(List<String> dishTypes) {
        List<String> selectedDishes = new ArrayList<>(dishTypes.size());
        for (String dishType : dishTypes) {
            List<String> availableDishes = dinnersByType.get(dishType);
            String selectedDish = getRandomDish(availableDishes);
            selectedDishes.add(selectedDish);
        }
        return selectedDishes;
    }

    private String getRandomDish(List<String> availableDishes) {
        int numberOfDishesForType = availableDishes.size();
        int dishIndex = random.nextInt(numberOfDishesForType);
        return availableDishes.get(dishIndex);
    }

}
