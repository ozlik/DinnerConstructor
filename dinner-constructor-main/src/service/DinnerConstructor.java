package service;

import model.Dish;

import java.util.*;

public class DinnerConstructor {

    List<Dish> dishes;
    Random random;

    public DinnerConstructor() {
        this.dishes = new ArrayList<>();
        this.random = new Random();
    }


    public void addNewDish(String dishType, String dishName) {
        if (isDishAlreadyAdded(dishType, dishName)) {
            System.out.println("Такое блюдо уже есть в меню");
            return;
        }
        dishes.add(new Dish(dishType, dishName));
    }

    public List<List<String>> generateCombos(int comboNumber, List<String> dishTypes) {
        List<List<String>> combos = new ArrayList<>();
        for (int i = 0; i <= comboNumber; i++) {
            combos.add(generateCombo(dishTypes));
        }
        return combos;
    }

    public boolean checkType(String type) {
        return getAllTypes().contains(type);
    }

    private List<String> generateCombo(List<String> dishTypes) {
        List<String> selectedDishes = new ArrayList<>(dishTypes.size());
        for (String dishType : dishTypes) {
            String selectedDish = getRandomDish(getDishesByType(dishType));
            selectedDishes.add(selectedDish);
        }
        return selectedDishes;
    }

    private String getRandomDish(List<String> availableDishes) {
        int dishIndex = random.nextInt(availableDishes.size());
        return availableDishes.get(dishIndex);
    }

    private boolean isDishAlreadyAdded(String type, String name) {
        for (Dish dish : dishes) {
            if (dish.getType().equalsIgnoreCase(type) &&
                    dish.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> getAllTypes() {
        Set<String> dishesTypes = new HashSet<>();
        for (Dish dish : dishes) {
            dishesTypes.add(dish.getType());
        }
        return dishesTypes;
    }

    private List<String> getDishesByType(String type) {
        List<String> dishesByType = new ArrayList<>();
        for(Dish dish: dishes) {
        if(dish.getType().equals(type)) {
            dishesByType.add(dish.getName());
        }
        }
        return dishesByType;
    }

}
