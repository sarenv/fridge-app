package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// Unit tests for Fridge class
public class FridgeTest {
    private Fridge testFridge;

    @BeforeEach
    void runBefore() {
        testFridge = new Fridge();
    }

    @Test
    void TestConstructor() {
        assertEquals(0, testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE, testFridge.getFridgeSpace());
    }

    //Tests for add method
    @Test
    void testAddSuccessfulOneElement() {
        Food f1 = new Food("carrot",3,25);

        testFridge.add(f1);
        assertEquals(1,testFridge.getListOfFood().size());
    }

    @Test
    void testAddSuccessfulTwoElements() {
        Food f1 = new Food("carrot",3,25);
        Food f2 = new Food("apple",1,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(2,testFridge.getListOfFood().size());
    }

    @Test
    void testAddFailFullFridge() {
        Food f1 = new Food("pizza",Fridge.MAX_FRIDGE_SPACE,100);
        Food f2 = new Food("apple",1,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(1,testFridge.getListOfFood().size());
    }

    @Test
    void testAddFailFoodBiggerThanCapacity() {
        Food f1 = new Food("pizza",Fridge.MAX_FRIDGE_SPACE -5,100);
        Food f2 = new Food("apple",6,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(1,testFridge.getListOfFood().size());
    }

    // Tests for remove method
    @Test
    void testRemoveSuccessfulOneElement() {
        Food f1= new Food("cake",20,10);

        testFridge.add(f1);
        testFridge.removeFood(f1.getName());
        assertEquals(0,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE,testFridge.getFridgeSpace());
    }

    @Test
    void testRemoveSuccessfulTwoElements() {
        Food f1= new Food("cake",20,10);
        Food f2= new Food("cheese",15,30);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.removeFood(f1.getName());
        assertEquals(1,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE -(f2.getFoodSize()),testFridge.getFridgeSpace());
    }

    @Test
    void testRemoveFailEmpty() {
        testFridge.removeFood("cake");
        assertEquals(0,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE,testFridge.getFridgeSpace());
    }

    @Test
    void testRemoveFailFoodNotFound() {
        Food f1= new Food("cake",20,10);
        Food f2= new Food("cheese",15,30);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.removeFood("sprouts");
        assertEquals(2,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE -f1.getFoodSize()-f2.getFoodSize(),testFridge.getFridgeSpace());
    }

    // Tests for contains method
    @Test
    void testContainsTrueOneElement() {
        Food f1= new Food("cake",20,10);

        testFridge.add(f1);
        assertTrue(testFridge.containsName(f1.getName()));

    }

    @Test
    void testContainsTrueTwoElements() {
        Food f1= new Food("cake",20,10);
        Food f2= new Food("cheese",15,30);

        testFridge.add(f1);
        testFridge.add(f2);
        assertTrue(testFridge.containsName(f2.getName()));

    }

    @Test
    void testContainsFalseEmpty() {
        assertFalse(testFridge.containsName("Cake"));
    }

    @Test
    void testContainsFalseFoodNotFound() {
        Food f1 = new Food("soda",5,100);
        Food f2 = new Food("leftovers",10,20);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.add(f1);
        testFridge.add(f1);
        assertFalse(testFridge.containsName("cheese"));
    }

    // Tests for expired method

    @Test
    void testExpiredEmpty() {
        testFridge.expired();

        assertEquals(0,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE,testFridge.getFridgeSpace());
    }

    @Test
    void testExpiredNoExpiration() {
        Food f1 = new Food("soda",5,100);
        Food f2 = new Food("leftovers",10,20);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.expired();
        assertEquals(2,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE -f1.getFoodSize()-f2.getFoodSize(),testFridge.getFridgeSpace());
    }

    @Test
    void testExpireOneExpiredFirst() {
        Food f1 = new Food("soda",0,0);
        Food f2 = new Food("leftovers",10,20);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.expired();
        assertEquals(1,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE -f2.getFoodSize(),testFridge.getFridgeSpace());
    }

    @Test
    void testExpireOneExpiredSecond() {
        Food f1 = new Food("soda",88,100);
        Food f2 = new Food("leftovers",2,0);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.expired();
        assertEquals(1,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE -f1.getFoodSize(),testFridge.getFridgeSpace());
    }

    @Test
    void testExpiredAllExpired() {
        Food f1 = new Food("soda",8,-1);
        Food f2 = new Food("leftovers",28,-1);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.expired();
        assertEquals(0,testFridge.getListOfFood().size());
        assertEquals(Fridge.MAX_FRIDGE_SPACE,testFridge.getFridgeSpace());
    }
}
