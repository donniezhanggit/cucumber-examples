package com.vish.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.PrintStream;

/**
 * Created by vish on 14/7/16.
 */
public class step_definitions {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();

    /**
     * start console capture
     * @return the old print stream.
     */
    private PrintStream startConsoleCapture() {
        //http://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
        //initialize output stream
        if (baos == null)
            baos = new ByteArrayOutputStream();
        else baos.reset();

        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        return old;
    }

    private String stopConsoleCapture(PrintStream oldPrintStream) {
        // Put things back
        System.out.flush();
        System.setOut(oldPrintStream);
        // Show what happened
        System.out.println("Captured console output: " + baos.toString());
        return baos.toString();
    }

    @When("^I run the parking lot application with file \"([^\"]*)\"$")
    public void iRunTheParkingLotApplicationWithFile(String arg0) throws Throwable {
        PrintStream old = startConsoleCapture();
        app.main(arg0);
        stopConsoleCapture(old);
    }

    @Then("^output should contain \"([^\"]*)\"$")
    public void outputShouldContain(String arg1) throws Throwable {
        Assert.assertTrue("expected \"" + arg1 + "\" but found \"" + baos.toString() + "\"",
                baos.toString().toLowerCase().contains(arg1.toLowerCase()));
    }


    @When("^I type \"([^\"]*)\"$")
    public void i_type(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


}
