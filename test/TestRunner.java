/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Cladius
 */
public class TestRunner {
    
    public TestRunner() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    public static void main(String[] args){
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        //Result res = new JUnitCore.runClasses(GameplayTest.class);
        junit.run(GameplayTest.class);
        
        for (Failure fail: junit.run(GameplayTest.class).getFailures()) {
            System.out.println(fail.toString());
        }
        System.out.println(junit.run(GameplayTest.class).wasSuccessful());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
