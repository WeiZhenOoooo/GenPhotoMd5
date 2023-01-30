package com.xingfu;

import com.beust.jcommander.JCommander;
import com.xingfu.param.CLI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        CLI cli = new CLI();
        JCommander jCommander = JCommander.newBuilder().addObject(cli).build();
        jCommander.parse(args);
        cli.run(jCommander);
    }
}
