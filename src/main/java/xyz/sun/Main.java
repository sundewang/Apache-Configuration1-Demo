package xyz.sun;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.EnvironmentConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;

/**
 * https://blog.csdn.net/qq_30739519/article/details/50865526
 */
public class Main {
    public static void main(String[] args) throws Exception {
        readSingle();
        System.out.println("------------------------------------------");
        readList();
        readFileChange();
        System.out.println("----------------After change--------------");
        Thread.sleep(50*1000);
        readFileChange();
    }

    public static void readSingle() throws ConfigurationException {
        XMLConfiguration configuration = new XMLConfiguration("test.xml");
        // 不需要考虑根节点
        System.out.println(configuration.getString("database.url"));
        System.out.println(configuration.getString("database.port"));
        System.out.println(configuration.getString("database.login"));
        System.out.println(configuration.getString("database.password"));
    }

    public static void readList() {
        try {
            XMLConfiguration conf = new XMLConfiguration("test2.xml");
            System.out.println(conf.getString("databases.database(0).url"));
            System.out.println(conf.getString("databases.database(0).port"));
            System.out.println(conf.getString("databases.database(0).login"));
            System.out.println(conf.getString("databases.database(0).password"));
            System.out.println(conf.getString("databases.database(1).url"));
            System.out.println(conf.getString("databases.database(1).port"));
            System.out.println(conf.getString("databases.database(1).login"));
            System.out.println(conf.getString("databases.database(1).password"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 未工作
     */
    public static void readFileChange() {
        XMLConfiguration config = null;
        try {
            config = new XMLConfiguration("test.xml");
            ReloadingStrategy strategy = new FileChangedReloadingStrategy();
            ((FileChangedReloadingStrategy) strategy).setRefreshDelay(5000);
            config.setReloadingStrategy(strategy);
            System.out.println(config.getString("database.url"));
            System.out.println(config.getString("database.port"));
            System.out.println(config.getString("database.login"));
            System.out.println(config.getString("database.password"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }
}
