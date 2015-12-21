package gml.greendao;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by user on 2015/12/21.
 */
public class TableGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1000, "gml.greendao");
        addUser(schema);
        new DaoGenerator().generateAll(schema, "../src/main/java");
    }

    private static void addUser(Schema schema){
        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement();
        user.addStringProperty("username").notNull();    // 开始时间
        user.addStringProperty("password");      // 结束时间
        user.addLongProperty("createtime");      // 循环间隔时间
        user.addStringProperty("remark");    // 通知内容
    }
}
