package testacceptance;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import analyseresult.Result;
import datastructure.EnduroMap;
import datastructure.Person;
import parse.ParseInRegister;

public class AcceptanceTest1 {
    private File endFile, startFile;
    private String resultString;

    @Before
    public void setup() throws FileNotFoundException {
        endFile = new File("acceptance/acceptanstest1/maltider.txt");
        startFile = new File("acceptance/acceptanstest1/starttider.txt");
        resultString = readFile(new File("acceptance/acceptanstest1/resultat.txt"));
    }

    private String readFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scan.hasNextLine()) {
            sb.append(scan.nextLine() + "\n");
        }
        scan.close();
        return sb.toString();
    }

    @Test
    public void testAcceptance() throws IOException {
        EnduroMap em = new EnduroMap();
        for (int i = 1; i < 6; i++) {
            em.addParticipant(new Person(i));
        }
        ParseInRegister pir = new ParseInRegister(em);
        pir.parseStartFile(startFile);
        pir.parseEndFile(endFile);
        Result r = new Result(pir.getEnduroMap());
        r.generateMinimalOneLapResult("mockresult.txt");
        assertEquals(readFile(new File("mockresult.txt")), resultString);
    }

    @After
    public void clear() {
        new File("mockresult.txt").delete();
    }
}