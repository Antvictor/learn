package antvictor.study.server.impl;

import antvictor.api.Api;

/**
 * @author exccedy
 * @date 2023/4/13
 **/
public class ApiImpl implements Api {


    @Override
    public String getTest(String name) {
        return "Hello, " + name;
    }
}
