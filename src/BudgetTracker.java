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

        List<Category> categories = dataManager.readCategoriesFromCSV("src/Data/data.csv");

        UserInterface userInterface = new UserInterface(dataManager);

        userInterface.start();
    }
}