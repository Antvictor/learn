package antvictor.study.design.factorymethod;

/**
 * 工厂方法模式
 * 适用于：创建对象有大量重复代码。客户端只需要知道产品的类型，无需知道产品的创建细节。
 * 优点：用户只需要关心产品的类型，无需关心产品的创建细节。
 *      符合开闭原则，每新增一个产品，只需要增加对应的工厂类，不需要修改原有代码。
 * 缺点：类的数量容易过多，增加新产品时，就需要增加对应的工厂类。
 *      增加了结构的复杂度以及系统的抽象性和理解难度
 * @author Antvictor
 * @date 2023/12/16
 **/
public interface ICourseFactory {
    public ICourse create();
}
