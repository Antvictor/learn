package larry.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import larry.study.list.ListTest;

/**
 * @author exccedy
 * @date 2022/7/25
 **/
@RestController
@RequestMapping("test/list")
public class TestListController {
    @Autowired
    ListTest testList1;

    @Autowired
    ListTest testList2;

    @GetMapping("/test")
    public void test() {
        testList1.add();
        System.out.println("--------------test1");
        testList1.print();
        testList2.add();
        System.out.println("--------------test2");
        testList2.print();

        System.out.println("-------------------------------");
        System.out.println("--------------test1");
        testList1.print();
        System.out.println("--------------test2");
        testList2.print();
    }
}
