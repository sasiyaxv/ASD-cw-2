package Services.Base;

import Data.DataManager;

public class BaseService {

    protected DataManager dataManager;
    public BaseService() {
        this.dataManager = DataManager.getInstance();
    }
}
