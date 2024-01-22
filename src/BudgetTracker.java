import Models.Category;
import Views.UserInterface;
import Data.DataManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BudgetTracker {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        List<Category> categories = readCategoriesFromCSV("src/Data/data.csv");

        List<Category> categoryList = new ArrayList<>(categories);
        dataManager.setCategories(categoryList);

        UserInterface userInterface = new UserInterface(dataManager);

        userInterface.start();
    }


    public static List<Category> readCategoriesFromCSV(String filePath) {
        List<Category> categories = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0].trim();
                String description = parts[1].trim();

                Category category = new Category();
                category.setName(name);
                category.setDescription(description);
                categories.add(category);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categories;
    }


}