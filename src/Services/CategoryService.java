package Services;

import Models.Category;
import Services.Base.BaseService;

import java.util.Scanner;

public class CategoryService extends BaseService {

    public CategoryService() {
        super();
    }

    public void addCategory() {
        Scanner scanner = new Scanner(System.in);

        String name;
        do {
            System.out.println("Enter name:");
            name = scanner.nextLine();

            if (name == null || name.trim().isEmpty()) {
                System.out.println("Name cannot be null or empty. Please try again.");
            }
        } while (name == null || name.trim().isEmpty());

        System.out.println("Enter description (optional):");
        String description = scanner.nextLine();

        dataManager.addCategories(new Category(name, description));
    }
}
