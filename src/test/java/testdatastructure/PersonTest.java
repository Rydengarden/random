package testdatastructure;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import datastructure.EnduroMap;
import datastructure.EnduroTime;
import datastructure.Person;

public class PersonTest {
    Person p1;

    @Before
	public void setUp() {
    p1 = new Person(1);
    }
    
        @Test
        public void testmkString() {
            // TODO
            assertEquals(true, true);
        }

        @Test
        public void testOnlyStrtNrDetails() {
            assertEquals(p1.details(), "1; ");
        }

        @Test
        public void testAddedDetail() {
            p1.setDetail("Klubb1", "123");
            assertEquals(p1.details(), "1; 123; ");
        }

        @Test
        public void testGetAndSet() {
            p1.putEndTime(EnduroTime.parse("12:00:00"));
            p1.putStartTime(EnduroTime.parse("11:00:00"));
            assertEquals(p1.getEndTimes().get(0), EnduroTime.parse("12:00:00"));
            assertEquals(p1.getStartTimes().get(0), EnduroTime.parse("11:00:00"));
            assertEquals(p1.getNbr(), 1);
        }

       @Test
         public void testPutGetName(){
            p1.putName("Calle");
           assertEquals(p1.getName(), "Calle");
        }
        @Test 
        public void TestNameOutput(){
            EnduroMap map = new EnduroMap();
            for(int i = 1;i< 6;i++)
            {
            Person p = new Person(i);
            p.putName("Calle" + i);
            map.addParticipant(p);
            }
        
        }
        

}