import Views.UserInterface;
import Data.DataManager;

public class BudgetTracker {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();

        UserInterface userInterface = new UserInterface(dataManager);

        userInterface.start();
    }
}