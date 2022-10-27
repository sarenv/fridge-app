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
        assertEquals(0, testFridge.getFridge().size());
        assertEquals(Fridge.MAX_CAPACITY, testFridge.getCapacity());
    }

    //Tests for add method
    @Test
    void testAddSuccessfulOneElement() {
        Food f1 = new Food("Carrot",3,25);

        testFridge.add(f1);
        assertEquals(1,testFridge.getFridge().size());
    }

    @Test
    void testAddSuccessfulTwoElements() {
        Food f1 = new Food("Carrot",3,25);
        Food f2 = new Food("Apple",1,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(2,testFridge.getFridge().size());
    }

    @Test
    void testAddFailFullFridge() {
        Food f1 = new Food("Pizza",Fridge.MAX_CAPACITY,100);
        Food f2 = new Food("Apple",1,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(1,testFridge.getFridge().size());
    }

    @Test
    void testAddFailFoodBiggerThanCapacity() {
        Food f1 = new Food("Pizza",Fridge.MAX_CAPACITY-5,100);
        Food f2 = new Food("Apple",6,36);

        testFridge.add(f1);
        testFridge.add(f2);
        assertEquals(1,testFridge.getFridge().size());
    }

    // Tests for remove method
    @Test
    void testRemoveSuccessfulOneElement() {
        Food f1= new Food("Cake",20,10);

        testFridge.add(f1);
        testFridge.remove(f1.getName());
        assertEquals(0,testFridge.getFridge().size());
    }

    @Test
    void testRemoveSuccessfulTwoElements() {
        Food f1= new Food("Cake",20,10);
        Food f2= new Food("Cheese",15,30);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.remove(f1.getName());
        assertEquals(1,testFridge.getFridge().size());
    }

    @Test
    void testRemoveFailEmpty() {
        testFridge.remove("Cake");
        assertEquals(0,testFridge.getFridge().size());
    }

    @Test
    void testRemoveFailFoodNotFound() {
        Food f1= new Food("Cake",20,10);
        Food f2= new Food("Cheese",15,30);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.remove("Sprouts");
        assertEquals(2,testFridge.getFridge().size());
    }

    // Tests for contains method
    @Test
    void testContainsTrueOneElement() {
        Food f1= new Food("Cake",20,10);

        testFridge.add(f1);
        assertTrue(testFridge.containsName(f1.getName()));

    }

    @Test
    void testContainsTrueTwoElements() {
        Food f1= new Food("Cake",20,10);
        Food f2= new Food("Cheese",15,30);

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
        Food f1 = new Food("Soda",5,100);
        Food f2 = new Food("Leftovers",10,20);

        testFridge.add(f1);
        testFridge.add(f2);
        testFridge.add(f1);
        testFridge.add(f1);
        assertFalse(testFridge.containsName("Cheese"));
    }


}
